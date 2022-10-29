import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { subjects } from '../utils/data';
import * as API from '../utils/api';
import Container from "@mui/material/Container";
import FormControl from "@mui/material/FormControl"
import Input from "@mui/material/Input"
import InputLabel from "@mui/material/Input"
import Typography from "@mui/material/Typography"
import Button from "@mui/material/Button"

const TestScreen = () => {
  const [questions, setQuestions] = useState([]);
  const [answerDTOs] = useState([]);

  const location = useLocation();
  const paths = location.pathname.split('/');
  let type = paths[2];

  let subject = subjects.map(subject => {
    return subject[0] === paths[2] ? subject[1] : '';
  });

  const handleChange = (e, id) => {
    let answerDTO = {
      content: e.target.value,
      questionId: id,
    };
    answerDTOs[id - 1] = answerDTO;
  };

  const onSubmit = () => {
    if (window.confirm('답안을 제출하시겠습니까?')) {
      try {
        const res = API.post('answer', {
          answers: answerDTOs,
        });
        if (res.status === 200) {
          alert('답안 제출이 완료되었습니다.');
          // window.location.href="/test";
        }
      } catch (error) {
        alert('답안이 제출되지 않았습니다.');
      }
    }
  };

  useEffect(() => {
      // try {
      //   const res = API.get('question/' + type, {});
      //   if (res.status === 200) {
      //     if (questions.length === 0) {
      //       setQuestions(res.data);
      //     }
      //   }
      // } catch (err) {
      //   console.log(err);
      // }
      console.log(type);
      setQuestions([{title:"title"},{title:"titie12"}]);
  }, []);

  return (
    <Container>
      <Typography variant="h3" gutterBottom>
        {subject} 테스트
      </Typography>
        {questions.map((question, index) => (
          <FormControl margin='dense' fullWidth key={index} variant="standard"> 
            <InputLabel htmlFor="component-simple">
              {`${index+1}. ${question.title}`}
            </InputLabel>
            <Input
              id={`question_${index}`}
              onChange={e => handleChange(e, question.id)} 
              multiline
              placeholder="답을 입력해주세요"
            />
          </FormControl>
        ))}
      <Button 
        size="small"
        variant="contained" 
        onClick={onSubmit}
      >제출하기</Button>
    </Container>
  );
};

export default TestScreen;
