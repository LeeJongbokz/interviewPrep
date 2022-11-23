import { useState } from 'react';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import Box from '@mui/material/Box';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
// import Grid from '@mui/material/Unstable_Grid2';
const Profile = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfrimPassword] = useState('');
  const [name, setName] = useState('');
  const [open, setOpen] = useState(false);
  // const [loading, setLoading] = useState(true);

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
    console.log(e.target.email.value);
    console.log(e.target.password.value);
    console.log(e.target.confirmpassword.value);
    console.log(e.target.name.value);
    if (!response.ok) {
      alert('오류가 발생했습니다. 다시 시도해주세요!');
      return;
    }
    
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

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const HandleChangeName = () =>{
    alert('닉네임 변경')
  }
  // useEffect(() => {

  //   const fetchMemberData = async () => {
  //     setLoading(true);
  //     const response = await fetch(`https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/answer.json?orderBy=%22testId%22&equalTo="${testId}"`);
  //     //console.log(testId);

  //     if(!response.ok){
  //       throw new Error('Some Thing Went Error');
  //     }
  //     const data = await response.json();
  //     const answers = [];
  //     for( const key in data){
  //       answers.push({
  //         id: key,
  //         answer: data[key].answer
  //       })
  //     }
  //     setAnswerArray(answers);  
  //     //console.log(data);
      
  //   }
  //   fetchAnswer().catch((err) => {
  //     console.log(err)
  //   })
    
  // }, []);

  return (
    <>
      <Typography component="h1" variant="h5" fontWeight="bold">
        계정 관리
      </Typography>
      <Typography component="h5" variant="h6" fontWeight="800" sx={{  marginTop: '20px'}}>
        회원 정보
      </Typography>
      <Card onSubmit={onSubmit} noValidate variant="outlined" sx={{  marginBottom: '20px', padding: '20px'}}>
        <Typography component="h5" fontWeight="800" >
          닉네임
        </Typography>
        <Box display="flex" justifyContent="center" alignItems="center">
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
          <Button type="submit" variant="outlined" label={'margin="normal" '} sx={{  width: '150px' ,height: '54px'}}>
          닉네임 변경
          </Button>
        </Box>
        <Typography component="h5" fontWeight="800" >
          이름
        </Typography>
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
        <Typography component="h5" fontWeight="800" >
          이메일
        </Typography>
        <Box display="flex" justifyContent="center" alignItems="center">
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
          <Button type="submit" variant="outlined" label={'margin="normal" '} sx={{  width: '150px' ,height: '54px'}}>
          이메일 변경
          </Button>
      </Box>
        <Typography component="h5" fontWeight="800" >
          비밀번호
        </Typography>
        <Box display="flex" justifyContent="center" alignItems="center">
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
          <div>
            <Button type="submit" variant="outlined" label={'margin="normal" '} sx={{  width: '150px' ,height: '54px'}} onClick={handleClickOpen}>
            비밀번호 변경
            </Button>
            <Dialog open={open} onClose={handleClose} >
              <DialogTitle fontWeight="800">비밀번호 변경</DialogTitle>
              <DialogContent>
                <Box display="flex" justifyContent="center" alignItems="center" >
                  <DialogContentText fontWeight="600" sx={{  width: '250px'}}>
                    현재 비밀번호
                  </DialogContentText>
                  <TextField
                    id="password"
                    label="password"
                    type="password"
                    margin="normal"
                    name="password"
                    fullWidth
                    value={password}
                    autoFocus
                    variant="standard"
                  />
                </Box>
                <Box display="flex" justifyContent="center" alignItems="center" >
                  <DialogContentText fontWeight="600" sx={{  width: '250px'}}>
                    새 비밀번호
                  </DialogContentText>
                  <TextField
                    id="password"
                    label="password"
                    type="password"
                    margin="normal"
                    name="password"
                    fullWidth
                    value={password}
                    autoFocus
                    variant="standard"
                  />
                </Box>
                <Box display="flex" justifyContent="center" alignItems="center" >
                  <DialogContentText fontWeight="600" sx={{  width: '250px'}}>
                    비밀번호 확인
                  </DialogContentText>
                  <TextField
                    id="confirmPassword"
                    label="confirmPassword"
                    type="password"
                    margin="normal"
                    name="confirmPassword"
                    fullWidth
                    value={confirmPassword} 
                    autoFocus
                    variant="standard"
                  />
                </Box>
              </DialogContent>
              <DialogActions>
                <Button onClick={handleClose}>취소</Button>
                <Button onClick={handleClose} onChange={HandleChangeName}>변경</Button>
              </DialogActions>
            </Dialog>
          </div>
        </Box>
      </Card>
      <Button type="submit" variant="contained" label={'margin="normal"'}>
        저장
      </Button>
    </>
  )
}

export default Profile