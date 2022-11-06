import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
// import { subjects } from '../utils/data';
// import * as API from '../utils/api';
import Container from "@mui/material/Container";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import PaperUI from './UI/PaperUI';

const TestScreen = () => {
  const { subject:testId } = useParams();

  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");

  // const location = useLocation();
  // const paths = location.pathname.split('/');
  // let type = paths[2];

  // let subject = subjects.map(subject => {
  //   return subject[0] === paths[2] ? subject[1] : '';
  // });

  const changeHandler = (e) => {
    setAnswer(e.target.value);
  };

  const submitHandler = async () => {
    if (window.confirm('답안을 제출하시겠습니까?')) {
      const bodyData = {
        testId,
        answer
      }
      const response = await fetch('https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/answer.json', {
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
      // redirect to where??
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

    const fetchQuestion = async () => {
      console.log(testId);
      const response = await fetch(`https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/question/${testId}.json`);

      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data.title);  
    }
    fetchQuestion().catch((err) => {
      console.log(err)
    })

    // setQuestion([{title:"title"},{title:"titie12"}]);
  }, []);
      
  return (
    <Container maxWidth="sm">
      <PaperUI>
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
      </PaperUI>
    </Container>
  );
};

export default TestScreen;
