package com.jumoassess.csvfileparser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.jumoassess.csvfileparser.api.CsvWriter;

public class CsvWriterImpl<T> implements CsvWriter<T> {
	
	private String[] columnNames;
	private FileWriter fileWriter;
	private List<T> data;
	private CellProcessor[] processor;
	
	public CsvWriterImpl(FileWriter fileWriter, List<T> data, String[] columnNames, CellProcessor[] processor) {
		this.fileWriter = fileWriter;
		this.data = data;
		this.columnNames = columnNames;
		this.processor = processor;
	}

	@Override
	public void writeFile() throws IOException {
        ICsvBeanWriter beanWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
        beanWriter.writeHeader(columnNames);
        
        for( final T row : data ) {
            beanWriter.write(row, columnNames, processor);
        }
        
        beanWriter.close();
	}

}
