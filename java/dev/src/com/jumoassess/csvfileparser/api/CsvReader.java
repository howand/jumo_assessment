package com.jumoassess.csvfileparser.api;

import java.io.IOException;
import java.util.List;

public interface CsvReader<T> {
	
	List<T> parseFile() throws IOException;
}
