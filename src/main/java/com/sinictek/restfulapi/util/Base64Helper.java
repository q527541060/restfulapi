package com.sinictek.restfulapi.util;

import com.alibaba.druid.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterOutputStream;

/**
 * @Author sinictek-pd
 * @Date 2020/9/25 15:17
 * @Version 1.0
 */
public class Base64Helper {
    /**
     * zlib压缩+base64
     */
    public static String compressData(String data) {
        ByteArrayOutputStream bos;
        DeflaterOutputStream zos;
        String out = null;
        try {
            bos = new ByteArrayOutputStream();
            zos = new DeflaterOutputStream(bos);
            zos.write(data.getBytes());
            out = net.iharder.Base64.encodeBytes(bos.toByteArray());
            zos.flush();
            zos.close();
            bos.close();
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * zlib解压+base64
     */
    public static String decompressData(String encdata) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InflaterOutputStream zos = new InflaterOutputStream(bos);
            zos.write(net.iharder.Base64.decode(encdata.getBytes()));
            zos.close();
            return new String(bos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
