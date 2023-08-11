import React, {useState} from 'react';
import {Route, Routes} from 'react-router-dom';
import Home from "./pages/Home/home";
import {Content, Footer, Header} from "antd/es/layout/layout";
import SearchInput from "./components/SearchInput";
import {Layout, Space} from "antd";
import Sider from "antd/es/layout/Sider";
import DocDetail from "./pages/DocumentDetail";
import DocumentDetail from "./pages/DocumentDetail";
import {SEARCH_CHANNEL} from "./constants";

const headerStyle: React.CSSProperties = {
    textAlign: 'center',
    display: 'flex',
    alignItems: 'center',
    color: 'red',
    height: 64,
    paddingInline: 50,
    backgroundColor: 'red',
};

const footerStyle: React.CSSProperties = {
    textAlign: 'center',
    height: 200,
    color: 'red',
    backgroundColor: 'red',
};



const App = () => {

    const [keywordInfo, setKeywordInfo] = useState<string>('');

    const getKeywords = (keywords: string) => {
        console.log(keywords)
        setKeywordInfo(keywords)
    }


    return (
        <>
            <Space direction="vertical" style={{ width: '100%' }} size={[0, 48]}>
                <Layout>
                    <Header style={headerStyle}>
                        <SearchInput getKeywords={getKeywords}/>
                    </Header>
                    <Layout hasSider>
                        <Content>
                            <Routes>
                                <Route path="/" element={
                                    <Home
                                    channel={SEARCH_CHANNEL.CODE}
                                    keywords={keywordInfo}
                                    />}
                                ></Route>
                                <Route path="/:id" element={<DocumentDetail />}></Route>
                            </Routes>
                        </Content>
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