package com.coffee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coffee.entity.XieZhuan;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface XieZhuanService extends IService<XieZhuan> {


    void importFile(MultipartFile file) throws IOException;

    List<XieZhuan> getXZList();
}
