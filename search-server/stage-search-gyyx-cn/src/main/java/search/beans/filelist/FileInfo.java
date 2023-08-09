package search.beans.filelist;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: gyyx-search
 * @description: 文件列表
 * @author: yuanshuai
 * @create: 2023-08-09 12:55
 **/
@Data
public class FileInfo {

    /**
     * ID
     */
    private String id;

    /**
     * 概述
     */
    private String resume;

    /**
     * 后缀
     */
    private String extension;

    /**
     * 文件大小
     */
    private Integer filesize;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 项目名称
     */
    private String projectName;

    public void setIndexingDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = sdf.parse(dateStr);
            this.indexingDate = parse;
        }catch (Exception e) {
            this.indexingDate = new Date();
        }

    }

    /**
     * 索引日期
     */
    private Date indexingDate;

}
