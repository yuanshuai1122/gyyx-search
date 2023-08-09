import React, {ReactNode, useEffect} from 'react';
import SearchLayout from "./components/SearchLayout";
import SearchCard from "./components/SearchCard";
import SearchInput from "./components/SearchInput";
import SearchPagination from "./components/SearchPagination";
import {getFilePageList} from "./services/search";
const App: React.FC = () => {


    useEffect(()=> {
        getFilePageList({
            channel: "test-job",
            pageNum: 1,
            pageSize: 10
        }).then(response => {
            console.log(response)
        }).catch(error => {
            console.error(error)
        })


    }, [])




    const contents: ReactNode[] = [
        <SearchCard key={1} title={"你好"} contents={
            new Map([
            ["key1", "value1"],
            ["key2", "value2"],
            ["key3", "value2"],
            ["key4", "value2"],
            ["key5", "value2"],
            ["key6", "value2"],
            ])
        }/>,
        <SearchCard key={2} title={"你好"} contents={
            new Map([
                ["key1", "value1"],
                ["key2", "value2"],
                ["key3", "value2"],
                ["key4", "value2"],
                ["key5", "value2"],
                ["key6", "value2"],
            ])
        }/>

    ];


    return (

        <SearchLayout
            header={
            <SearchInput/>
            }
            contents={
                contents
            }
            footer={
            <SearchPagination defaultCurrent={6} total={500}/>
            }
        />

    );
}

export default App;