import { useState, useEffect } from 'react';

import { BACKEND_BASE_URL } from '../../global_variables';

import Typography from '@mui/material/Typography';
import { Box } from '@mui/system';

import { getTestQuestion, setTestQuestion } from './TestScreenVariables';

const QuestionField = ({ questionId }) => {
  const [question, setQuestion] = useState(getTestQuestion);

  useEffect(() => {
    const fetchQuestion = async () => {
      const response = await fetch(`${BACKEND_BASE_URL}/question/single/${questionId}`);

      if (!response.ok) {
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.data.title);
      setTestQuestion(data.data.title);
    };
    if (!question) {
      fetchQuestion().catch(err => {
        console.log(err);
      });
    }
  }, [questionId]);

  return (
    <Box marginTop={5}>
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
