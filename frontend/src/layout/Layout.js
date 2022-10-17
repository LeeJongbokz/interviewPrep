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
  height: 100%;
  background-color: rgba(135, 206, 235, 0.3);
  padding-bottom: 30px;
`;

export default Layout;
