/*
package com.sinictek.restfulapi.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
//import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;



@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {

        // 创建SQL解析器集合
       // List<ISqlParser> sqlParserList = new ArrayList<>();
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(2) {{
            put("s_pad", (metaObject, sql, tableName) -> {
                Object param = getParamValue("s_pad_yyyyMMdd", metaObject);
                String year = param !=null ? String.valueOf(param)
                        : String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) +
                        String.valueOf(Calendar.getInstance().get(Calendar.MONTH))+
                        String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                return tableName + "_" + year;
            });
        }});
       // sqlParserList.add((ISqlParser) dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
        return paginationInterceptor;
    }


    private Object getParamValue(String title, MetaObject metaObject){
        //获取参数
        Object originalObject = metaObject.getOriginalObject();
        JSONObject originalObjectJSON = JSON.parseObject(JSON.toJSONString(originalObject));
        JSONObject boundSql = originalObjectJSON.getJSONObject("boundSql");
        try {
            JSONObject parameterObject = boundSql.getJSONObject("parameterObject");
            return parameterObject.get(title);
        }catch (Exception e) {
            return null;
        }
    }

}
*/
