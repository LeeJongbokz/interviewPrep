import React from 'react';
import Layout from "../layout/Layout";
import styled from "styled-components";

const Div = styled.div`
    height: 999px;
    background-color: #e9e9e9;
    padding-top: 5px;
`

const H3 = styled.h3`
    margin-top: 20px;
    margin-bottom: 20px;
`

const SubjectName = styled.div`
    margin-top: 35px;
    height: 30px;
`

const A = styled.a`
    font-size: 20px;
    text-decoration: none;
`

const Test = () => {

    return(
        <Layout>
            <Div>
                <H3>과목 선택</H3>
                <SubjectName><A href="/test/java">자바</A></SubjectName><br/>
                <SubjectName><A href="/test/spring">스프링</A></SubjectName><br/>
                <SubjectName><A href="/test/network">네트워크</A></SubjectName><br/>
                <SubjectName><A href="/test/database">데이터베이스</A></SubjectName><br />
                <SubjectName><A href="/test/javascript">자바스크립트</A></SubjectName>
            </Div>
        </Layout>
    );

}


export default Test;