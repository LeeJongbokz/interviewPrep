import React, { useEffect, useState, useContext, useRef } from 'react';
import AuthContext from '../../store/auth-context';
import { useParams, useNavigate } from 'react-router-dom';
import Typography from '@mui/material/Typography';
import ContainerUI from '../UI/ContainerUI';

import SubmitButtonGroup from './SubmitButtonGroup';
import AnswerField from './AnswerField';

const TestScreen = () => {
  const { subject: testId } = useParams();
  const navigate = useNavigate();
  const authCtx = useContext(AuthContext);

  const [question, setQuestion] = useState('');
  const answerRef = useRef();

  const submitHandler = async () => {
    if (window.confirm('답안을 제출하시겠습니까?')) {
      const bodyData = {
        questionId: testId,
        content: answerRef.current.value.slice(0, 50),
      };
      const response = await fetch(`http://52.202.27.18:8080/answer/`, {
        method: 'POST',
        body: JSON.stringify(bodyData),
        headers: {
          'Content-Type': 'application/json',
          accessToken: authCtx.token,
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

  useEffect(() => {
    const fetchQuestion = async () => {
      const response = await fetch(`http://52.202.27.18:8080/question/single/${testId}`);

      if (!response.ok) {
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.title);
    };
    fetchQuestion().catch(err => {
      console.log(err);
    });
  }, [testId]);

  return (
    <>
      {question && (
        <ContainerUI>
          <Typography variant="h4" gutterBottom>
            Question.
          </Typography>
          <Typography variant="h5" gutterBottom>
            {question}
          </Typography>
          <AnswerField answerRef={answerRef} />
          <SubmitButtonGroup testId={testId} submitHandler={submitHandler} />
        </ContainerUI>
      )}
    </>
  );
};

export default TestScreen;
