import React, {ReactNode} from 'react';
import SearchLayout from "./SearchLayout";
import SearchCard from "./SearchCard";
import SearchInput from "./SearchInput";
import SearchPagination from "./SearchPagination";
const App: React.FC = () => {



    const contents: ReactNode[] = [
        <SearchCard title={"你好"} contents={
            new Map([
            ["key1", "value1"],
            ["key2", "value2"],
            ["key3", "value2"],
            ["key4", "value2"],
            ["key5", "value2"],
            ["key6", "value2"],
            ])
        }/>,
        <SearchCard title={"你好"} contents={
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