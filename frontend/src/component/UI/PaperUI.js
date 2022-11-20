import React from 'react';

import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

const PaperUI = props => {
  return (
    <Container maxWidth="sm" sx={{ marginTop: { sm: '100px' } }}>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
        }}
      >
        <Grid padding={4} component={Paper} square>
          <Typography align="center" component="h1" variant="h5">
            {props.title}
          </Typography>
          {props.children}
        </Grid>
      </Box>
    </Container>
  );
};

export default PaperUI;
