package com.jumoassess.aggregator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jumoassess.aggregator.api.DataAggregator;
import com.jumoassess.csvfileparser.CsvReaderImpl;
import com.jumoassess.csvfileparser.CsvWriterImpl;
import com.jumoassess.csvfileparser.api.CsvReader;
import com.jumoassess.csvfileparser.api.CsvWriter;

public class Aggregate {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("No input file specified");
			return;
		}
		
		String inputFile = args[0];
		try {
			FileReader fileReader = new FileReader(inputFile);
			FileWriter fileWriter = new FileWriter("Output.csv");
			List<CsvFileMapperBean> loans;
			List<CsvFileMapperBean> results;
			
			CsvReader<CsvFileMapperBean> reader = new CsvReaderImpl<CsvFileMapperBean>(
					CsvCellProcessor.getInputProcessor(), 
					CsvFileMapperBean.class, fileReader);
			
			DataAggregator dataAggregator = new DataAggregatorImpl();
			
			loans = reader.parseFile();
			results = dataAggregator.aggregateData(loans);
			
			CsvWriter<CsvFileMapperBean> writer = new CsvWriterImpl<CsvFileMapperBean>(fileWriter, 
					results, new String[]{"Network", "Product", "Date", "Amount", "Count"}, 
					CsvCellProcessor.getOutputProcessor());
			
			writer.writeFile();
			
			fileReader.close();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not located the specified file: " + inputFile);
		} catch (IOException e) {
			System.err.println("Unable to parse the file and aggregate the data");
		}
	}

}
