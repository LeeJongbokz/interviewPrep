
import { useContext } from 'react';
import { Navigate, Route, Routes as Switch } from 'react-router-dom';

import Layout from './layout/Layout';

// import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import TestListPage from './pages/TestListPage';
import SignUpPage from './pages/SignUpPage';
import ExamPage from './pages/ExamPage';
import MyPage from './pages/MyPage';
import AnswerListPage from './pages/AnswerListPage';
import TestScreenPage from './pages/TestScreenPage';
import AuthContext from './store/auth-context';

function App() {

  const authCtx = useContext(AuthContext);

  return (
    <div className="App">
      <Switch>
        <Route element={<Layout />}>
          <Route path="/" element={<TestListPage />} />
          <Route path="/test" element={<TestListPage />} />
          <Route path="/exam" element={<ExamPage />} />
          <Route path="/test/:subject" element={<TestScreenPage />} />
          <Route path="/answer/:id" element={<AnswerListPage />} />
          <Route path="/login" element={ authCtx.isLoggedIn ? <Navigate to="/" replace={true} /> : <LoginPage /> } />
          <Route path="/signup" element={ authCtx.isLoggedIn ? <Navigate to="/" /> : <SignUpPage /> } />
          <Route path="/my-page" element={ authCtx.isLoggedIn ? <MyPage /> : <Navigate to="/" /> } />
        </Route>
      </Switch>
    </div>
  );
}

export default App;
