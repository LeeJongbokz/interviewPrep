import React, { useRef, useContext } from 'react';
import AuthContext from '../../store/auth-context';
import { useNavigate, Link } from 'react-router-dom';

import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';

import PaperUI from '../UI/PaperUI';
import DividingLine from '../UI/DividingLine';
import IconGroup from './IconGroup';

const Login = () => {
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);

  const formRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();

  const onSubmitHandler = e => {
    e.preventDefault();
    if (formRef.current.reportValidity() === false) {
      return;
    }

    authCtx
      .login(emailRef.current.value, passwordRef.current.value)
      .then(data => {
        if (data.error) {
          throw new Error();
        } else if (data.success === false) {
          alert('인증정보가 올바르지 않습니다.');
          return;
        } else {
          navigate('/');
          return;
        }
      })
      .catch(e => {
        alert('인증이 실패했거나 오류가 발생했습니다!');
      });

    // navigate('/');
    return;
  };

  // function ClickHander() {
  //   const res = API.get(
  //     `oauth/authorize?client_id=7631d083ab97ccae8103b0aed5d67b05&redirect_uri=http://52.3.173.210:8080/oauth2/code/kakao&response_type=code`
  //   );
  //   console.log(res);
  // }
  return (
    <PaperUI title="Login">
      <form onSubmit={onSubmitHandler} ref={formRef} noValidate>
        <TextField
          id="email"
          label="email"
          type="email"
          margin="normal"
          inputRef={emailRef}
          autoComplete="on"
          required
          fullWidth
        />
        <TextField
          id="password"
          label="passwords"
          type="password"
          margin="normal"
          inputRef={passwordRef}
          autoComplete="off"
          required
          fullWidth
        />
        <ButtonGroup fullWidth sx={{ marginTop: 2 }}>
          <Button type="submit" variant="contained">
            로그인
          </Button>
          <Button variant="outlined" component={Link} to="/signup">
            회원가입
          </Button>
        </ButtonGroup>
      </form>
      <DividingLine />
      <IconGroup />
    </PaperUI>
  );
};

export default Login;
