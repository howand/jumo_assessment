package com.jumoassess.csvfileparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jumoassess.aggregator.CsvCellProcessor;
import com.jumoassess.aggregator.CsvFileMapperBean;
import com.jumoassess.csvfileparser.api.CsvWriter;

public class CsvWriterImplTest {

	@Test
	public void testFileWriting() throws IOException {
		File outputFile = new File("WritingTestOutput.csv");
		
		if (outputFile.exists()) {
			outputFile.delete();
		}
		
		List<CsvFileMapperBean> loansAggregation = new ArrayList<CsvFileMapperBean>();
		FileWriter file = new FileWriter(outputFile);
		CsvWriter<CsvFileMapperBean> writer = new CsvWriterImpl<CsvFileMapperBean>(file, loansAggregation, 
				new String[] {"Network", "Product", "Date", "Amount", "Count"}, CsvCellProcessor.getOutputProcessor());

		writer.writeFile();
		
		assertTrue(outputFile.exists());
		
		FileReader result = new FileReader(outputFile);
		BufferedReader bReader = new BufferedReader(result);
		
		String columns = bReader.readLine();
		assertEquals("Network,Product,Date,Amount,Count", columns);
		bReader.close();
		result.close();
	}

}
