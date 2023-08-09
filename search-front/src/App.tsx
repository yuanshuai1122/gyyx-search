import React from 'react';
import {Route, Routes} from 'react-router-dom';
import Index from "./pages/Index";
import {Header, Footer} from "antd/es/layout/layout";
import SearchInput from "./components/SearchInput";

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
    return (
        <>
            <Header style={headerStyle}>
                <SearchInput/>
            </Header>
            <Routes>
                <Route path="/" element={<Index />}></Route>
            </Routes>
            <Footer
                style={footerStyle}>
                2312321321
            </Footer>
        </>
    );
};

export default App;