import React from 'react';
import SearchLayout from "./SearchLayout";
import SearchCard from "./SearchCard";
import SearchInput from "./SearchInput";
const App: React.FC = () => {




    return (

        <SearchLayout
            header={
            <SearchInput/>
            }
            content={
                <SearchCard/>
            }
        />

    );
}

export default App;