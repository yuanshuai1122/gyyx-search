/**
 * 文件信息
 */
export interface FileInfo {
    id: string,
    projectName: string
    filename: string,
    extension: string,
    resume: string,
    filesize: number,
    indexingDate: string,
}

/**
 * 文件信息列表
 */
export interface FileInfoList {
    fileInfoList: FileInfo[]
}

/**
 * 分页信息
 */
export interface PageInfo {
    pageNum: number,
    pageSize: number,
    total: number,
}

/**
 * 文档详情
 */
export interface DocDetail {
    id: string,
    filename: string,
    content: string,
    extension: string,
    contentType: string,
    created: string,
    lastModified: string,
    lastAccessed: string,
    indexingDate: string,
    filesize: number,
    projectName: string,
    filePath: string
}

/**
 * 侧边栏信息
 */
export interface SiderInfo {
    key: string;
    value: string | number;
}


