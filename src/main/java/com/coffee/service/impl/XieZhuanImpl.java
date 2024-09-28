package com.coffee.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coffee.entity.XieZhuan;
import com.coffee.mapper.XieZhuanMapper;
import com.coffee.service.XieZhuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class XieZhuanImpl extends ServiceImpl<XieZhuanMapper, XieZhuan> implements XieZhuanService {

    @Autowired
    XieZhuanMapper xieZhuanMapper;

    private static final String UPLOAD_DIR = "C:\\Users\\Lenovo\\Desktop\\";

    @Override
    public void importFile(MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR);

        // 创建目录（如果不存在）
        Files.createDirectories(path);

        String fileName = file.getOriginalFilename();

        // 存储文件
        Files.write(path.resolve(fileName), bytes);

        String filePath = UPLOAD_DIR + fileName;
        readExcel(filePath, XieZhuan.class, new CustomExcelListener<XieZhuan>(fileName));
    }

    @Override
    public List<XieZhuan> getXZList() {
        return xieZhuanMapper.selectList(null);
    }

    class CustomExcelListener<T> extends AnalysisEventListener<T> {

        private String filename;

        public CustomExcelListener() {
        }

        public CustomExcelListener(String filename) {
            this.filename = filename;
        }


        @Override
        public void invoke(T object, AnalysisContext context) {

            switch (filename) {
                case "携转业务成功率日指标.xlsx":
                    xieZhuanMapper.insert((XieZhuan) object);
                    break;
            }
            System.out.println("解析数据：" + object);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            System.out.println("读取" + filename + "文件并存入数据库结束======");
        }

    }




    public static <T> void readExcel(String path, Class<T> clazz, AnalysisEventListener<T> listener) {
        EasyExcel.read(path, clazz, listener).sheet().doRead();
    }

}
