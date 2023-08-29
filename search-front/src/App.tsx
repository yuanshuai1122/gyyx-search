import React, {useState} from 'react';
import './App.css'
import {Card, Row, Space} from "antd";
import DataLayout from "./components/DataLayout";


const App = () => {

    const redisSelected = () => {
        console.log("12121")
    }


    return (
        <>
            <DataLayout/>
        </>
    );
};

export default App;