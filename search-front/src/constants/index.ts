
export const SEARCH_BASE_URL = '//search.gyyx.cn';

// 请求的数据类型
export const CONTENT_TYPE = {
  URLENCODED: 'application/x-www-form-urlencoded',
  JSON: 'application/json',
  FORM_DATA: 'multipart/form-data',
};


/**
 * 搜索渠道
 */
export class SEARCH_CHANNEL {
  /**
   * 代码渠道
   */
  public static readonly CODE: string = "test-job";

  /**
   * 微信
   */
  public static readonly WE_CHAT: string = "wechat";
}
