import { useState } from 'react';
import styled from 'styled-components';
import * as API from '../utils/api';
const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfrimPassword] = useState('');
  const [name, setName] = useState('');
  const onSubmit = e => {
    e.preventDefault();
    console.log(e.target.email.value);
    console.log(e.target.password.value);
    console.log(e.target.confirmpassword.value);
    console.log(e.target.name.value);

    try {
      const res = API.post('members/signup');
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };

  function HandleChange(e) {
    switch (e.target.name) {
      case 'name':
        setName(e.target.value);
        break;
      case 'confirmpassword':
        setConfrimPassword(e.target.value);
        break;
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
        <H4>이름</H4>
        <Input type="text" placeholder="Enter Name" name="name" id="name" defaultValue={name} />

        <H4>이메일</H4>
        <Input
          type="email"
          id="email"
          name="email"
          placeholder="Enter email"
          defaultValue={email}
        />
        <H4>비밀번호</H4>
        <Input
          type="password"
          id="password"
          name="password"
          placeholder="Password"
          defaultValue={password}
        />
        <H4>비밀번호확인</H4>
        <Input
          type="password"
          id="confirmpassword"
          name="confirmpassword"
          placeholder="Password"
          defaultValue={confirmPassword}
        />

        <Button margin="30px" color="skyblue">
          회원가입
        </Button>
      </InputBoxes>
    </>
  );
};

export default SignUp;

const InputBoxes = styled.form`
  width: 500px;
  height: 600px;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding-top: 50px;
  text-align: center;
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
