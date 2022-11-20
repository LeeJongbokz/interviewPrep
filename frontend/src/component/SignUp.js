import { useState } from 'react';

import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

// import * as API from '../utils/api';import { flexbox } from '@mui/system';
// import styled from 'styled-components';
// import * as API from '../utils/api';
import PaperUI from './UI/PaperUI';

const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfrimPassword] = useState('');
  const [name, setName] = useState('');

  const onSubmit = async e => {
    e.preventDefault();
    if (password !== confirmPassword) {
      return alert('비밀번호화 비밀번호 확인이 같지 않습니다. 다시 확인해주세요!');
    }
    const bodyData = {
      name: name,
      email: email,
      password: password,
      confirmPassword: confirmPassword,
    };
    const response = await fetch(`http://52.202.27.18:8080/members/signup`, {
      method: 'POST',
      body: JSON.stringify(bodyData),
      headers: {
        'Content-Type': 'application/json',
      },
    });
    if (!response.ok) {
      alert('오류가 발생했습니다. 다시 시도해주세요!');
      return;
    }
    console.log(e.target.email.value);
    console.log(e.target.password.value);
    console.log(e.target.confirmpassword.value);
    console.log(e.target.name.value);

    // try {
    //   const res = API.post('members/signup');
    //   console.log(res);
    //   console.log(HandleChange(e));
    // } catch (err) {
    //   console.log(err);
    // }
  };

  function HandleChange(e) {
    //  console.log(onSubmit(e));
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
    <PaperUI title="Sign Up">
      <form onSubmit={onSubmit} noValidate>
        <TextField
          id="name"
          label="name"
          name="name"
          type="text"
          margin="normal"
          required
          fullWidth
          value={name}
          onChange={HandleChange}
        />
        <TextField
          id="email"
          label="email"
          type="email"
          margin="normal"
          name="email"
          required
          fullWidth
          value={email}
          onChange={e => HandleChange(e)}
        />
        <TextField
          id="password"
          label="password"
          type="password"
          margin="normal"
          name="password"
          required
          fullWidth
          value={password}
          onChange={e => HandleChange(e)}
        />
        <TextField
          id="confirmpassword"
          label="confirmpassword"
          type="password"
          margin="normal"
          name="confirmpassword"
          required
          fullWidth
          value={confirmPassword}
          onChange={e => HandleChange(e)}
        />
        <Button type="submit" variant="contained" label={'margin="normal"'}>
          회원가입
        </Button>
      </form>
    </PaperUI>
  );
};

export default SignUp;

// const InputBoxes = styled.form`
//   width: 500px;
//   height: 600px;
//   display: flex;
//   flex-direction: column;
//   margin: 0 auto;
//   padding-top: 50px;
//   text-align: center;
// `;

// const H4 = styled.h4`
//   font-weight: bold;
//   height: 10px;
// `;

// const Input = styled.input`
//   width: 300px;
//   height: 30px;
//   margin-left: 100px;
//   border-color: grey;
//   border-width: 1px;
//   border-radius: 3px;
//   padding-left: 2px;
// `;

// const Button = styled.button`
//   width: 305px;
//   height: 35px;
//   margin-top: ${props => (props.margin ? props.margin : '50px')};
//   margin-left: 100px;
//   cursor: pointer;
//   background-color: ${props => (props.color ? props.color : 'dodgerblue')};
//   border-radius: 3px;
//   border-width: 2px;
//   border-style: solid;
//   border-color: ${props => (props.color ? props.color : 'dodgerblue')};
//   color: white;
//   font-weight: bold;
// `;
