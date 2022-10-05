import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Main = () => {
  return (
    <>
      <H1>당신의 기술 면접을 위한 최고의 솔루션</H1>
      <H2>답안을 작성하세요. 최고의 멘토들이 피드백 드립니다.</H2>
      <P>
        서비스 첫 이용시 누구나 <strong>무료</strong>
      </P>
      <LinkBox>
        <Link style={{ textDecoration: 'inherit' }} to="/login">
          <TextBox>지금 이용하기</TextBox>
        </Link>
      </LinkBox>
    </>
  );
};
const TextBox = styled.span`
  border-radius: 3px;
  background-color: blue;
  padding: 15px;
  color: white;
  &:hover {
    background-color: skyblue;
    color: yellow;
    font-weight: 500;
    transform: scale(1.4);
  }
`;

const H1 = styled.h1`
  padding-top: 50px;
`;

const H2 = styled.h2`
  margin-top: 20px;
`;

const P = styled.p`
  font-size: 20px;
`;

const LinkBox = styled.div`
  margin-top: 50px;
`;

export default Main;
