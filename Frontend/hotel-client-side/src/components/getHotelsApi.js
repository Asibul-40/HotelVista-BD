import React, { useEffect, useState } from 'react';
import './homepage.css';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const HotelList = () => {
  const [hotels, setHotels] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/hotels')
      .then(response => {
        console.log(response.data); // Log the fetched data
        setHotels(response.data);
      })
      .catch(error => console.error(error));
  }, []);

  const navigate = useNavigate();

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    const date = document.getElementById('date').value;
    navigate(`/search-results/${date}`);
  };

  return (
        <div style={{ textAlign: 'center', background: '#f2f2f2', padding: '20px' }}>
          <header>
            <h1 style={{ color: '#333', marginBottom: '10px' }}>Welcome to RoomVista</h1>
            <p style={{ color: '#666', fontSize: '18px' }}>Find and book the best hotel rooms for your next adventure!</p>
          </header>
          <main>
            <section style={{ marginBottom: '20px' }}>
              <h2 style={{ color: '#333', marginBottom: '10px' }}>Search for Hotels</h2>
              <form onSubmit={handleSearchSubmit}>
                <label htmlFor="region" style={{ marginRight: '10px' }}>Region:</label>
                <input type="text" id="region" placeholder="Enter a region" style={{ padding: '5px', marginRight: '10px' }} />
                <label htmlFor="date" style={{ marginRight: '10px' }}>Date:</label>
                <input type="date" id="date" style={{ padding: '5px', marginRight: '10px' }} />
                <button type="submit" style={{ background: '#333', color: '#fff', padding: '5px 10px', border: 'none' }}>Search</button>
              </form>
            </section>
            <section>
              <h2 style={{ color: '#333', marginBottom: '20px' }}>Featured</h2>
              <div>
                <ul style={{ display: 'flex', justifyContent: 'center', listStyle: 'none' }}>
                  {hotels.map(hotel => (
                   <li
                   key={hotel.id}
                   style={{
                     margin: '0 20px',
                     padding: '20px',
                     borderRadius: '5px',
                     boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                     display: 'flex',
                     flexDirection: 'column',
                     alignItems: 'center',
                     textAlign: 'center'
                   }}
                 >
                   <div
                     style={{
                       backgroundImage: `url(${hotel.imageUrl})`,
                       backgroundSize: 'contain',
                       backgroundRepeat: 'no-repeat',
                       width: '150px',
                       height: '100px'
                     }}
                   />
                   <h2 style={{ color: '#333', marginBottom: '0px' }}>{hotel.name}</h2>
                   <p style={{ color: '#666', margin: '0px' }}>City: {hotel.city}</p>
                   <p style={{ color: '#666', margin: '0px' }}>Address: {hotel.address}</p>
                 </li>
                 
                  ))}
                </ul>
              </div>
            </section>
          </main>
        </div>
  );
};

export default HotelList;


