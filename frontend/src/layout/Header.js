import { useContext } from 'react';
import AuthContext from '../store/auth-context';
import { Link } from 'react-router-dom';

import Box from '@mui/material/Box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import { Typography } from '@mui/material';
import companyLogo from '../img/logo.PNG';

const Header = () => {
  const authCtx = useContext(AuthContext);

  const logoutHandler = () => {
    authCtx.logout();
  };

  const headerButtons = authCtx.isLoggedIn ? (
    <>
      <Button color="inherit" onClick={logoutHandler} sx={{  color:'#3A3A3A' ,fontWeight:'bold' ,fontSize:'14px'}} >
        Logout
      </Button>
      <Button component={Link} to="/my-page" sx={{  color:'#3A3A3A' ,fontWeight:'bold' ,fontSize:'14px'}} >
        mypage
      </Button>
    </>
  ) : (
    <Button component={Link} to="/login" sx={{  color:'#3A3A3A' ,fontWeight:'bold' ,fontSize:'14px'}} >
      Login
    </Button>
  );

  const categoryButtons = (
    <>
      <Box
              sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                '& > *': {
                  m: 1,
                },
              }}
            >
              <ButtonGroup variant="none" aria-label="outlined button group" sx={{  color:'#3A3A3A' ,fontWeight:'bold' ,fontSize:'14px'}}>
                <Button component={Link} to="/test" >문제</Button>
                <Button component={Link} to="/exam">모의고사</Button>
              </ButtonGroup>
            </Box>
    </>
  );

  return (
    <>
      <Box sx={{ flwxGrow: 1}}>
        <AppBar position="static" variant="outLine" sx={{boxShadow:'none' }}>
          <Toolbar sx={{  backgroundColor:'white', borderBottom: 'solid 1px #f4f4f4'}}>
            {/* <MenuIcon /> */}
            <Typography
              noWrap
              variant="h6"
              component={Link}
              to="/"
              sx={{
                display: 'flex',
                flexGrow: 1,
                fontWeight: 700,
                textDecoration: 'none',
              }}
            >
              <img src={companyLogo} alt="logo" style={{ height: '30px' }} />
            </Typography>
            {categoryButtons}
            {headerButtons}
          </Toolbar>
        </AppBar>
      </Box>
    </>
  );
};

export default Header;
