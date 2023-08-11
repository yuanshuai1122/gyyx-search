import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {getDocInfo} from "../../services/search";
import {DocDetail, SiderInfo} from "../../types/search";
import {Layout} from "antd";
import {Content} from "antd/es/layout/layout";


const contentStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: 800,
    color: 'black',
    backgroundColor: 'white',
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
    const [docDetail, setDocDetail] = useState<DocDetail>();

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
                        <h1>
                            {docDetail?.filename}
                        </h1>
                        <div>
                            {docDetail?.content}
                        </div>
                    </Content>
                </Layout>
            </Layout>
        </>
    );
};

export default DocumentDetail;