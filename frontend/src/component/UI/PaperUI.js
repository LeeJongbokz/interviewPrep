import React from "react";
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';

const PaperUI = (props) => {
  return (<Grid
  padding={4}
  component={Paper}
  square> 
    {props.children}
  </Grid>)
}

export default PaperUI;