package search.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 搜索枚举
 *
 * @author yuanshuai
 * @date 2023/08/08
 */
@Getter
@AllArgsConstructor
public enum SearchEnums {

    /**
     * 代码
     */
    CODE("test-job","代码渠道","codeSearchStrategy"),

    /**
     * 微信支付
     */
    WECHAT("wechat","企业微信渠道","wechatSearchStrategy")


    ;

    /**
     * 渠道
     */
    private String channel;
    /**
     * 描述
     */
    private String description;

    /**
     * 策略实现类对应的 beanName
     */
    private String beanName;


    /**
     * 全量数据枚举map
     */
    private static final Map<String, SearchEnums> SEARCH_ENUMS_MAP = Arrays.stream
            (SearchEnums.values()).collect(Collectors.toMap(SearchEnums::getChannel, e -> e));


    /**
     * 根据通道查找枚举类型
     *
     * @param channel 通道
     * @return {@link SearchEnums}
     */
    public static SearchEnums search(String channel) {
        if (StringUtils.isBlank(channel)) {
            return null;
        }
        return SEARCH_ENUMS_MAP.get(channel);
    }

}
