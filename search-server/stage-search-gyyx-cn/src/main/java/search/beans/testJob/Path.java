package search.beans.testJob;

import lombok.Data;

/**
 * @program: gyyx-search
 * @description: 路径
 * @author: yuanshuai
 * @create: 2023-08-07 11:03
 **/
@Data
public class Path {

    /**
     * root
     */
    private String root;

    /**
     * 虚拟路径
     */
    private String virtual;

    /**
     * 真实路径
     */
    private String real;

}
