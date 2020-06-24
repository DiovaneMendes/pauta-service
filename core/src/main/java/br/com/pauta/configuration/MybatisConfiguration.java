package br.com.pauta.configuration;

import lombok.SneakyThrows;

import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MybatisConfiguration {
  @Bean
  @SneakyThrows
  public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    return sessionFactory.getObject();
  }
}