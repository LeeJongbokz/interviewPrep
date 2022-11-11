import React, { useRef, useContext } from 'react';
import AuthContext from '../store/auth-context';
import { useNavigate, Link } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import Divider from '@mui/material/Divider';
import Paper from '@mui/material/Paper';

// import * as API from '../utils/api';

const Login = () => {
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext)

  const formRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();

  const onSubmitHandler = e => {
    e.preventDefault();
    if(formRef.current.reportValidity() === false) {
      return
    }

    authCtx.login(emailRef.current.value);
    navigate("/");
    return;
    // try {
    //   const res = API.post('login', {
    //     email,
    //     password,
    //   });
    //   if (res.status === 200) {
    //     window.location.href = '/test';
    //   }
    // } catch (error) {
    //   alert('이메일 혹은 비밀번호가 잘못 입력되었습니다.');
    // }
  };

  // function ClickHander() {
  //   const res = API.get(
  //     `oauth/authorize?client_id=7631d083ab97ccae8103b0aed5d67b05&redirect_uri=http://52.3.173.210:8080/oauth2/code/kakao&response_type=code`
  //   );
  //   console.log(res);
  // }
  return (
    <Container maxWidth="sm">
      <Grid padding={4} component={Paper} square>
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
          }}
        >
          <Typography align="center" component="h1" variant="h5">
            Login
          </Typography>
          <form onSubmit={onSubmitHandler} ref={formRef} noValidate>
            <TextField
              id="email"
              label="email"
              type="email"
              margin="normal"
              inputRef={emailRef}
              required
              fullWidth
            />
            <TextField
              id="password"
              label="passwords"
              type="password"
              margin="normal"
              inputRef={passwordRef}
              required
              fullWidth
            />
            <ButtonGroup fullWidth>
              <Button type="submit" variant="contained">로그인</Button>
              <Button variant="outlined" component={Link} to="/signup">회원가입</Button>
            </ButtonGroup>
          </form>
          <Divider variant="middle" />
          <Stack
            sx={{
              dislpay: 'flex',
              flexDirection: 'column',
            }}
            mt={5}
            spacing={1}
          >
            {/* <Button variant="outlined">kakao_login</Button>
            <Button variant="outlined">naver_login</Button>
            <Button variant="outlined">google_login</Button> */}
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

export default Login;
