package com.example.demo.model;

import com.example.demo.unitl.WordUtil;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/12/16.
 */
@SuppressWarnings("serial")
public class Word {
    @Value("${di}")
    private String filePath;
    private String fileName="word";
    private String fileOnlyName="1";

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilOnlyName() {
        return fileOnlyName;
    }

    public void setFilOnlyName(String filOnlyName) {
        this.fileOnlyName = filOnlyName;
    }

    public String createWord(List<String> projects){
        Map<String,Object> dataMap=new HashMap<>();
//        dataMap.put()
        String[]pj=new String[4];
        pj[0]="身体很好";
        pj[1]="好的一批";
        pj[2]="身体好的岂止一批";
        pj[3]="这情况简直结束牛皮";
        List<Map<String,Object>>listInfo=new ArrayList<Map<String, Object>>();
        int i=0;
        for (String s:projects
                ) {
            Map<String,Object>map=new HashMap<>();
            map.put("no",i+"");
            map.put("project",s);
//            int i1=
            map.put("describe",pj[i%4]);
            listInfo.add(map);
        }
        dataMap.put("listInfo",listInfo);
        String name="1";
//        filePath="F:/";
        fileOnlyName="1.doc";
//        fileName

        WordUtil.createWord(dataMap,"string.ftl",filePath,fileOnlyName);
        return "created";
    }

    public String dowloadWord() {
        /** 先判断文件是否已生成  */
        try {
            //解决中文乱码
            filePath = URLDecoder.decode(filePath, "UTF-8");
            fileOnlyName = URLDecoder.decode(fileOnlyName, "UTF-8");
            fileName = URLDecoder.decode(fileName, "UTF-8");

            //如果文件不存在，则会跳入异常，然后可以进行异常处理
            new FileInputStream(filePath + File.separator + fileOnlyName);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "dowloadWord";
    }

    /**
     * 返回最终生成的word文档 文件流
     * 下载生成的word文档
     */
    public InputStream getWordFile() {
        try {
            //解决中文乱码
            fileName = URLDecoder.decode(fileName, "UTF-8");

            /** 返回最终生成的word文件流  */
            return new FileInputStream(filePath + File.separator + fileOnlyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
