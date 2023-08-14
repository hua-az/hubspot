package com.test.hua.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.hua.service.MessageHistoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MessageHistoryProvider sorter;

    @GetMapping("/summary")
    public String getConversationSummary() throws JsonProcessingException {

        return sorter.getMessageHistory();

    }

}
