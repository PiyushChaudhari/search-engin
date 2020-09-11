package org.klevu.engin.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.klevu.engin.entity.SelectedData;
import org.klevu.engin.repository.SelectedDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	
	private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);
	
	@Autowired
	private SelectedDataRepository selectedDataRepository;
	
	@Value("${klevu.search.data.path}")
	private String filePath;

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(selectedDataRepository.count() == 0) {
			LOG.info("************* Load data started *************");		
			loadData();
			LOG.info("************* Load data ended *************");
		}
		
		
	}
	
	private void loadData() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line = reader.readLine();
			while(line != null){
				
				String[] data = line.split("	");
                
                SelectedData selectedData = new SelectedData();
        		selectedData.setProductId(data[0]);
        		selectedData.setIpAddress(data[1]);
        		selectedData.setProductDetail(data[2]);
        		
        		selectedDataRepository.save(selectedData);
                
                line = reader.readLine();
                
            }
		} catch (IOException ex) {
			LOG.error("Error generate when load data: {}",ex.fillInStackTrace());
		}
	}

}
