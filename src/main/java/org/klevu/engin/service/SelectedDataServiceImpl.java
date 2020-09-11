package org.klevu.engin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.klevu.engin.dao.SelectedDataDao;
import org.klevu.engin.entity.SelectedData;
import org.klevu.engin.repository.SelectedDataRepository;
import org.klevu.engin.vo.ResponseVO;
import org.klevu.engin.vo.SelectedDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SelectedDataServiceImpl implements SelectedDataService{

	@Autowired
	private SelectedDataRepository selectedDataRepository;
	
	@Autowired
	private SelectedDataDao selectedDataDao;
	
	@Value("${klevu.search.product.top}")
	private Long topProduct;
	
	@Override
	public ResponseVO<List<SelectedDataVO>> searchProduct(String productId) {
		
		ResponseVO<List<SelectedDataVO>> response = new ResponseVO<>();
		try {
			
			List<SelectedData> list = selectedDataRepository.findByProductId(productId);
			
			if(null != list && !list.isEmpty()) {
				AggregationResults<SelectedDataVO> result = selectedDataDao.findProductIdNotEqualsAndIpAddressInList(productId, list.stream().map(SelectedData::getIpAddress).collect(Collectors.toList()));
				if(!result.getMappedResults().isEmpty()) {
					response.setStatus(HttpStatus.OK.value());
					response.setMessage("Recommended Products Found.");
					response.setData(result.getMappedResults());
				}else {
					response.setStatus(HttpStatus.NOT_FOUND.value());
					response.setMessage("Recommended Products Not Found.");
				}
			}else {
				response.setStatus(HttpStatus.NOT_FOUND.value());
				response.setMessage("Product Id Not Found.");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			response.setData(null);
		}
		return response;
	}

	@Override
	public ResponseVO<List<SelectedDataVO>> topProduct() {
		ResponseVO<List<SelectedDataVO>> response = new ResponseVO<>();
		try {
			AggregationResults<SelectedDataVO> result = selectedDataDao.findTopNProduct(topProduct);
			
			if(result.getMappedResults().isEmpty()) {
				response.setStatus(HttpStatus.NOT_FOUND.value());
				response.setMessage("Top Product Not Found.");
				response.setData(null);
			}else {
				response.setStatus(HttpStatus.OK.value());
				response.setMessage("Top Products Found.");
				response.setData(result.getMappedResults());	
			}
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			response.setData(null);
		}
		return response;
	}

}
