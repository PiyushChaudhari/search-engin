package org.klevu.engin.dao;

import java.util.List;

import org.klevu.engin.vo.SelectedDataVO;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

public interface SelectedDataDao {

	public AggregationResults<SelectedDataVO> findProductIdNotEqualsAndIpAddressInList(String productId,List<String> ipAddress);
	
	public AggregationResults<SelectedDataVO> findTopNProduct(Long topN);
}
