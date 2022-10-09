import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import styled from 'styled-components';
import { subjects } from '../utils/data';
import * as API from '../utils/api';
const TestScreen = () => {
  const [questions, setQuestions] = useState([]);
  const [answerDTOs] = useState([]);

  const location = useLocation();
  const paths = location.pathname.split('/');
  let type = paths[2];

  let subject = subjects.map(subject => {
    return subject[0] === paths[2] ? subject[1] : '';
  });

  let num = 1;

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
    try {
      const res = API.get('question/' + type, {});
      if (res.status === 200) {
        if (questions.length === 0) {
          setQuestions(res.data);
        }
      }
    } catch (err) {
      console.log(err);
    }
  }, [questions]);

  return (
    <Div>
      <H3>
        <TitleBox>
          <Title>{subject} 테스트</Title> <Button onClick={onSubmit}>제출하기</Button>
        </TitleBox>
      </H3>
      <UL>
        {questions.map(question => (
          <Question>
            <SubTitle>
              {num++}
              {'. '}
              {question.title}
            </SubTitle>
            <TextArea onChange={e => handleChange(e, question.id)}></TextArea>
          </Question>
        ))}
      </UL>
    </Div>
  );
};

const Div = styled.div`
  background-color: #e9e9e9;
`;

const H3 = styled.h3`
  margin-bottom: 0px;
`;

const TitleBox = styled.div`
  font-size: 24px;
  margin-bottom: 15px;
  display: flex;
  flex-direction: row;
`;

const Title = styled.div`
  width: 300px;
  margin: 0 auto;
  margin-top: 7px;
  margin-left: 610px;
`;

const UL = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-weight: bold;
`;

const Question = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const SubTitle = styled.div`
  margin-bottom: 7px;
`;

const TextArea = styled.textarea`
  width: 700px;
  height: 250px;
  resize: none;
  padding-left: 4px;
  margin-left: 410px;
  margin-bottom: 20px;
  border-color: grey;
  border-radius: 3px;
  font-size: 1em;
`;

const Button = styled.button`
  float: right;
  width: 150px;
  height: 35px;
  margin-right: 150px;
  margin-top: 7px;
  margin-bottom: 30px;
  background-color: dodgerblue;
  border-radius: 3px;
  border-width: 2px;
  border-style: solid;
  border-color: dodgerblue;
  color: white;
  font-weight: bold;
  cursor: pointer;
`;
export default TestScreen;
