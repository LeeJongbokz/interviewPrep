import React from 'react';
import Header from './Header';
import styled from 'styled-components';
import { Outlet } from 'react-router-dom';
const Layout = () => {
  return (
    <>
      <Header />
      <Main>
        <Outlet />
      </Main>
    </>
  );
};
const Main = styled.main`
  height: 100vh;
  background-color: #e9e9e9;

  text-align: center;
`;

export default Layout;
