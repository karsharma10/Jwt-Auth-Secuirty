import * as React from "react";

export default function Header(props){
    return(
        <header className="App-Header">
            <h1 className = "App-Title">{props.pageTitle}</h1>
        </header>
    )
}