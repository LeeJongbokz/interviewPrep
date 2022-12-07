import React, { useState, useContext, useRef } from 'react';
import AuthContext from '../../store/auth-context';
import { useParams, useNavigate } from 'react-router-dom';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';

import QuestionSectionHeader from './QuestionSectionHeader';
import SubmitButtonGroup from './SubmitButtonGroup';
import AnswerField from './AnswerField';

import { BACKEND_BASE_URL } from '../../global_variables';
import QuestionField from './QuestionField';
import DiscussionSection from '../Discussion/DiscussionSection'
import AnswerList from '../Answer/AnswerList';

const TestScreen = () => {
  const { questionId } = useParams();
  const [headerVal, setHeaderVal] = useState(0);

  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);

  const answerRef = useRef();

  const submitHandler = async () => {
    if (window.confirm('답안을 제출하시겠습니까?')) {
      const bodyData = {
        questionId,
        content: answerRef.current.value.slice(0, 50),
      };
      const response = await fetch(`${BACKEND_BASE_URL}/answer/`, {
        method: 'POST',
        body: JSON.stringify(bodyData),
        headers: {
          'Content-Type': 'application/json',
          accessToken: authCtx.token,
          refreshToken: authCtx.refreshToken,
        },
      });
      if (!response.ok) {
        alert('오류가 발생했습니다. 다시 시도해주세요!');
        return;
      }
      navigate('/test');
      return;
    }
  };

  return (
    <Container
      maxWidth={false}
      sx={{ backgroundColor: 'white', height: '100vh', paddingTop: '50px', paddingBottom: '50px' }}
    >
      <Grid container borderTop={1} borderColor={'lightGray'}>
        <Grid item xs={12} md={6} borderRight={1} borderColor={'lightGray'}>
          <QuestionSectionHeader questionId={questionId} headerVal={headerVal} setHeaderVal={setHeaderVal} />
          {headerVal === 0 && <QuestionField questionId={questionId} />}
          {headerVal === 1 && <DiscussionSection />}
          {headerVal === 2 && <AnswerList />}
          {headerVal === 3 && <>SUBMISSION</>}
        </Grid>
        <Grid item xs={12} md={6}>
          <Box padding={2}>
            <AnswerField answerRef={answerRef} />
            <SubmitButtonGroup questionId={questionId} submitHandler={submitHandler} />
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
};

export default TestScreen;
