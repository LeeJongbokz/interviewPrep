import React, { useState } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = e => {
    console.log(e.target.value);
    setEmail(e.target.value);
  };

  const handlePasswordChange = e => {
    console.log(e.target.value);

    setPassword(e.target.value);
  };

  const onSubmit = () => {
    console.log('여기 잘 넘어감');
    axios
      .post('http://52.3.173.210:8080/login', {
        email: email,
        password: password,
      })
      .then(function (response) {
        console.log(response);
        if (response.status === 200) {
          window.location.href = '/test';
        }
      })
      .catch(function (error) {
        alert('이메일 혹은 비밀번호가 잘못 입력되었습니다.');
      });
  };

  return (
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
      <Button onClick={() => navigate('/singup')} margin="30px" color="skyblue">
        회원가입
      </Button>
    </InputBoxes>
  );
};

const InputBoxes = styled.div`
  width: 500px;
  height: 600px;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding-top: 50px;
`;

const H4 = styled.h4`
  font-weight: bold;
  height: 10px;
`;

const Input = styled.input`
  width: 300px;
  height: 30px;
  margin-left: 100px;
  border-color: grey;
  border-width: 1px;
  border-radius: 3px;
  padding-left: 2px;
`;

const Button = styled.button`
  width: 305px;
  height: 35px;
  margin-top: ${props => (props.margin ? props.margin : '50px')};
  margin-left: 100px;
  cursor: pointer;
  background-color: ${props => (props.color ? props.color : 'dodgerblue')};
  border-radius: 3px;
  border-width: 2px;
  border-style: solid;
  border-color: ${props => (props.color ? props.color : 'dodgerblue')};
  color: white;
  font-weight: bold;
`;

export default Login;
