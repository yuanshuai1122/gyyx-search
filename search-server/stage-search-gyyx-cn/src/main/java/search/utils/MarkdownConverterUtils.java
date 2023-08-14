package search.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @program: gyyx-search
 * @description: markdown转换工具类
 * @author: yuanshuai
 * @create: 2023-08-14 09:26
 **/
@Component
public class MarkdownConverterUtils {

    public String convertToMarkdown(String code, String codeFileName) {
        String fileExtension = FilenameUtils.getExtension(codeFileName);
        if (StringUtils.isNotBlank(fileExtension)) {
            return convert(code, fileExtension);
        }

        return code;
    }

    private String convert(String code, String fileExtension) {
        // You can use a code syntax highlighter library like "pygments" to convert code to markdown
        // Here, I'm using a simple method that wraps code with triple backticks to indicate code block in markdown

        return "```" + fileExtension + "\n" + code + "\n```";
    }

}
