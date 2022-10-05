import React from 'react';
import Header from './Header';
import styled from 'styled-components';

const Layout = props => {
  return (
    <>
      <Header />
      <Main>{props.children}</Main>
    </>
  );
};
const Main = styled.main`
  height: 999px;
  text-align: center;
`;

export default Layout;
