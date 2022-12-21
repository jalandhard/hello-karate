package com.daasworld.hellokarate.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.utils.TextFileManipulator;

@Service
public class FinanceDataService {
	
	private TextFileManipulator textFileManipulatorObject = new TextFileManipulator();
		
	public String getByIndex(int index) {
        return textFileManipulatorObject.readFinanceDataFromTextFile(index);
    }

    public String initializeFinanceTestData(int size) {
        return textFileManipulatorObject.writeFinanceDataOntoTextFile(size);
    }

}
