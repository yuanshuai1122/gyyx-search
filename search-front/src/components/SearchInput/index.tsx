import React from 'react';
import Search from "antd/es/input/Search";

const SearchInput = () => {
    return (
        <>
            <Search
                placeholder="请输入关键字"
                enterButton="搜索"
                size="large" />
        </>
    );
};

export default SearchInput;