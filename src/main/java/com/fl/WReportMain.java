package com.fl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@ServletComponentScan
public class WReportMain {

    public static void main(String[] args) {
        SpringApplication.run(WReportMain.class,args);
    }

}
