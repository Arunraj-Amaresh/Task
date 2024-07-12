package com.example.SpringBatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CsvItemProcessor implements ItemProcessor<Invoice,Invoice> {

	@Override
	public Invoice process(Invoice item) throws Exception {
		// TODO Auto-generated method stub
		

		
		
		
		return item;
	}

}
