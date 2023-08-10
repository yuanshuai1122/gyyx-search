import React, {ReactNode} from 'react';
import {Card, Tag, Typography} from "antd";
import Meta from "antd/es/card/Meta";
import {FolderFilled} from "@ant-design/icons";
import {Link} from "react-router-dom";

const { Text } = Typography;

const contentList = (contentMap: Map<string, string|number>): ReactNode[] => {
    const contentList: ReactNode[] = [];
    contentMap.forEach((value, key) => {
        const pNode: ReactNode =
            <p>
                {<Text type="secondary">{key}</Text>}
                <Text strong>{value}</Text>
            </p>
        contentList.push(pNode)
    })

    return contentList
}


interface Props {
    title: string,
    projectName: string
    id: string,
    contents: Map<string, string|number>
}
const SearchCard  = (props: Props) => {

    const {title, projectName, id, contents} = props

    return (
        <>
            <Card
                bordered={true}
                style={{width: '90%', marginLeft: 50, marginBottom: 30, marginTop: 30, paddingLeft: '5%', paddingRight: '5%'}}
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