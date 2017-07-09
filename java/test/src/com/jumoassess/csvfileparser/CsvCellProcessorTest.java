package com.jumoassess.csvfileparser;

import static org.junit.Assert.*;

import org.junit.Test;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.jumoassess.aggregator.CsvCellProcessor;

import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.constraint.NotNull;

public class CsvCellProcessorTest {

	@Test
	public void networkParserColumn0ShouldBeNull() {
		CellProcessor[] cellProcessor = CsvCellProcessor.getInputProcessor();
		
		assertTrue(cellProcessor[0] == null);
	}
	
	@Test
	public void networkParserColumn1ShouldBeOfTypeNotNull() {
		CellProcessor[] cellProcessor = CsvCellProcessor.getInputProcessor();
		
		assertTrue(cellProcessor[1].getClass().equals(NotNull.class));
	}
	
	@Test
	public void networkParserColumn2ShouldBeOfTypeNotNull() {
		CellProcessor[] cellProcessor = CsvCellProcessor.getInputProcessor();
		
		assertTrue(cellProcessor[2].getClass().equals(NotNull.class));
	}
	
	@Test
	public void networkParserColumn3ShouldBeOfTypeNotNull() {
		CellProcessor[] cellProcessor = CsvCellProcessor.getInputProcessor();
		
		assertTrue(cellProcessor[3].getClass().equals(NotNull.class));
	}
	
	@Test
	public void networkParserColumn4ShouldBeOfTypeParseBigDecimal() {
		CellProcessor[] cellProcessor = CsvCellProcessor.getInputProcessor();
		
		assertTrue(cellProcessor[4].getClass().equals(ParseBigDecimal.class));
	}

}
