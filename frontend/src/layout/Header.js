import Box from '@mui/material/box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';

import { Typography } from '@mui/material';

const Header = () => {
  return (
    <>
      <Box sx={{ flwxGrow: 1}}>
        <AppBar position="static">
          <Toolbar>
            {/* <MenuIcon /> */}
            <Typography 
              noWrap
              variant="h6" 
              component="a" 
              href="/"
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
            <Button color="inherit" href="/login">Login</Button>
          </Toolbar>
        </AppBar>
      </Box>
    </>
  );
};

export default Header;
