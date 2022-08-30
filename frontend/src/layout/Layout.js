import React from "react";
import Header from "./Header";
import styled from "styled-components";

const Main = styled.main`
    height: 999px;
`


const Layout = (props) => {
    return (
        <div>
            <Header />
            <Main>
                {props.children}
            </Main>
        </div>
    )
}

export default Layout;