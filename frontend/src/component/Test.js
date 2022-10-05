import React from 'react';
import Layout from '../layout/Layout';
import styled from 'styled-components';

import Problem from './Problem';
const Test = () => {
  const problems = [
    ['네트워크', 'TCP와 UDP의 차이', '상', '35명'],
    ['자바스크립트', '호이스팅', '상', '10명'],
    ['네트워크', '3Way-handshaking', '하', '5명'],
    ['자바스크립트', '클러져', '중', '5명'],
    ['인성문제', '당신의 장단점은', '중', '15명'],
    ['React', '가상 돔을 쓰는 이유', '상', '5명'],
    ['네트워크', 'OSI 7계층을 설명하시오', '상', '5명'],
  ];

  return (
    <Layout>
      <Table>
        <Thead>
          <TRow>
            <Tcol>과목</Tcol>
            <Tcol>문제 이름</Tcol>
            <Tcol>문제 난이도</Tcol>
            <Tcol>푼 사람 수</Tcol>
          </TRow>
        </Thead>
        {problems.map((problem, index) => {
          return <Problem key={index} problem={problem} />;
        })}
      </Table>
    </Layout>
  );
};

const TRow = styled.tr`
  display: flex;
  justify-content: space-between;
  width: 100%;
  min-width: 150px;
`;
const Tcol = styled.th`
  display: flex;
  flex: 1 1 0%;
  justify-content: center;
`;
const Thead = styled.thead`
  display: flex;
  padding-left: 0.5rem;
  padding-right: 0.5rem;
  padding-top: 1rem;
  padding-bottom: 1rem;
  width: 100%;
`;

const Table = styled.table`
  background-color: #e5e7eb;
  width: 100%;
  border-style: solid;
`;

export default Test;
