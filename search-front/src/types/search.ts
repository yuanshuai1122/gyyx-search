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


