import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './component/Login';
import Main from './component/Main';
import Test from './component/Test';
import TestScreen from './component/TestScreen';
import SingUp from './component/SingUp';
import Layout from './layout/Layout';
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route element={<Layout />}>
            <Route path="/" element={<Main />} />
            <Route path="/login" element={<Login />} />
            <Route path="/test" element={<Test />} />
            <Route path="/test/:subject" element={<TestScreen />} />
            <Route path="/singup" element={<SingUp />} />
          </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
