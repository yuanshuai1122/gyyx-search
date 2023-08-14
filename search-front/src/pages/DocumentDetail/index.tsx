import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {getDocInfo} from "../../services/search";
import {DocDetail, SiderInfo} from "../../types/search";
import {Layout} from "antd";
import {Content} from "antd/es/layout/layout";
import ReactMarkdown from "react-markdown";
import remarkGfm from 'remark-gfm'
import './index.css'


const contentStyle: React.CSSProperties = {
    textAlign: 'left',
    minHeight: 800,
    backgroundColor: 'white',
    paddingLeft: '5%',
    paddingRight: '5%',
};

/**
 * 构造文档信息列表
 * @param docData
 */
const buildDocInfoData = (docData: DocDetail): SiderInfo[] => {
    return [
        {
            key: "ID：",
            value: docData.id
        },
        {
            key: "所属项目：",
            value: docData.projectName
        },
        {
            key: "文档名称：",
            value: docData.filename
        },
        {
            key: "文档后缀：",
            value: docData.extension
        },
        {
            key: "文档路径：",
            value: docData.filePath
        },
        {
            key: "文档大小：",
            value: docData.filesize
        },
        {
            key: "文档类型：",
            value: docData.contentType
        },
        {
            key: "收录时间：",
            value: docData.indexingDate
        },
        {
            key: "创建时间：",
            value: docData.created
        },
        {
            key: "上次访问时间：",
            value: docData.lastAccessed
        },
        {
            key: "上次修改时间：",
            value: docData.lastModified
        },
    ]

}

type Props = {
    getDocPanel: (infos: SiderInfo[], flag: number) => void;
};

const DocumentDetail:React.FC<Props> = ({getDocPanel}) => {

    const { id} = useParams();
    const [docDetail, setDocDetail] = useState<DocDetail>({
        id: "",
        filename: "",
        content: "",
        extension: "",
        contentType: "",
        created: "",
        lastModified: "",
        lastAccessed: "",
        indexingDate: "",
        filesize: 0,
        projectName: "",
        filePath: ""
    });

    useEffect(()=> {
        getDocInfo({
            channel: 'test-job',
            id: id ?? ""
        }).then(response => {
            console.log(response.data)
            const docData: DocDetail = response.data
            setDocDetail(docData)
            getDocPanel(buildDocInfoData(docData), 1)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    return (
        <>
            <Layout>
                <Layout hasSider>
                    <Content style={contentStyle}>
                        <div className={"md-content"}>
                            <h1>
                                {docDetail.filename}
                            </h1>
                            <div>
                                <ReactMarkdown
                                    children={docDetail.content}
                                    remarkPlugins={[remarkGfm]}
                                    // components={{
                                    //     code({node, inline, className, children, ...props}) {
                                    //         const match = /language-(\w+)/.exec(className || '')
                                    //         return !inline && match ? (
                                    //             <SyntaxHighlighter
                                    //                 children={String(children).replace(/\n$/, '')}
                                    //                 style={dark}
                                    //                 language={match[1]}
                                    //                 PreTag="div"
                                    //                 {...props}
                                    //             />
                                    //         ) : (
                                    //             <code className={className} {...props}>
                                    //                 {children}
                                    //             </code>
                                    //         )
                                    //     }
                                    // }}
                                />;
                            </div>
                        </div>
                    </Content>
                </Layout>
            </Layout>
        </>
    );
};

export default DocumentDetail;