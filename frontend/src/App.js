import { useContext } from 'react';
import { Navigate, Route, Routes as Switch } from 'react-router-dom';
import { createTheme, ThemeProvider } from '@mui/material';

import Layout from './layout/Layout';

import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import TestListPage from './pages/TestListPage';
import SignUpPage from './pages/SignUpPage';
import MyPage from './pages/MyPage';
import AnswerListPage from './pages/AnswerListPage';
import TestScreenPage from './pages/TestScreenPage';

import AuthContext from './store/auth-context';

function App() {
  const theme = createTheme({
    // palette: {
    //   primary: {
    //     light: '#757ce8',
    //     main: '#3f50b5',
    //     dark: '#002884',
    //     contrastText: '#fff',
    //   },
    //   secondary: {
    //     light: '#ff7961',
    //     main: '#f44336',
    //     dark: '#ba000d',
    //     contrastText: '#000',
    //   },
    // },
  });
  const authCtx = useContext(AuthContext);

  return (
    <ThemeProvider theme={theme}>
    <div className="App">
      <Switch>
        <Route element={<Layout />}>
          <Route path="/" element={<MainPage />} />
          <Route path="/test" element={<TestListPage />} />
          <Route path="/test/:subject" element={<TestScreenPage />} />
          <Route path="/answer/:id" element={<AnswerListPage />} />
          <Route
            path="/login"
            element={authCtx.isLoggedIn ? <Navigate to="/" replace={true} /> : <LoginPage />}
          />
          <Route
            path="/signup"
            element={authCtx.isLoggedIn ? <Navigate to="/" /> : <SignUpPage />}
          />
          <Route path="/my-page" element={authCtx.isLoggedIn ? <MyPage /> : <Navigate to="/" />} />
        </Route>
      </Switch>
    </div>
    </ThemeProvider>
  );
}

export default App;
