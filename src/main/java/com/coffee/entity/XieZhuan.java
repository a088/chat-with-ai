package com.coffee.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("xiezhuan")
public class XieZhuan {

    @TableId(type= IdType.AUTO)
    private Long id;

    @ExcelProperty("省代码")
    private String provinceCode;

    @ExcelProperty("省公司")
    private String provinceCompanny;

    @ExcelProperty("数据日期")
    private String dataDate;

    @ExcelProperty("省侧系统原因导致携转业务失败量")
    private String failCount;

    @ExcelProperty("携转业务总量")
    private String businessCount;

    @ExcelProperty("携转业务接口成功率")
    private String successRate;

}
