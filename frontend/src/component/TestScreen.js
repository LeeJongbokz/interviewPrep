import React, { useEffect, useState, useContext } from 'react';
import AuthContext from '../store/auth-context';

import { useParams, useNavigate } from 'react-router-dom';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import ContainerUI from './UI/ContainerUI';

const TestScreen = () => {
  const { subject: testId } = useParams();
  const navigate = useNavigate();

  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');

  const authCtx = useContext(AuthContext);

  const changeHandler = e => {
    setAnswer(e.target.value);
  };

  const submitHandler = async () => {
    if (window.confirm('답안을 제출하시겠습니까?')) {
      const bodyData = {
        questionId: testId,
        content: answer,
      };
      const response = await fetch(`http://52.202.27.18:8080/answer/`, {
        method: 'POST',
        body: JSON.stringify(bodyData),
        headers: {
          'Content-Type': 'application/json',
          accessToken: authCtx.token
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
  }, []);

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
          <FormControl
            margin="dense"
            fullWidth
            variant="standard"
            sx={{ marginTop: '20px', marginBottom: '20px' }}
          >
            <TextField
              id="answer"
              multiline
              rows={5}
              value={answer}
              onChange={changeHandler}
              placeholder="답을 입력해주세요"
            />
          </FormControl>
          <Button variant="contained" onClick={submitHandler} m={1}>
            제출하기
          </Button>
        </ContainerUI>
      )}
    </>
  );
};

export default TestScreen;
