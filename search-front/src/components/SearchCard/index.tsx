import React from 'react';
import {Card} from "antd";

const SearchCard: React.FC  = () => {
    return (
        <>
            <Card
                title="标题"
                bordered={false} style={{width: '90%', marginLeft: 50, marginBottom: 15, marginTop: 15}}
                hoverable={true}
            >
                <p>Card content</p>
                <p>Card content</p>
                <p>Card content</p>
                <p>Card content</p>
                <p>Card content</p>
                <p>Card content</p>
                <p>Card content</p>
            </Card>
        </>
    );
};

export default SearchCard;