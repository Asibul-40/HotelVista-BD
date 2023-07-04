import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import SearchResultsPage from './components/SearchResultsApi';
import Login from './components/loginForm';
import Register from './components/registerForm';
import Home from './pages/home'

function App() {
  return (
    <Router>
    <div className="App">
       <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route path='/login' element={<Login/>} />
          <Route path='/register' element={<Register/>} />
          <Route path="/search-results/:city/:checkInDate/:checkOutDate" element={<SearchResultsPage/>} />
        </Routes>
    </div>
    </Router>
  );
}

export default App;
