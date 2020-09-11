package org.klevu.engin.dao;

import java.util.List;

import org.klevu.engin.constants.SelectedDataColumnKeyConstants;
import org.klevu.engin.entity.SelectedData;
import org.klevu.engin.vo.SelectedDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;


@Repository
public class SelectedDataDaoImpl implements SelectedDataDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public AggregationResults<SelectedDataVO> findProductIdNotEqualsAndIpAddressInList(String productId, List<String> ipAddress) {

		
		
		Criteria c = Criteria.where(SelectedDataColumnKeyConstants.PRODUCT_ID).ne(productId)
					.and(SelectedDataColumnKeyConstants.IP_ADDRESS).in(ipAddress);
		
		MatchOperation matchOp = Aggregation.match(c);
		
		GroupOperation groupOp = Aggregation.group(SelectedDataColumnKeyConstants.PRODUCT_ID,SelectedDataColumnKeyConstants.PRODUCT_DETAIL)
				 .count().as(SelectedDataColumnKeyConstants.TOT_COUNT);
		
		ProjectionOperation projectOp = Aggregation.project()
				.andExpression(SelectedDataColumnKeyConstants.PRODUCT_ID).as(SelectedDataColumnKeyConstants.PRODUCT_ID)
				.andExpression(SelectedDataColumnKeyConstants.PRODUCT_DETAIL).as(SelectedDataColumnKeyConstants.PRODUCT_NAME)
				.andExclude(Fields.UNDERSCORE_ID)
				;
		
		Aggregation aggregation= Aggregation.newAggregation(matchOp,groupOp,projectOp);
		return mongoTemplate.aggregate(aggregation, SelectedData.class, SelectedDataVO.class);
	}

	@Override
	public AggregationResults<SelectedDataVO> findTopNProduct(Long topN) {
		
		GroupOperation groupOp = Aggregation.group(SelectedDataColumnKeyConstants.PRODUCT_ID,SelectedDataColumnKeyConstants.PRODUCT_DETAIL).count().as(SelectedDataColumnKeyConstants.TOT_COUNT);
		
		SortOperation sortOp = Aggregation.sort(Direction.DESC, SelectedDataColumnKeyConstants.TOT_COUNT);
		
		LimitOperation limitOp = Aggregation.limit(topN);
		
		ProjectionOperation projectOp = Aggregation.project()
				.andExpression(SelectedDataColumnKeyConstants.PRODUCT_ID).as(SelectedDataColumnKeyConstants.PRODUCT_ID)
				.andExpression(SelectedDataColumnKeyConstants.PRODUCT_DETAIL).as(SelectedDataColumnKeyConstants.PRODUCT_NAME)
				.andExclude(Fields.UNDERSCORE_ID)
				;
		
		Aggregation aggregation= Aggregation.newAggregation(groupOp,sortOp,limitOp,projectOp);
		return mongoTemplate.aggregate(aggregation, SelectedData.class, SelectedDataVO.class);
	}
}
