package com.jumoassess.csvfileparser;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.jumoassess.aggregator.CsvCellProcessor;
import com.jumoassess.aggregator.CsvFileMapperBean;
import com.jumoassess.csvfileparser.api.CsvReader;

public class CsvReaderImplTest {

	@Test
	public void testFileReading() throws IOException {
		FileReader fileReader = new FileReader("NetworkLoans.csv");
		CsvReader<CsvFileMapperBean> reader = new CsvReaderImpl<CsvFileMapperBean>(
				CsvCellProcessor.getInputProcessor(), CsvFileMapperBean.class, fileReader);
		
		List<CsvFileMapperBean> networkLoans = reader.parseFile();
		
		assertTrue(networkLoans.size() == 8);
	}

}
