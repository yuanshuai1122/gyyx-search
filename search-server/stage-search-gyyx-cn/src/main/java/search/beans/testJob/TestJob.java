package search.beans.testJob;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @program: gyyx-search
 * @description: test-job实体
 * @author: yuanshuai
 * @create: 2023-08-07 10:57
 **/
@Data
@Document(indexName = "test-job")
public class TestJob {

    /**
     * 索引id
     */
    @Id
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 源数据
     */
    private Object meta;

    /**
     * 文件属性
     */
    private File file;

    /**
     * 路径属性
     */
    private Path path;

}
