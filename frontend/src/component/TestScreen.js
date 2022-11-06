import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Container from "@mui/material/Container";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import PaperUI from './UI/PaperUI';

const TestScreen = () => {
  const { subject:testId } = useParams();
  const navigate = useNavigate();

  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");

  const changeHandler = (e) => {
    setAnswer(e.target.value);
  };

  const submitHandler = async () => {
    if (window.confirm('답안을 제출하시겠습니까?')) {
      const bodyData = {
        questionId: testId,
        content: answer
      }
      const response = await fetch('http://52.202.27.18:8080/answer', {
        method: 'POST',
        body: JSON.stringify(bodyData), 
        headers: {
          'Content-Type' : 'application/json'
        }
      })
      if(!response.ok){
        alert("오류가 발생했습니다. 다시 시도해주세요!")
        return;
      }     
      navigate('/test');
      return; 
    }
  };

  useEffect(() => {
    const fetchQuestion = async () => {
      const response = await fetch(`http://52.202.27.18:8080/answer/single/${testId}`);

      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.title);  
    }
    fetchQuestion().catch((err) => {
      console.log(err);
    })
  }, []);
      
  return (
    <Container maxWidth="sm">
      { question && <PaperUI>
        <Typography variant="h3" gutterBottom>
          
        </Typography>
        <FormControl margin='dense' fullWidth variant="standard"> 
          <TextField id="answer"
            multiline
            label={`문제 : ${question}`}
            value={answer}
            onChange={changeHandler} 
            placeholder="답을 입력해주세요"
          />
        </FormControl>
        <Button 
          size="small"
          variant="contained" 
          onClick={submitHandler}
        >제출하기</Button>
      </PaperUI> }
    </Container>
  );
};

export default TestScreen;
