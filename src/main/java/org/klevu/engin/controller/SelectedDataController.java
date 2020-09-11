package org.klevu.engin.controller;

import java.util.List;

import org.klevu.engin.service.SelectedDataService;
import org.klevu.engin.vo.ResponseVO;
import org.klevu.engin.vo.SelectedDataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/select-data")
public class SelectedDataController {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(SelectedDataController.class);

	@Autowired
	private SelectedDataService selectedDataService;

	@RequestMapping(value="/product/{id}")
	public ResponseVO<List<SelectedDataVO>> search(@PathVariable(name="id",required=true) String id){
		LOG.info("Product Id:> {} ",id);
		return selectedDataService.searchProduct(id);
	}
	
	@RequestMapping(value="/top-product")
	public ResponseVO<List<SelectedDataVO>> topProduct(){
		return selectedDataService.topProduct();
	}
}
