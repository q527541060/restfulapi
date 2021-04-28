package com.sinictek.restfulapi.model.queryBean;

import com.sinictek.restfulapi.model.AFov;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/12/9 14:27
 * @Version 1.0
 */
@Getter
@Setter
@Data
public class AoiFovQueryBean {

    private String aoiMode;
    private String pcbIdLine;

    private String pcbImagePath;
    private String boardposition;
    private String pcbImageBase64;

    private List<AFov> fovs;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private String remark;
}
