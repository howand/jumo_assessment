package com.jumoassess.aggregator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jumoassess.aggregator.CsvCellProcessor;
import com.jumoassess.aggregator.CsvFileMapperBean;
import com.jumoassess.aggregator.DataAggregatorImpl;
import com.jumoassess.aggregator.api.DataAggregator;
import com.jumoassess.csvfileparser.CsvReaderImpl;
import com.jumoassess.csvfileparser.CsvWriterImpl;
import com.jumoassess.csvfileparser.api.CsvReader;
import com.jumoassess.csvfileparser.api.CsvWriter;

public class DataAggregatorTest {

	@Test
	public void testDataAggregation() throws IOException {
		FileReader fileReader = new FileReader("NetworkLoans.csv");
		FileWriter fileWriter = new FileWriter("Output.csv");
		List<CsvFileMapperBean> loans;
		List<CsvFileMapperBean> results;
		List<String> aggregatedResults = new ArrayList<>();
		
		aggregatedResults.add("Network,Product,Date,Amount,Count");
		aggregatedResults.add("'Network 2','Loan Product 1',Mar,1122.00,1");
		aggregatedResults.add("'Network 1','Loan Product 2',Apr,1801.00,1");
		aggregatedResults.add("'Network 3','Loan Product 3',Apr,1928.00,1");
		aggregatedResults.add("'Network 2','Loan Product 1',Apr,5671.00,1");
		aggregatedResults.add("'Network 2','Loan Product 3',Apr,1747.00,1");
		aggregatedResults.add("'Network 3','Loan Product 2',Mar,2084.00,1");
		aggregatedResults.add("'Network 1','Loan Product 1',Mar,4098.00,2");
		
		CsvReader<CsvFileMapperBean> reader = new CsvReaderImpl<CsvFileMapperBean>(
				CsvCellProcessor.getInputProcessor(), 
				CsvFileMapperBean.class, fileReader);

		DataAggregator rowsCreator = new DataAggregatorImpl();
		
		loans = reader.parseFile();
		results = rowsCreator.aggregateData(loans);
		
		CsvWriter<CsvFileMapperBean> writer = new CsvWriterImpl<CsvFileMapperBean>(fileWriter, 
				results, new String[]{"Network", "Product", "Date", "Amount", "Count"}, 
				CsvCellProcessor.getOutputProcessor());
		
		writer.writeFile();
		
		FileReader resultFileReader = new FileReader("Output.csv");
		BufferedReader bReader = new BufferedReader(resultFileReader);
		
		assertEquals(7, results.size());
		
		String row;
		while ((row = bReader.readLine()) != null) {
			assertTrue(aggregatedResults.contains(row));
		}
		
		bReader.close();
		resultFileReader.close();
		fileReader.close();
		fileWriter.close();
	}

}
