import React, {ReactNode} from 'react';
import { Layout, Space } from 'antd';
import Search from "antd/es/input/Search";

const { Header, Footer, Sider, Content } = Layout;

const headerStyle: React.CSSProperties = {
    textAlign: 'center',
    color: '#fff',
    height: 64,
    paddingInline: 50,
    backgroundColor: '#7dbcea',
};

const contentStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: 300,
    color: '#fff',
    backgroundColor: '#108ee9',
};

const siderStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: 300,
    color: '#fff',
    backgroundColor: '#3ba0e9',
};

const footerStyle: React.CSSProperties = {
    textAlign: 'center',
    color: '#fff',
    backgroundColor: '#7dbcea',
};

interface Props {
    header?: ReactNode
    content?: ReactNode
    sider?: ReactNode
    footer?: ReactNode
}

const SearchLayout = (props: Props) => {

    const {header, content, sider, footer} = props


    return(
        <Space direction="vertical" style={{ width: '100%' }} size={[0, 48]}>
            <Layout>
                <Header style={headerStyle}>
                    {header}
                </Header>
                <Layout hasSider>
                    <Content style={contentStyle}>
                        {content}
                    </Content>
                    <Sider
                        width={400}
                        style={siderStyle}>
                        {sider}
                    </Sider>
                </Layout>
                <Footer
                    style={footerStyle}>
                    {footer}
                </Footer>
            </Layout>
        </Space>
    );
}
;

export default SearchLayout;