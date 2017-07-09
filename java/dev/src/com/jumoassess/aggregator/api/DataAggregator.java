package com.jumoassess.aggregator.api;

import java.util.List;

import com.jumoassess.aggregator.CsvFileMapperBean;

public interface DataAggregator {
	
	List<CsvFileMapperBean> aggregateData(List<CsvFileMapperBean> networkLoansMapper);
}
