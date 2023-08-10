package search.beans.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: gyyx-search
 * @description: 代码文件详情
 * @author: yuanshuai
 * @create: 2023-08-10 09:28
 **/
@Data
public class CodeFileDetail {

    /**
     * ID
     */
    private String id;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 内容
     */
    private String content;

    /**
     * 文件后缀
     */
    private String extension;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 最后修改时间
     */
    private String lastModified;

    /**
     * 最后访问时间
     */
    private String lastAccessed;

    /**
     * 索引日期
     */
    private String indexingDate;

    /**
     * 文件大小
     */
    private Integer filesize;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 路径
     */
    private String filePath;


}
