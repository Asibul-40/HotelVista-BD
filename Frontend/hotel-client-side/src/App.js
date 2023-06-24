import logo from './logo.svg';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import HotelList from './components/getHotelsApi';
import SearchResultsPage from './components/SearchResultsApi';

function App() {
  return (
    <Router>
    <div className="App">
       <Routes>
          <Route exact path="/" element={<HotelList/>} />
          <Route path="/search-results/:date" element={<SearchResultsPage/>} />
        </Routes>
    </div>
    </Router>
  );
}

export default App;
