package com.fl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class WReportMain {

    public static void main(String[] args) {
        SpringApplication.run(WReportMain.class,args);
    }
}
