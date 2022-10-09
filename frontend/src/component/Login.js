import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import * as API from '../utils/api';
const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const onSubmit = e => {
    e.preventDefault();
    try {
      const res = API.post('login', {
        email,
        password,
      });
      if (res.status === 200) {
        window.location.href = '/test';
      }
    } catch (error) {
      alert('이메일 혹은 비밀번호가 잘못 입력되었습니다.');
    }
  };
  function HandleChange(e) {
    switch (e.target.id) {
      case 'password':
        setPassword(e.target.value);
        break;
      case 'email':
        setEmail(e.target.value);
        break;
      default:
    }
  }
  return (
    <>
      <InputBoxes onChange={e => HandleChange(e)} onSubmit={e => onSubmit(e)}>
        <H4>이메일</H4>
        <Input type="email" id="email" placeholder="Enter email" defaultValue={email} />
        <H4>비밀번호</H4>
        <Input type="password" id="password" placeholder="Password" defaultValue={password} />
        <Button>로그인</Button>
        <Button onClick={() => navigate('/signup')} margin="30px" color="skyblue">
          회원가입
        </Button>
        <SNSButton back={'/kakao_login.png'}></SNSButton>
        <SNSButton back={'/naver_login.png'}></SNSButton>
        <SNSButton back={'/google_login.png'}></SNSButton>
      </InputBoxes>
    </>
  );
};

const InputBoxes = styled.form`
  width: 500px;
  display: flex;
  flex-direction: column;
  padding-top: 50px;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
`;

const H4 = styled.span`
  font-weight: bold;
  text-align: center;
  margin: 10px;
`;

const Input = styled.input`
  width: 250px;
  height: 30px;
  border-color: grey;
  border-width: 1px;
  border-radius: 3px;
  padding-left: 2px;
`;

const Button = styled.button`
  width: 200px;
  height: 35px;
  margin-top: ${props => (props.margin ? props.margin : '50px')};
  cursor: pointer;
  background-color: ${props => (props.color ? props.color : 'dodgerblue')};
  border-radius: 3px;
  border-width: 2px;
  border-style: solid;
  border-color: ${props => (props.color ? props.color : 'dodgerblue')};
  color: white;
  font-weight: bold;
  background-image: ${props => (props.back ? props.back : '')};
`;
const SNSButton = styled.div`
  background-image: url(${props => (props.back ? props.back : '')});
  width: 200px;
  height: 50px;
  margin-top: 30px;
  cursor: pointer;
  background-repeat: no-repeat;
  background-size: 100% 100%;
`;

export default Login;
