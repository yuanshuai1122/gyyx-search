import React, {useState} from 'react';
import {Route, Routes} from 'react-router-dom';
import Index from "./pages/Home";
import {Content, Footer, Header} from "antd/es/layout/layout";
import SearchInput from "./components/SearchInput";
import {Divider, Layout, List, Space, Typography} from "antd";
import Sider from "antd/es/layout/Sider";
import './App.css'
import DocumentDetail from "./pages/DocumentDetail";
import {SEARCH_CHANNEL} from "./constants";
import {SiderInfo} from "./types/search";

const headerStyle: React.CSSProperties = {
    textAlign: 'center',
    display: 'flex',
    alignItems: 'center',
    height: 120,
    paddingInline: 50,
    backgroundColor: 'white',
};

const footerStyle: React.CSSProperties = {
    textAlign: 'center',
    height: 200,
    color: 'red',
    backgroundColor: 'red',
};

const siderStyle: React.CSSProperties = {
    textAlign: 'left',
    minHeight: 800,
    color: 'white',
    backgroundColor: 'white',
    paddingLeft: 40,
    paddingRight: '5%'

};
const contentStyle: React.CSSProperties = {
    minHeight: 800,
    paddingTop: 25,
    paddingLeft: '5%',
    paddingRight: '5%',
};



/**
 * 文档信息Sider
 * @param docInfo 文档信息
 */
const docInfoSider = (docInfo: SiderInfo[] | undefined) => {
    return (
        <>
            <Divider orientation="center">文档信息</Divider>
            <List
                split={false}
                bordered={false}
                dataSource={docInfo ?? []}
                renderItem={(item) => (
                    <List.Item>
                        <Typography.Text type="secondary">{item.key}</Typography.Text>{item.value.toString()}
                    </List.Item>
                )}
            />
        </>
    )
}

/**
 * 搜索选项sider
 * @param info 信息
 */
const searchOptionSider = (info: string) => {
    return (
        <>
            <Divider orientation="center">搜索选项</Divider>
            <div>{info}</div>
        </>
    )
}

const App = () => {

    const [keywordInfo, setKeywordInfo] = useState<string>('');
    const [docInfo, setDocInfo] = useState<SiderInfo[]>()
    const [flag, setFlag] = useState<number>(0);

    const getKeywords = (keywords: string, flag: number) => {
        setKeywordInfo(keywords)
        setFlag(flag)
    }

    const getDocPanel = (infos: SiderInfo[], flag: number): void => {
        setDocInfo(infos)
        setFlag(flag)
    }

    return (
        <>
            <Space direction="vertical" style={{ width: '100%' }} size={[0, 48]}>
                <Layout>
                    <Header className={'layout-header'} style={headerStyle}>
                        <SearchInput getKeywords={getKeywords}/>
                    </Header>
                    <Layout hasSider>
                        <Content className={'layout-content'} style={contentStyle}>
                            <Routes>
                                <Route
                                    path="/"
                                    element={
                                    <Index
                                    channel={SEARCH_CHANNEL.CODE}
                                    keywords={keywordInfo}
                                    />}
                                ></Route>
                                <Route
                                    path="/:id"
                                    element={
                                    <DocumentDetail
                                        getDocPanel={getDocPanel}
                                    />
                                }></Route>
                            </Routes>
                        </Content>
                        <Sider
                            width={400}
                            style={siderStyle}>
                            {flag == 1 ? docInfoSider(docInfo) : searchOptionSider("这里是首页搜索信息")}

                        </Sider>
                    </Layout>
                    <Footer style={footerStyle}>
                        全局footer
                    </Footer>
                </Layout>
            </Space>
        </>
    );
};

export default App;