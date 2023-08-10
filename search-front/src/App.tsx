import React from 'react';
import {Route, Routes} from 'react-router-dom';
import Index from "./pages/Index";
import {Content, Footer, Header} from "antd/es/layout/layout";
import SearchInput from "./components/SearchInput";
import {Layout, Space} from "antd";
import Sider from "antd/es/layout/Sider";

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

const siderStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: 300,
    color: '#fff',
    backgroundColor: '#3ba0e9',
};

const App = () => {
    return (
        <>

            <Space direction="vertical" style={{ width: '100%' }} size={[0, 48]}>
                <Layout>
                    <Header style={headerStyle}>
                        <SearchInput/>
                    </Header>
                    <Layout hasSider>
                        <Content>
                            <Routes>
                                <Route path="/" element={<Index />}></Route>
                            </Routes>
                        </Content>
                        <Sider
                            width={400}
                            style={siderStyle}>
                            2232
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