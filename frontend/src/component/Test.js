import React, { useEffect, useState } from 'react';
//import { useNavigate } from 'react-router-dom';
//import styled from 'styled-components';
import Problem from './Problem';
// import Checkbox from './Checkbox';
// import Card from './Card';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Unstable_Grid2';

const Test = () => {
  const problems = [
    ['네트워크', 'TCP와 UDP의 차이', '상', '35명'],
    ['자바스크립트', '호이스팅', '상', '10명'],
    ['네트워크', '3Way-handshaking', '하', '5명'],
    ['자바스크립트', '클러져', '중', '5명'],
    ['React', '가상 돔을 쓰는 이유', '상', '5명'],
    ['네트워크', 'OSI 7계층을 설명하시오', '상', '5명'],
    ['네트워크', 'OSI 7계층을 설명하시오', '상', '5명'],
    ['네트워크', 'OSI 7계층을 설명하시오', '상', '5명'],
  ];
  const [question, setQuestion] = useState("");
  //const navigate = useNavigate();
  
  useEffect(() => {
    const fetchQuestion = async () => {
      
      console.log('api연동성공');
      const response = await fetch(`https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/question.json`);
      // .then((response) => response.json());
      console.log(question);
      if(!response.ok){
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setQuestion(data);
      console.log(data);

    }
    fetchQuestion().catch((err) => {
      console.log(err)
    })
  }, []);


  return (
    <Container>
      {/* 필터 */}
      {/* <div>
        <Checkbox/>
      </div>
      <div>
        <Card/>
      </div> */}
      {/*<div>
      <Grid 
        container 
        spacing={1} 
        columns={{ xs:12 }}
      >
        {question.map((test, index) => {
            return (
              <Grid key={index} item xs={12} sm={6} md={3} onClick={() => alert('야야')}>
                <Problem problem={test} />
              </Grid>
            )
          })}
      </Grid>
      </div>*/}
      <div>
        <Grid 
          container 
          spacing={1} 
          columns={{ xs:12 }}
        >
          {problems.map((problem, index) => {
            return (
              <Grid key={index} item xs={12} sm={6} md={3}>
                <Problem problem={problem} />
                <div> {question.title}</div>
              </Grid>
            )
          })}
        </Grid>
      </div>
    </Container>
  );
};

export default Test;

// const Container = styled.div`
//   display: flex;
//   flex-wrap: wrap;
//   text-align: center;
// `;