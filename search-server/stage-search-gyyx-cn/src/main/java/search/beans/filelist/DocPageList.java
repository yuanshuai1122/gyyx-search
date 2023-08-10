package search.beans.filelist;

import lombok.Data;

import java.util.List;

/**
 * @program: gyyx-search
 * @description: 文件列表
 * @author: yuanshuai
 * @create: 2023-08-09 13:00
 **/
@Data
public class DocPageList {

    /**
     * 文件信息
     */
    private List<CodeDocConstant> codeDocConstants;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 总数
     */
    private Long total;

}
