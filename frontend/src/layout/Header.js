import { useState, useContext } from 'react';
import AuthContext from '../store/auth-context';
import { Link } from 'react-router-dom';

import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Typography } from '@mui/material';
import companyLogo from '../img/logo.PNG';

const Header = () => {
  const [value, setValue] = useState(0);
  const authCtx = useContext(AuthContext);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

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
              <Tabs value={value} onChange={handleChange} centered>
                <Tab label="문제" component={Link} to="/test"/>
                <Tab label="모의고사" component={Link} to="/exam"/>
              </Tabs>
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
