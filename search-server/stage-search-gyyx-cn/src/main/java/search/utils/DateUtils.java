package search.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: gyyx-search
 * @description: 日期格式化
 * @author: yuanshuai
 * @create: 2023-08-07 13:10
 **/
public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static Date toDate(String dateString) {
        return toDate(dateString, DEFAULT_FORMAT);
    }

    public static Date toDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
