import React, { useState } from 'react';
// import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Typography from "@mui/material/Typography";
import Container from '@mui/material/Container';
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack';
import Divider from '@mui/material/Divider';

import Paper from '@mui/material/Paper';

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
  // function ClickHander() {
  //   const res = API.get(
  //     `oauth/authorize?client_id=7631d083ab97ccae8103b0aed5d67b05&redirect_uri=http://52.3.173.210:8080/oauth2/code/kakao&response_type=code`
  //   );
  //   console.log(res);
  // }
  return (
    <Container maxWidth="sm">
      <Grid
        padding={4}
        component={Paper}
        square
      >
        <Box 
          sx={{
            display: "flex",
            flexDirection: 'column',
          }}
          >
          <Typography align='center' component="h1" variant="h5">
            Login
          </Typography>
          <form
            onChange={HandleChange}
            onSubmit={onSubmit}
            noValidate
          >
            <TextField 
              id="email" 
              label="email"
              type="email" 
              margin="normal"
              defaultValue={email}
              required
              fullWidth
            />
            <TextField 
              id="password"
              label="passwords"
              type="password" 
              margin="normal" 
              defaultValue={password} 
              required
              fullWidth 
            />
            <ButtonGroup fullWidth>
            <Button variant="contained">로그인</Button>
            <Button variant="outlined"
              onClick={() => navigate('/signup')}
            >
              회원가입
            </Button>
            </ButtonGroup> 
          </form>
          <Divider variant="middle" />
          <Stack
            sx={{
              dislpay:"flex",
              flexDirection:"column",
            }}
            mt={5}
            spacing={1}
          >
            <Button variant="outlined">kakao_login</Button>
            <Button variant="outlined">naver_login</Button>
            <Button variant="outlined">google_login</Button>
             {/* <Link onClick={ClickHander}> 
               <SNSButton back={'/kakao_login.png'}></SNSButton> 
             </Link> 
            <SNSButton back={'/naver_login.png'}></SNSButton>
            <SNSButton back={'/google_login.png'}></SNSButton> */}
          </Stack>
        </Box>
      </Grid>
    </Container>
  );
};

// const InputBoxes = styled.form`
//   width: 500px;
//   display: flex;
//   flex-direction: column;
//   padding-top: 50px;
//   margin: 0 auto;
//   align-items: center;
//   justify-content: center;
// `;

// const H4 = styled.span`
//   font-weight: bold;
//   text-align: center;
//   margin: 10px;
// `;

// const Input = styled.input`
//   width: 250px;
//   height: 30px;
//   border-color: grey;
//   border-width: 1px;
//   border-radius: 3px;
//   padding-left: 2px;
// `;

// const Button = styled.button`
//   width: 200px;
//   height: 35px;
//   margin-top: ${props => (props.margin ? props.margin : '50px')};
//   cursor: pointer;
//   background-color: ${props => (props.color ? props.color : 'dodgerblue')};
//   border-radius: 3px;
//   border-width: 2px;
//   border-style: solid;
//   border-color: ${props => (props.color ? props.color : 'dodgerblue')};
//   color: white;
//   font-weight: bold;
//   background-image: ${props => (props.back ? props.back : '')};
// `;
// const SNSButton = styled.div`
//   background-image: url(${props => (props.back ? props.back : '')});
//   width: 200px;
//   height: 50px;
//   margin-top: 30px;
//   cursor: pointer;
//   text-align: center;
//   background-repeat: no-repeat;
//   background-size: 100% 100%;
// `;

export default Login;
