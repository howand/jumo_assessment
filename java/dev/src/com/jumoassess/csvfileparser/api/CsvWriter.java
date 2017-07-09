package com.jumoassess.csvfileparser.api;

import java.io.IOException;

public interface CsvWriter<T> {
	
	void writeFile() throws IOException;
}
