package com.daasworld.hellokarate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daasworld.hellokarate.services.FinanceDataService;
import com.utils.TextFileManipulator;

@RestController
public class FinanceDataController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private FinanceDataService financeDataService;
 
    @Autowired
    public FinanceDataController(FinanceDataService financeDataService) {
        this.financeDataService = financeDataService;
    }
    
 // $ curl localhost:8080/api/finance/initialize
    @GetMapping(value = "/api/finance/initialize", produces = "application/txt")
    public ResponseEntity<String> getFinanceDataInitialized(@RequestParam(required=false, defaultValue = "15") String size){
        logger.info("Get finance Data Initialize api called");
        String strInitializedText = "";
        int noOfLines = 0;
        //Code for Text File Initialization incase of text file empty and Set strInitializedText value to "Finance Test Data Initialized Successfully"
        try {
        	noOfLines = Integer.parseInt(size);
        } catch (Exception e) {
			// TODO: handle exception
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        strInitializedText = financeDataService.initializeFinanceTestData(noOfLines);
        if(strInitializedText == null || strInitializedText.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(strInitializedText, HttpStatus.OK);
        }
    }
    
 // $ curl localhost:8080/api/finance/1
    @GetMapping(value = "/api/finance/{index}", produces = "application/txt")
    public ResponseEntity<String> getByIndex(@PathVariable int index, @RequestParam(required=false, defaultValue = "false") String regexRequired){
        logger.info("finance api getByIndex() called");
        String str = financeDataService.getByIndex(index);
        if(Boolean.valueOf(regexRequired))
        	if(str.matches("[A-Za-z0-9\\s\\t]*finance.finance[A-Za-z0-9\\s\\t]*"))
        		str = "Regex Match Found Successfully";
        	else
        		str = "Regex Match Not Found Successfully";
        if(str == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(str, HttpStatus.OK);
        }
    }

}
