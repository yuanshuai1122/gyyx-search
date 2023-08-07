package search.beans.testjob;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

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
    @Field("content_type")
    private String contentType;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 最后修改时间
     */
    @Field("last_modified")
    private String lastModified;

    /**
     * 最后访问时间
     */
    @Field("last_accessed")
    private String lastAccessed;

    /**
     * 最后索引日期
     */
    @Field("indexing_date")
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
