package com.jumoassess.aggregator;

import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CsvCellProcessor {

	public static CellProcessor[] getInputProcessor() {
		return new CellProcessor[] {
				null,
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new ParseBigDecimal()};
	}
	
	public static CellProcessor[] getOutputProcessor() {
		return new CellProcessor[] {
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new NotNull()};
	}

}
