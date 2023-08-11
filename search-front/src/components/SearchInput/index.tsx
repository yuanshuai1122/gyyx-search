import React from 'react';
import Search from "antd/es/input/Search";

type Props = {
    getKeywords: (value: string) => void;
};
const SearchInput: React.FC<Props> = ({getKeywords}) => {

    // const [input, setInput] = useState<string>("");
    //
    // const changeHandle = (event: any) => {
    //     console.log(event.target.value)
    //     setInput(event.target.value)
    // }


    return (
        <>
            <Search
                allowClear
                bordered={true}
                placeholder="请输入关键字"
                enterButton="搜索"
                size="large"
                // onChange={changeHandle}
                onSearch={getKeywords}
            />
        </>
    );
};

export default SearchInput;