import { useContext } from 'react';
import AuthContext from '../store/auth-context';
import { Link } from 'react-router-dom';

import Box from '@mui/material/box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Typography } from '@mui/material';

const Header = () => {
  const authCtx = useContext(AuthContext);

  const logoutHandler = () => {
    authCtx.logout();
  };

  const headerButtons = authCtx.isLoggedIn ? (
    <>
      <Button color="inherit" onClick={logoutHandler}>
        Logout
      </Button>
      <Button component={Link} to="/my-page" color="inherit">
        mypage
      </Button>
    </>
  ) : (
    <Button component={Link} to="/login" color="inherit">
      Login
    </Button>
  );

  return (
    <>
      <Box sx={{ flwxGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
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
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              숨터뷰
            </Typography>
            {headerButtons}
          </Toolbar>
        </AppBar>
      </Box>
    </>
  );
};

export default Header;
