package com.sinictek.restfulapi.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-09-21
 */
@TableName("s_errorcode")
public class SErrorcode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer Code;
    private String Description;
    private  String create_time;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer Code) {
        this.Code = Code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "SErrorcode{" +
        ", Code=" + Code +
        ", Description=" + Description +
        "}";
    }
}
