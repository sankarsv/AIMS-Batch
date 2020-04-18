/**
 * 
 */
package com.aims.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author 
shanmugapriyaJ
 *
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "mainDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource mainDataSource(){
        return DataSourceBuilder.create().build();
    }

}
