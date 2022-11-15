import React from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';

const PaperUI = props => {
  return (
    <Container maxWidth="sm" sx={{ marginTop: {sm:"100px"}}}>
      <Grid padding={4} component={Paper} square>
        {props.children}
      </Grid>
    </Container>
  );
};

export default PaperUI;
