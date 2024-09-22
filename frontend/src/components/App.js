import SigninMU from './SignInMU';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PrivateRoute from './PrivateRoute';
import DashboardComponent from './card/DashboardComponent';
import CheckinPage from './CheckinPage';
import IncomeChartComponent from './chart/IncomChartComponent';
import DoorListComponent from './door/DoorListComponent';

function App() {
  return (
    <div className="wrapper">
      <Router>
        <Routes>
          <Route path="/dashboard" element={<PrivateRoute element={DashboardComponent} />} />
          <Route path="/doorList" element={<PrivateRoute element={DoorListComponent} />} />
          <Route path="/checkin" element={<PrivateRoute element={CheckinPage} />} />
          <Route path="/report" element={<PrivateRoute element={IncomeChartComponent} />} />
          <Route path="/" element={<SigninMU/>}>
          </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
