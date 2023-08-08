package search.beans.testjob;

import lombok.Data;

/**
 * @program: gyyx-search
 * @description: 文件属性
 * @author: yuanshuai
 * @create: 2023-08-07 10:59
 **/
@Data
public class File {

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
     * 最后索引日期
     */
    private String indexingDate;

    /**
     * 文件大小
     */
    private Integer filesize;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件URL
     */
    private String url;


}
