import React, {ReactNode, useEffect} from 'react';
import {Avatar, Card, Space, Tag, Typography} from "antd";
import Meta from "antd/es/card/Meta";
import {FolderFilled, TwitterOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";

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
    projectName: string
    contents: Map<string, string|number>
}
const SearchCard  = (props: Props) => {

    const {title, projectName, contents} = props

    return (
        <>
            <Card
                //title = {title}
                bordered={true}
                style={{width: '90%', marginLeft: 50, marginBottom: 15, marginTop: 15}}
                hoverable={true}
            >
                <Meta
                    //avatar={<Avatar src="https://xsgames.co/randomusers/avatar.php?g=pixel" />}
                    title={
                        title
                    }
                    description={
                        <Tag icon={<FolderFilled />} color="geekblue">
                            {projectName}
                        </Tag>
                    }
                />
                {
                    contentList(contents).map(item => item)
                }
            </Card>
        </>
    );
};

export default SearchCard;