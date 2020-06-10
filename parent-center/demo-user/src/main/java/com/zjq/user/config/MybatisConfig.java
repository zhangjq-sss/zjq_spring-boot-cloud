package com.zjq.user.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zjq.user.config.DbContextHolder.DbType;

@Configuration
@MapperScan("com.zjq.user.dao")
@EnableTransactionManagement
public class MybatisConfig {
	
	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;

	@Primary
	@Bean(name = "writeDataSource")
	@ConfigurationProperties(prefix = "druid.write")
	public DataSource writeDataSource() {

		return DataSourceBuilder.create().type(dataSourceType).build();

	}

	@Bean(name = "readDataSource1")
	@ConfigurationProperties(prefix = "druid.read1")
	public DataSource readDataSource1() {

		return DataSourceBuilder.create().type(dataSourceType).build();

	}

	@Bean(name = "readDataSource2")
	@ConfigurationProperties(prefix = "druid.read2")
	public DataSource readDataSource2() {

		return DataSourceBuilder.create().type(dataSourceType).build();

	}

	/**
	 * 
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 * 
	 */
	@Bean(name = "dynamicDataSource")
	public DynamicDataSource dynamicDataSource(@Qualifier("writeDataSource") DataSource writeDataSource,

			@Qualifier("readDataSource1") DataSource readDataSource1, @Qualifier("readDataSource2") DataSource readDataSource2) {

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

		targetDataSources.put(DbType.WRITE, writeDataSource);

		targetDataSources.put(DbType.READ1, readDataSource1);

		targetDataSources.put(DbType.READ2, readDataSource2);

		DynamicDataSource dataSource = new DynamicDataSource();

		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法

		dataSource.setDefaultTargetDataSource(writeDataSource);// 默认的datasource设置为myTestDbDataSource

		return dataSource;

	}

	/**
	 * 
	 * 根据数据源创建SqlSessionFactory
	 * 
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource,

			@Value("${mybatis.typeAliasesPackage}") String typeAliasesPackage,

			@Value("${mybatis.mapperLocations}") String mapperLocations) throws Exception {

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

		factoryBean.setDataSource(dynamicDataSource);// 指定数据源(这个必须有，否则报错)

		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加

		factoryBean.setTypeAliasesPackage(typeAliasesPackage);// 指定基包

		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//

		return factoryBean.getObject();

	}

	/**
	 * 
	 * 配置事务管理器
	 * 
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {

		return new DataSourceTransactionManager(dataSource);

	}
}
