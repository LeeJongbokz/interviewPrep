import React from 'react';
import { Link } from 'react-router-dom';
import Layout from "../layout/Layout";
import styled from "styled-components";


const Div = styled.div`
    height: 999px;
    background-color: #e9e9e9;
    padding-top: 5px;
`

const H1 = styled.h1`
    margin-top: 20px;
`

const H2 = styled.h2`
    margin-top: 20px;
`

const P = styled.p`
    font-size: 20px;
`

const LinkBox = styled.div`
    margin-top: 10px;    
`
const A = styled.a`
    font-size: 18px;
    text-decoration: none;
`


const Main = () => {


    return(
        <Layout>
            <Div>
                <H1>당신의 기술 면접을 위한 최고의 솔루션</H1>
                <H2>답안을 작성하세요. 최고의 멘토들이 피드백 드립니다.</H2>
                <P>
                    서비스 첫 이용시 누구나 <strong>무료</strong>
                </P>
                <LinkBox><A href="/login">지금 이용하기</A></LinkBox>
            </Div>
        </Layout>
    );



}


export default Main;