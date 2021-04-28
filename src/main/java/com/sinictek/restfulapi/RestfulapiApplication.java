package com.sinictek.restfulapi;

import com.sinictek.restfulapi.util.DataBaseInit;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan("com.sinictek.restfulapi.dao")
@Log4j2
public class RestfulapiApplication {

    public static void main(String[] args) {
        try
        {
            DataBaseInit.initUseSQL("/sql/db_spm01.sql",
                    "/sql/db_spm02.sql",
                    "/sql/db_spm03.sql",
                    "/sql/db_spm04.sql",
                    "/sql/db_spm05.sql",
                    "/sql/db_spm06.sql",
                    "/sql/db_spm07.sql",
                    "/sql/db_spm08.sql",
                    "/sql/db_spm09.sql",
                    "/sql/db_spm10.sql",

                    "/sql/db_spm11.sql",
                    "/sql/db_spm12.sql",
                  /*  "/sql/db_spm13.sql",
                    "/sql/db_spm202204.sql",
                    "/sql/db_spm202205.sql",
                    "/sql/db_spm202206.sql",
                    "/sql/db_spm202207.sql",
                    "/sql/db_spm202208.sql",
                    "/sql/db_spm202209.sql",
                    "/sql/db_spm202210.sql",
                    "/sql/db_spm202211.sql",
                    "/sql/db_spm202212.sql",*/


                    "/sql/db_spm210001.sql"
            );
        }
        catch (IOException e)
        {
        }
        /*try
        {
            DataBaseInit.initUseSQL("/sql/db_spm202103.sql",
                    "/sql/db_spm202104.sql",
                    "/sql/db_spm202105.sql",
                    "/sql/db_spm202106.sql",
                    "/sql/db_spm202107.sql",
                    "/sql/db_spm202108.sql",
                    "/sql/db_spm202109.sql",
                    "/sql/db_spm202110.sql",
                    "/sql/db_spm202111.sql",
                    "/sql/db_spm202112.sql",

                    "/sql/db_spm202201.sql",
                    "/sql/db_spm202202.sql",
                    "/sql/db_spm202203.sql",
                    "/sql/db_spm202204.sql",
                    "/sql/db_spm202205.sql",
                    "/sql/db_spm202206.sql",
                    "/sql/db_spm202207.sql",
                    "/sql/db_spm202208.sql",
                    "/sql/db_spm202209.sql",
                    "/sql/db_spm202210.sql",
                    "/sql/db_spm202211.sql",
                    "/sql/db_spm202212.sql",


                    "/sql/db_spm210001.sql"
            );
        }
        catch (IOException e)
        {
            log.error("DataBaseInit error:"+e.getMessage());
        }*/
        SpringApplication.run(RestfulapiApplication.class, args);
    }

}
