import React from 'react';
import Search from "antd/es/input/Search";

const SearchInput = () => {
    return (
        <>
            <Search placeholder="input search text" enterButton="搜索" size="large" loading />
        </>
    );
};

export default SearchInput;