import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {getDocInfo} from "../../services/search";
import {DocDetail} from "../../types/search";

const DocumentDetail = () => {

    const { id} = useParams();
    const [docDetail, setDocDetail] = useState<DocDetail>();

    useEffect(()=> {
        getDocInfo({
            channel: 'test-job',
            id: id ?? ""
        }).then(response => {
            console.log(response.data)
            setDocDetail(response.data)
        }).catch(error => {
            console.log(error)
        })

    }, [])

    return (
        <div>
            <h1>这是详细: {id}</h1>
            <div>{docDetail?.content}</div>
            <div>{docDetail?.lastAccessed}</div>
        </div>
    );
};

export default DocumentDetail;