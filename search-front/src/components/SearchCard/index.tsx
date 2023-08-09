import React, {ReactNode, useEffect} from 'react';
import {Card, Space, Tag} from "antd";

const contentList = (contentMap: Map<string, string|number>): ReactNode[] => {
    const contentList: ReactNode[] = [];
    contentMap.forEach((value, key) => {
        const pNode: ReactNode =
            <p>
                {key + value}
            </p>
        contentList.push(pNode)
    })

    return contentList
}


interface Props {
    title: string,
    contents: Map<string, string|number>
}
const SearchCard  = (props: Props) => {

    const {title, contents} = props

    return (
        <>
            <Card
                title = {title}
                bordered={false} style={{width: '90%', marginLeft: 50, marginBottom: 15, marginTop: 15}}
                hoverable={true}
            >
                {
                    contentList(contents).map(item => item)
                }
            </Card>
        </>
    );
};

export default SearchCard;