import { useState } from 'react';

import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
// import ButtonGroup from '@mui/material/ButtonGroup';
import Typography from "@mui/material/Typography";
import Container from '@mui/material/Container';
import Box from '@mui/material/Box'
// import Stack from '@mui/material/Stack';
// import Divider from '@mui/material/Divider';

import Paper from '@mui/material/Paper';

// import * as API from '../utils/api';import { flexbox } from '@mui/system';
// import styled from 'styled-components';
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
      console.log(HandleChange(e));
    } catch (err) {
      console.log(err);
    }
  };

  function HandleChange(e) {
    console.log(onSubmit(e));
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
              Sign Up
            </Typography>
            <form
            // onChange={e => HandleChange(e)} 
            noValidate
            >
              <Typography align='center' component="h4" variant="h5">
                이름
              </Typography>
              <TextField 
                id="name" 
                label="name"
                type="text" 
                margin="normal"
                defaultValue={name}
                required
                fullWidth
                placeholder="Enter Name"
              />
              <Typography align='center' component="h4" variant="h5">
                이메일
              </Typography>
              <TextField
                type="email"
                id="email"
                name="email"
                placeholder="email"
                defaultValue={email}
                margin="normal"
                required
                fullWidth
              />
              <Typography align='center' component="h4" variant="h5">
                비밀번호
              </Typography>
              <TextField
                type="password"
                id="password"
                name="password"
                placeholder="Password"
                defaultValue={password}
                margin="normal"
                required
                fullWidth
              />
              <Typography align='center' component="h4" variant="h5">
                비밀번호 확인
              </Typography>
              <TextField
                type="password"
                id="confirmpassword"
                name="confirmpassword"
                placeholder="Password"
                defaultValue={confirmPassword}
                margin="normal"
                required
                fullWidth
              />
              <Button variant="contained" label={'margin="normal"'}>회원가입</Button>
            </form>
          </Box>
        </Grid>
      </Container>
    </>
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
