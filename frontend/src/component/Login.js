import React, {  useState } from 'react';
import axios from 'axios';
import Layout from "../layout/Layout";
import styled from "styled-components";




const Login = () => {
   
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }

    
    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    }



    const onSubmit = () => {
        axios.post("http://localhost:8080/login", {
            email: email,
            password: password,
        }).then(function (response) {
            console.log(response);
            if(response.status === 200){
                window.location.href="/test";
            }
        }).catch(function (error) {
            alert("이메일 혹은 비밀번호가 잘못 입력되었습니다.");
        });
    }


    return(
        <Layout>
            <Div>
                <InputBoxes>
                    <H4>이메일</H4>
                    <Input
                    type="email"
                    id="email"
                    placeholder="Enter email"
                    value={email}
                    onChange={handleEmailChange}
                    />
                    <H4>비밀번호</H4>
                    <Input
                    type="password"
                    id="password"
                    placeholder="Password"
                    value={password}
                    onChange={handlePasswordChange}
                    />
                    <Button onClick={onSubmit}>로그인</Button>
                </InputBoxes>
            </Div>
      </Layout>
    );


};

const Div = styled.div`
    height: 999px;
    background-color: #e9e9e9;
`

const InputBoxes = styled.div`
    width: 500px;
    height: 600px;
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    padding-top: 120px;
`

const H4 = styled.h4`
    font-weight: bold;
    height: 10px;
`

const Input = styled.input`
    width: 300px;
    height: 30px;
    margin-left: 100px;
    border-color: grey;
    border-width: 1px;
    border-radius: 3px;
    padding-left: 2px;
`

const Button = styled.button`
    width: 305px;
    height: 35px;
    margin-top: 40px;
    margin-left: 100px;
    cursor: pointer;
    background-color: dodgerblue; 
    border-radius: 3px;
    border-width: 2px;
    border-style: solid;
    border-color: dodgerblue;
    color: white;
    font-weight: bold;
`

export default Login;