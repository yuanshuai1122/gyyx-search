import React, {useState} from 'react';
import { Layout, Menu, Radio, RadioChangeEvent, theme } from 'antd';
import RedisTree from "../../pages/RedisTree";
import './index.css'

const { Header, Content, Footer, Sider } = Layout;

const DataLayout = () => {

    const [value, setValue] = useState(1);

    const onChange = (e: RadioChangeEvent) => {
        console.log('radio checked', e.target.value);
        setValue(e.target.value);
    };


    const {
        token: { colorBgContainer },
    } = theme.useToken();

    return (
        <Layout hasSider>
            <Sider
                style={{
                    overflow: 'auto',
                    height: '100vh',
                    position: 'fixed',
                    left: 0,
                    top: 0,
                    bottom: 0,
                }}
            >
                <div className="logo-vertical">
                    <img className="logo-vertical-img" src="/gyyx_logo.png"/>
                </div>
                <RedisTree/>
            </Sider>
            <Layout className="site-layout" style={{ marginLeft: 200 }}>
                <Header style={{ background: colorBgContainer }}>
                    <Radio.Group onChange={onChange} value={value}>
                        <Radio value={1}>Redis</Radio>
                        <Radio value={2}>Memcache</Radio>
                    </Radio.Group>
                </Header>
                <Content style={{ margin: '24px 16px 0', overflow: 'initial' }}>
                    <div style={{ padding: 24, textAlign: 'center', background: colorBgContainer }}>
                        <p>这里是value的内容</p>
                        {
                            // indicates very long content
                            Array.from({ length: 100 }, (_, index) => (
                                <React.Fragment key={index}>
                                    {index % 20 === 0 && index ? 'more' : '...'}
                                    <br />
                                </React.Fragment>
                            ))
                        }
                    </div>
                </Content>
                <Footer style={{ textAlign: 'center' }}>Ant Design ©2023 Created by Ant UED</Footer>
            </Layout>
        </Layout>
    );
};

export default DataLayout;