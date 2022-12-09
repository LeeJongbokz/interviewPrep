import { useState, useEffect } from 'react';

import Typography from '@mui/material/Typography';
import { Box } from '@mui/system';

import { getTestQuestion, setTestQuestion } from '../../TestScreenVariables';
import useHttpRequest from '../../../../hook/use-http';

const QuestionField = ({ questionId }) => {
  const [question, setQuestion] = useState(getTestQuestion);
  const { sendGetRequest } = useHttpRequest();

  useEffect(() => {
    const questionHandler = data => {
      setQuestion(data.data.title);
      setTestQuestion(data.data.title);
    };
    if (!question) {
      sendGetRequest(`/question/single/${questionId}`, questionHandler);
    }
  }, [sendGetRequest, questionId, question]);

  // useEffect(() => {
  //   const fetchQuestion = async () => {
  //     const response = await fetch(`${BACKEND_BASE_URL}/question/single/${questionId}`);

  //     if (!response.ok) {
  //       throw new Error('Some Thing Went Error');
  //     }
  //     const data = await response.json();
  //     setQuestion(data.data.title);
  //     setTestQuestion(data.data.title);
  //   };
  //   if (!question) {
  //     fetchQuestion().catch(err => {
  //       console.log(err);
  //     });
  //   }
  // }, [question, questionId]);

  return (
    <Box marginTop={2}>
      {question && (
        <Typography variant="h4" gutterBottom>
          #{questionId}.
        </Typography>
      )}
      <Typography variant="h5" gutterBottom>
        {question}
      </Typography>
    </Box>
  );
};

export default QuestionField;
