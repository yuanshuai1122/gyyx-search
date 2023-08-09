import {SEARCH_BASE_URL} from "../constants";
import request from '../utils/request';

interface Props {
    channel: string,
    keywords?: string,
    extension?: string,
    projectName?: string,
    pageNum: number,
    pageSize: number
}
export const getFilePageList = (params: Props): Promise<any> => request.get(`${SEARCH_BASE_URL}/search/list`, {...params});