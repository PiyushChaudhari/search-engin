package org.klevu.engin.service;

import java.util.List;

import org.klevu.engin.vo.ResponseVO;
import org.klevu.engin.vo.SelectedDataVO;

public interface SelectedDataService {

	public ResponseVO<List<SelectedDataVO>> searchProduct(String productId);
	
	public ResponseVO<List<SelectedDataVO>> topProduct();
}
