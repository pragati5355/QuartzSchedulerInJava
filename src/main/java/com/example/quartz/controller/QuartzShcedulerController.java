package com.example.quartz.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quartz.constants.SuccessMessage;
import com.example.quartz.model.SuccessResponse;
import com.example.quartz.service.QuartzServiceImpl;
import com.example.quartz.util.CustomResponseBuilder;

/**
 * @author Mindbowser | pragati.gawade@mindbowser.com
 */
@RestController
@RequestMapping()
public class QuartzShcedulerController {

    @Autowired
    private Environment environment;

    @Autowired
    private CustomResponseBuilder customResponseBuilder;

    @GetMapping()
    public ResponseEntity<SuccessResponse<Object>> QuartzSchedulerExample() throws SchedulerException {
        QuartzServiceImpl schedulerService = new QuartzServiceImpl();
        schedulerService.startScheduler();
        return customResponseBuilder.buildSuccessResponse(environment.getProperty(SuccessMessage.SUCESS),
                schedulerService,
                HttpStatus.OK);
    }

}
