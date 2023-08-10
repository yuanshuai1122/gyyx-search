import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {getDocInfo} from "../../services/search";
import {DocDetail, SiderInfo} from "../../types/search";
import {Divider, Layout, List, Typography} from "antd";
import {Content} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";


const contentStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: 600,
    color: 'black',
    backgroundColor: 'white',
};

const siderStyle: React.CSSProperties = {
    textAlign: 'left',
    minHeight: 600,
    color: 'white',
    backgroundColor: 'white',
    paddingLeft: 40

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

const DocumentDetail = () => {

    const { id} = useParams();
    const [docDetail, setDocDetail] = useState<DocDetail>();
    const [docInfo, setDocInfo] = useState<SiderInfo[]>()

    useEffect(()=> {
        getDocInfo({
            channel: 'test-job',
            id: id ?? ""
        }).then(response => {
            console.log(response.data)
            const docData: DocDetail = response.data
            setDocDetail(docData)
            setDocInfo(buildDocInfoData(docData))
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
                    <Sider
                        width={400}
                        style={siderStyle}>
                        <Divider orientation="center">文档信息</Divider>
                        <List
                            split={false}
                            bordered={false}
                            dataSource={docInfo}
                            renderItem={(item) => (
                                <List.Item>
                                    <Typography.Text type="secondary">{item.key}</Typography.Text>{item.value.toString()}
                                </List.Item>
                            )}
                        />

                    </Sider>
                </Layout>
            </Layout>
        </>
    );
};

export default DocumentDetail;