import React from 'react';
import SearchLayout from "./SearchLayout";
import SearchCard from "./SearchCard";
const App: React.FC = () => {




    return (

        <SearchLayout
            content={
                <SearchCard/>
            }
        />

    );
}

export default App;