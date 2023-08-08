import React from 'react';
import {Pagination} from "antd";


interface Props {
    defaultCurrent: number,
    total: number
}
const SearchPagination = (props: Props) => {

    const {defaultCurrent, total} = props

    return (
        <>
            <Pagination defaultCurrent={defaultCurrent} total={total} />;
        </>
    );
};

export default SearchPagination;