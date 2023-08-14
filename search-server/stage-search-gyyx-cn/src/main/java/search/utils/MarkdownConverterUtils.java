package search.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

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
        String markdown;

        switch (fileExtension) {
            case "java":
                markdown = convertJavaToMarkdown(code);
                break;
            case "python":
                markdown = convertPythonToMarkdown(code);
                break;
            // Add more cases for other supported code file types
            default:
                markdown = "Unsupported code file type";
        }

        return markdown;
    }

    private String convertJavaToMarkdown(String code) {
        // You can use a code syntax highlighter library like "pygments" to convert code to markdown
        // Here, I'm using a simple method that wraps code with triple backticks to indicate code block in markdown

        return "```java\n" + code + "\n```";
    }

    private String convertPythonToMarkdown(String code) {
        // Similar to convertJavaToMarkdown, you can use a python syntax highlighter library

        return "```python\n" + code + "\n```";
    }

}
