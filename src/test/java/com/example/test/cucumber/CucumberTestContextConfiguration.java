package com.example.test.cucumber;

import com.example.test.ConstraintissueApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = ConstraintissueApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
