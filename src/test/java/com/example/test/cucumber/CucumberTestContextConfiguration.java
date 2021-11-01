package com.example.test.cucumber;

import com.example.test.BookExampleApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BookExampleApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
