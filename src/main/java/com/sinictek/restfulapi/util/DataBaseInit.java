
package com.sinictek.restfulapi.util;

//import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * 
 * 数据库初始化
 * 
 * @author sinictek-pd
 * @version [版本号, 2020年1月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class DataBaseInit
{
    private static final Logger logger = LoggerFactory.getLogger(DataBaseInit.class);
    
    private DataBaseInit()
    {
        super();
    }
    

/**
     * 使用数据库脚本进行数据初始化-JdbcTemplate实现
     * 
     * @param sqlPathArr
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */

    public static void initUseSQL(String... sqlPathArr)
        throws IOException
    {
        int i=0;
        for (String sqlPath : sqlPathArr)
        {
            i++;
            Assert.notEmpty(sqlPathArr, "SQLPathArr length must be greater than 0");
            // 建库用临时DataSource
            MysqlDataSource dataSource = new MysqlDataSource();
            ResourceBundle config = ResourceBundle.getBundle("jdbc");
            String jdbcUrl = StringUtils.substringBeforeLast(config.getString("jdbc.url"+i), "/");
            dataSource.setUrl(jdbcUrl);
            dataSource.setUser(config.getString("jdbc.username"));
            dataSource.setPassword(config.getString("jdbc.password"));
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            //logger.info("JdbcTemplate = {}", jdbcTemplate);

            // 取数据库名
            String dataBase = StringUtils.substringAfterLast(config.getString("jdbc.url"+i), "/");
            dataBase = dataBase.contains("?") ? StringUtils.substringBefore(dataBase, "?") : dataBase;
            //logger.info("★★★★ jdbcUrl = {}, dataBase = {}", jdbcUrl, dataBase);

            // 按需建库
            jdbcTemplate.execute(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET UTF8", dataBase));

            // 正式库刷表
            dataSource.setUrl(config.getString("jdbc.url"+i)+ "&allowMultiQueries=true" );//+ "&allowMultiQueries=true"
            jdbcTemplate = new JdbcTemplate(dataSource);
            //logger.info("JdbcTemplate = {}", jdbcTemplate);
            try (InputStream inputStream = DataBaseInit.class.getResourceAsStream(sqlPath))
            {
                String sqlText = IOUtils.toString(inputStream, "utf-8");
                //logger.info("SQL = {}", sqlText);
                if (dataSource.getUrl().contains("allowMultiQueries=true"))
                {
                    logger.info("开始执行当前的初始化语句块");
                    jdbcTemplate.execute(sqlText);
                }
                else
                {
                    logger.info("开始分割执行当前的初始化语句块");
                    for (String sql : sqlText.split(";"))
                    {
                        if (StringUtils.isNotBlank(sql))
                        {
                            jdbcTemplate.execute(sql);
                        }
                    }
                }
            }
            logger.info("★★★★ execute  [{}] success!!", sqlPath);
        }
    }
}

