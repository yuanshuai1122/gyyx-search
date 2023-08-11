import {FileInfo, PageInfo} from "../../types/search";
import React, {ReactNode, useEffect, useState} from "react";
import SearchCard from "../../components/SearchCard";
import {getDocPageList} from "../../services/search";
import {Layout, Pagination} from "antd";

const { Header, Footer, Sider, Content } = Layout;


const contentStyle: React.CSSProperties = {
    //textAlign: 'center',
    minHeight: 300,
    color: 'white',
    backgroundColor: '#fcfcfc',
};

const siderStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: 300,
    color: '#fff',
    backgroundColor: '#3ba0e9',
};


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
                id={item.id}
                contents={new Map<string, string>([
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

interface Props {
    channel: string,
    keywords?: string,
    extension?: string,
    projectName?: string,
}

const Home = (props: Props) => {

    const {channel, keywords, extension, projectName} = props
    const [nodeList, setNodeList] = useState<ReactNode[]>();
    const [pageInfo, setPageInfo] = useState<PageInfo>({
        pageNum: 1,
        pageSize: 10,
        total: 100
    });

    /**
     * 分页处理
     * @param page 页数
     * @param pageSize 页大小
     */
    const pageChangeHandle = (page: number, pageSize: number): void => {
        setPageInfo({
            pageNum: page,
            pageSize: pageSize,
            total: pageInfo.total
        })
    }

    useEffect(()=> {
        console.log(pageInfo)
        getDocPageList({
            channel: channel,
            pageNum: pageInfo.pageNum,
            pageSize: pageInfo.pageSize,
            keywords: keywords
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
                getContentList(response.data.codeDocConstants)
            )
        }).catch(error => {
            console.error(error)
        })

    }, [keywords, pageInfo.pageNum, pageInfo.pageSize])

    return (
        <Layout>
            <Layout hasSider>
                <Content style={contentStyle}>
                    {nodeList?.map((item, index)=> {
                        return item;
                    })}
                    <Pagination
                        total={pageInfo.total}
                        pageSize={pageInfo.pageSize}
                        current={pageInfo.pageNum}
                        defaultCurrent={pageInfo.pageNum}
                        onChange={pageChangeHandle}
                        style={{textAlign: 'center'}}
                    />;
                </Content>
                <Sider
                    width={400}
                    style={siderStyle}>
                    这里是搜索选项
                </Sider>
            </Layout>
        </Layout>
    );
}

export default Home;