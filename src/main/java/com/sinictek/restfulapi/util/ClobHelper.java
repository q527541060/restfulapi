package com.sinictek.restfulapi.util;

import java.io.Reader;
import java.sql.Clob;

/**
 * @Author sinictek-pd
 * @Date 2021/3/18 15:37
 * @Version 1.0
 */
public class ClobHelper {
    /**
     * 将String转成Clob ,静态方法
     *
     * @param str
     *            字段
     * @return clob对象，如果出现错误，返回 null
     */
    public static Clob stringToClob(String str) {
        if (null == str)
            return null;
        else {
            try {
                java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str
                        .toCharArray());
                return c;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * 将Clob转成String ,静态方法
     *
     * @param clob
     *            字段
     * @return 内容字串，如果出现错误，返回 null
     */
    public static String clobToString(Clob clob) {
        if (clob == null)
            return null;

        StringBuffer sb = new StringBuffer(65535);// 64K
        Reader clobStream = null;
        try {
            clobStream = clob.getCharacterStream();
            char[] b = new char[60000];// 每次获取60K
            int i = 0;
            while ((i = clobStream.read(b)) != -1) {
                sb.append(b, 0, i);
            }
        } catch (Exception ex) {
            sb = null;
        } finally {
            try {
                if (clobStream != null) {
                    clobStream.close();
                }
            } catch (Exception e) {
            }
        }
        if (sb == null)
            return null;
        else
            return sb.toString();
    }
}
