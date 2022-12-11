import { useState, useEffect } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import TableCell from '@mui/material/TableCell';
import LoadingSpinner from '../UI/LoadingSpinner';

import useHttpRequest from '../../hook/use-http';
import ContainerUI from '../../component/UI/ContainerUI';
import MemberList from './MemberList';

export const TableCellColumnHead = ({ body }) => {
  return <TableCell component="th" scope="column" sx={{ color: 'white' }}>{body}</TableCell>;
}
const Profile = () => {
  const [memberInfo, setMemberInfo] = useState([]);

  // const [open, setOpen] = useState(false);
  // const [newNickname, setnewNickname] = useState(false);
  // const [value, setValue] = useState(0);
  const { isLoading, sendGetRequest } = useHttpRequest();

  // const handleChange = (event, newValue) => {
  //   setValue(newValue);
  // };

  // const onSubmitNickname = async e => {
  //   e.preventDefault();

  //   const bodyData = {
  //     name: name,
  //     nickname: nickname,
  //     email: email,
  //     password: password,
  //     confirmPassword: confirmPassword,
  //   };
  //   const response = await fetch(`http://52.202.27.18:8080/members/nickname/change`, {
  //     method: 'PUT',
  //     body: JSON.stringify(bodyData),
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   });
  //   console.log('성공!')
  //   console.log(bodyData.nickname);
  //   if (!response.ok) {
  //     alert('오류가 발생했습니다. 다시 시도해주세요!');
  //     return;
  //   }

  //   // try {
  //   //   const res = API.post('members/signup');
  //   //   console.log(res);
  //   //   console.log(HandleChange(e));
  //   // } catch (err) {
  //   //   console.log(err);
  //   // }

  // };

  // const onSubmitEmail = async e => {
  //   e.preventDefault();

  //   const bodyData = {
  //     name: name,
  //     nickname: nickname,
  //     email: email,
  //     password: password,
  //     confirmPassword: confirmPassword,
  //   };
  //   const response = await fetch(`http://52.202.27.18:8080/members/email/change`, {
  //     method: 'PUT',
  //     body: JSON.stringify(bodyData),
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   });
  //   console.log('성공!')
  //   console.log(bodyData.email);
  //   if (!response.ok) {
  //     alert('오류가 발생했습니다. 다시 시도해주세요!');
  //     return;
  //   }

  //   // try {
  //   //   const res = API.post('members/signup');
  //   //   console.log(res);
  //   //   console.log(HandleChange(e));
  //   // } catch (err) {
  //   //   console.log(err);
  //   // }

  // };

  // function HandleChange(e) {
  //   //  console.log(onSubmit(e));
  //   switch (e.target.name) {
  //     case 'name':
  //       setName(e.target.value);
  //       break;
  //     case 'nickname':
  //       setNickname(e.target.value);
  //       console.log(e.target.value);
  //       break;
  //     case 'confirmpassword':
  //       setConfrimPassword(e.target.value);
  //       break;
  //     case 'password':
  //       setPassword(e.target.value);
  //       break;
  //     case 'email':
  //       setEmail(e.target.value);
  //       break;
  //     default:
  //   }
  // }

  // function handleRecentProblem(e, newValue) {
  //   setValue(newValue);
  //   console.log(newValue);
  // }

  // const handleClickOpen = (e) => {

  //   switch (e.target.name) {
  //     case 'nicknameChange':
  //       setNickname(e.target.value);
  //       setnewNickname(true);
  //       console.log('닉네임부분');
  //       break;
  //     case 'passwordChange':
  //       setPassword(e.target.value);
  //       console.log('비밀번호 부분')
  //       setOpen(true);
  //       break;
  //     case 'email':
  //       setEmail(e.target.value);
  //       setOpen(true);
  //       break;
  //     default:
  //   }
  //   setOpen(true);
  // };

  // const handleClose = () => {
  //   setOpen(false);
  // };

  // const HandleChangeName = () => {
  //   alert('닉네임 변경')
  // }

  useEffect(() => {
    const memberInfoHandler = data => {
      setMemberInfo(data.data.content);
    };
    sendGetRequest(`/members/userInfo` , memberInfoHandler);
  }, [sendGetRequest]);

  return (
    <ContainerUI>
      <Typography component="h1" variant="h5" fontWeight="bold">
        계정 관리
      </Typography>
      <Typography component="h5" variant="h6" fontWeight="800" sx={{ marginTop: '20px' }}>
        회원 정보
      </Typography>
      <Card noValidate variant="outlined" sx={{ marginBottom: '20px', padding: '20px' }}>
        {isLoading && <LoadingSpinner />}
        {!isLoading && <MemberList memberInfo = {memberInfo} />}
      </Card>
      <Button type="submit" variant="contained" label={'margin="normal"'}>
        저장
      </Button>
    </ContainerUI>
  )
}

export default Profile;