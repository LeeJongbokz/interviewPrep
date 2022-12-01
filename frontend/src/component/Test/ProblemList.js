// import React, { useEffect,useState} from 'react';
import Problem from './Problem';
import Grid from '@mui/material/Unstable_Grid2';

const ProblemList = ({question}) => {
  return (
    <Grid container spacing={1} columns={12}>
      {question.map((test, index) => {
        return (
          <Grid        
            item
            key={index}
            xs={12}
            sm={6}
            md={3}
            // sx={{ width: { xs: '100%', sm: '50%', md: '25%' } }}
          >
            <Problem
              sx={{ height: { xs: '250px', sm: '150px', md: '100px' } }}
              key={test.id}
              problem={test}
            />
          </Grid>
        );
      })}
    </Grid>
  );
};

export default ProblemList;
