package com.jumoassess.csvfileparser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.io.CsvBeanReader;

import com.jumoassess.csvfileparser.api.CsvReader;

public class CsvReaderImpl<T> implements CsvReader<T> {
	private CellProcessor[] cellProcessor;
	private Class<T> csvFileMapperBean;
	private FileReader fileReader;
	
	public CsvReaderImpl(CellProcessor[] cellProcessor, Class<T> csvFileMapperBean, FileReader fileReader) {
		this.cellProcessor = cellProcessor;
		this.csvFileMapperBean = csvFileMapperBean;
		this.fileReader = fileReader;
	}

	@Override
	public List<T> parseFile() throws IOException {
		T csvFileRow;
		List<T> parsedData = new ArrayList<>();
        ICsvBeanReader beanReader = new CsvBeanReader(this.fileReader, CsvPreference.STANDARD_PREFERENCE);
        final String[] header = beanReader.getHeader(true);
        
        while( (csvFileRow = beanReader.read(csvFileMapperBean, header, cellProcessor)) != null ) {
            parsedData.add(csvFileRow);
        }
        
        beanReader.close();
		return parsedData;
	}

}
