import React, {ReactNode} from 'react';
import {Card, Tag, Typography} from "antd";
import Meta from "antd/es/card/Meta";
import {FolderFilled} from "@ant-design/icons";
import {Link} from "react-router-dom";
import './index.css'

const { Text } = Typography;

const contentList = (contentMap: Map<string, string>): ReactNode[] => {
    const contentList: ReactNode[] = [];
    contentMap.forEach((value, key) => {
        const pNode: ReactNode =
            <p key={key}>
                {<Text type="secondary">{key}</Text>}
                <span className="highlight" dangerouslySetInnerHTML={{__html:value}}/>
            </p>
        contentList.push(pNode)
    })

    return contentList
}


interface Props {
    title: string,
    projectName: string
    id: string,
    contents: Map<string, string>
}
const SearchCard  = (props: Props) => {

    const {title, projectName, id, contents} = props

    return (
        <>
            <Card
                bordered={true}
                style={{width: '100%', marginBottom: 20}}
                hoverable={true}
            >
                <Meta
                    style={{textAlign: 'center'}}
                    title={
                        <Link to={id}>
                            {title}
                        </Link>
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