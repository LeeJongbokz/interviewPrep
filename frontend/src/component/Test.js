import React from 'react';
//import styled from 'styled-components';
import Problem from './Problem';

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

  return (
    <Container>
      <div justifyContent={"center"}>
      <Grid 
        container 
        spacing={1} 
        columns={{ xs:12 }}
      >
        {problems.map((problem, index) => {
          return (
            <Grid item xs={12} sm={6} md={3}>
              <Problem key={index} problem={problem} />
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