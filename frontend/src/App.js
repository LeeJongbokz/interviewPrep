import { BrowserRouter as Router, Route } from 'react-router-dom';
import Login from './component/Login';
import Main from './component/Main';
import Test from './component/Test';
import TestScreen from './component/TestScreen';
import SignUp from './component/SignUp';
function App() {
  return (
    <div className="App">
      <Router>
        <Route path="/" component={Main} exact={true} />
        <Route path="/login" component={Login} />
        <Route path="/test" component={Test} exact={true} />
        <Route path="/test/:subject" component={TestScreen} exact={true} />
        <Route path="/signup" component={SignUp} exact={true} />
      </Router>
    </div>
  );
}

export default App;
