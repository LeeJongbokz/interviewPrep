import React from 'react';
import Layout from "../layout/Layout";
import styled from "styled-components";
import { subjects } from '../utils/data';
import { Link } from 'react-router-dom';
const Test = () => {
    return(
        <Layout>
            <Div>
                <H3>과목 선택</H3>
                {subjects.map((subject)=>{
               return <SubjectName><Link style={{  fontSize:"25px",textDecoration: 'inherit'}} to={`/test/${subject[0]}`}>{subject[1]}</Link></SubjectName>
                })}
                
            </Div>
        </Layout>
    );
}
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



export default Test;