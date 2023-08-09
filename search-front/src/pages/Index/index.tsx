import {FileInfo, PageInfo} from "../../types/search";
import {ReactNode, useEffect, useState} from "react";
import SearchCard from "../../components/SearchCard";
import { getFilePageList } from "../../services/search";
import SearchLayout from "../../components/SearchLayout";
import SearchInput from "../../components/SearchInput";
import SearchPagination from "../../components/SearchPagination";


/**
 * 获取内容列表
 * @param fileInfos 文件信息
 */
const getContentList = (fileInfos: FileInfo[]|undefined): ReactNode[] => {
    const contents: ReactNode[] = [];
    if (fileInfos == undefined) {
        return contents
    }
    fileInfos.forEach((item, index) => {
        contents.push(
            <SearchCard
                key={index}
                title={item.filename}
                projectName={item.projectName}
                contents={new Map<string, string|number>([
                    ["ID：", item.id],
                    ["文件后缀：", item.extension],
                    ["内容摘要：", item.resume],
                    ["文件大小：", item.filesize],
                    ["收录时间：", item.indexingDate]
                ])
                }/>
        )
    })
    return contents;
}


const Index: React.FC = () => {

    const [nodeList, setNodeList] = useState<ReactNode[]>();
    const [pageInfo, setPageInfo] = useState<PageInfo>({
        pageNum: 1,
        pageSize: 20,
        total: 100
    });

    useEffect(()=> {
        getFilePageList({
            channel: "test-job",
            pageNum: 1,
            pageSize: 20
        }).then(response => {
            console.log(response.data)
            // 设置分页信息
            setPageInfo({
                pageNum: response.data.pageNum,
                pageSize: response.data.pageSize,
                total: response.data.total
            })
            // 调用方法生成组件列表
            setNodeList(
                getContentList(response.data.fileInfos)
            )
        }).catch(error => {
            console.error(error)
        })

    }, [])

    return (

        <SearchLayout
            header={
                <SearchInput/>
            }
            contents={
                nodeList
            }
            footer={
                <SearchPagination defaultCurrent={pageInfo.pageNum} total={pageInfo.total}/>
            }
        />

    );
}

export default Index;