package search.beans;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @program: gyyx-search
 * @description: 用户
 * @author: yuanshuai
 * @create: 2023-08-07 10:07
 **/
@Data
@Document(indexName = "user")
public class User implements Serializable {

    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
