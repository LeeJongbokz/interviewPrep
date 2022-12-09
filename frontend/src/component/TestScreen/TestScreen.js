import React from 'react';
import { useParams } from 'react-router-dom';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import AnswerField from './AnswerSection/AnswerForm';

import ExplainSection from './ExplainSection/ExplainSection';

const TestScreen = () => {
  const { questionId } = useParams();

  return (
    <Container maxWidth={false} sx={{ backgroundColor: 'white', paddingTop: '50px' }}>
      <Grid container borderTop={1} borderColor={'lightGray'} minHeight="88vh">
        <Grid item xs={12} md={6} borderRight={1} borderColor={'lightGray'}>
          <ExplainSection questionId={questionId} />
        </Grid>
        <Grid item xs={12} md={6}>
          <AnswerField questionId={questionId} />
        </Grid>
      </Grid>
    </Container>
  );
};

export default TestScreen;
