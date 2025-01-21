package com.dbinc.cloudoffice.orgtree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class OrgtreeApplication extends SpringBootServletInitializer
@MapperScan(basePackages = "com.dbinc.cloudoffice.orgtree.mapper.api")
public class OrgtreeApplication
{
	/*
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) 
  {
    return builder.sources(OrgtreeApplication.class);
  }
  */
  public static void main(String[] args)
  {
    SpringApplication.run(OrgtreeApplication.class, args);
  }

}
