import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TestScreen from './component/TestScreen';
import Layout from './layout/Layout';
import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import TestListPage from './pages/TestListPage';
import SignUpPage from './pages/SingUpPage';
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route element={<Layout />}>
            <Route path="/" element={<MainPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/test" element={<TestListPage />} />
            <Route path="/test/:subject" element={<TestScreen />} />
            <Route path="/singup" element={<SignUpPage />} />
          </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
