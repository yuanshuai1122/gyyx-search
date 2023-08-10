import {SEARCH_BASE_URL} from "../constants";
import request from '../utils/request';


interface DocPageListProps {
    channel: string,
    keywords?: string,
    extension?: string,
    projectName?: string,
    pageNum: number,
    pageSize: number
}

/**
 * 获取文档列表
 * @param params 参数
 */
export const getDocPageList = (params: DocPageListProps): Promise<any> => request.get(`${SEARCH_BASE_URL}/search/list`, {...params});

interface DocInfoProps {
    channel: string,
    id: string
}

/**
 * 获取文档详细信息
 * @param params 参数
 */
export const getDocInfo = (params: DocInfoProps): Promise<any> => request.get(`${SEARCH_BASE_URL}/search/info`, {...params});