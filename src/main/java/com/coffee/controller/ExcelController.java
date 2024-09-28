package com.coffee.controller;

import com.coffee.common.AjaxResult;
import com.coffee.entity.XieZhuan;
import com.coffee.service.XieZhuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private XieZhuanService xieZhuanService;

    @PostMapping("/importFile")
    private AjaxResult importFile(@RequestParam("file") MultipartFile file) throws IOException {

        xieZhuanService.importFile(file);
        return AjaxResult.success("导入成功");
    }

    @GetMapping("/getXZList")
    private AjaxResult getXZList(){
        List<XieZhuan> list = xieZhuanService.getXZList();
        return AjaxResult.success("查询成功",list);
    }


}
