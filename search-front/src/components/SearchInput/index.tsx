import React from 'react';
import Search from "antd/es/input/Search";
import {useNavigate} from "react-router-dom";

type Props = {
    getKeywords: (value: string, flag: number) => void;
};
const SearchInput: React.FC<Props> = ({getKeywords}) => {

    const navigate = useNavigate();

    const handleInputChange = (value: string) => {
        getKeywords(value, 0)
        // 重定向到首页
        //navigate('/');
    }

    return (
        <>
            <Search
                allowClear
                bordered={true}
                placeholder="请输入关键字"
                enterButton="搜索"
                size="large"
                onSearch={handleInputChange}
            />
        </>
    );
};

export default SearchInput;