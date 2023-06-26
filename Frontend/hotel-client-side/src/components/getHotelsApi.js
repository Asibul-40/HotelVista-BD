import React, { useEffect, useState } from 'react';
import './homepage.css';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const HotelList = () => {
  const [hotels, setHotels] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/hotels')
      .then(response => {
        console.log(response.data);
        const firstSixHotels = response.data.slice(0, 6); // Log the fetched data
        setHotels(firstSixHotels);
      })
      .catch(error => console.error(error));
  }, []);

  const navigate = useNavigate();

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    const date = document.getElementById('date').value;
    const city = document.getElementById('city').value;
    if (date.trim() === '') {
      alert('Please enter the date');
      return;
    }
    navigate(`/search-results/${city}/${date}`);
  };
    return (
      <div style={{ textAlign: 'center', background: '#f2f2f2', padding: '20px' }}>
        <header style={{ background: '#333', color: '#fff', padding: '20px', marginBottom: '20px' }}>
          <h1 style={{ marginBottom: '10px' }}>Welcome to RoomVista</h1>
          <p style={{ fontSize: '18px' }}>Find and book the best hotel rooms for your next adventure!</p>
        </header>
        <main>
          <section style={{ marginBottom: '20px' }}>
            <h2 style={{ marginBottom: '10px' }}>Search for Hotels</h2>
            <form onSubmit={handleSearchSubmit}>
              <label htmlFor="city" style={{ marginRight: '10px' }}>City:</label>
              <select id="city" style={{ padding: '5px', marginRight: '10px' }}>
                <option value="Dhaka">Dhaka</option>
                <option value="Chittagong">Chittagong</option>
                <option value="Sylhet">Sylhet</option>
                <option value="Khulna">Khulna</option>
              </select>
              <label htmlFor="date" style={{ marginRight: '10px' }}>Date:</label>
              <input type="date" id="date" style={{ padding: '5px', marginRight: '10px' }} />
              <button type="submit" style={{ background: '#333', color: '#fff', padding: '5px 10px', border: 'none' }}>Search</button>
            </form>
          </section>
          <section style={{ marginBottom: '20px' }}>
            <h2 style={{ marginBottom: '10px' }}>Deals and Offers</h2>
            <div style={{ display: 'flex', justifyContent: 'center', flexWrap: 'wrap' }}>
              <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
                <h3 style={{ marginBottom: '10px' }}>Special Summer Discount</h3>
                <p style={{ margin: '0px' }}>Enjoy up to 20% off on selected hotels. Limited time offer!</p>
              </div>
              <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
                <h3 style={{ marginBottom: '10px' }}>Last Minute Deals</h3>
                <p style={{ margin: '0px' }}>Grab amazing discounts on last-minute bookings. Don't miss out!</p>
              </div>
              {/* Add more deals and offers here */}
            </div>
          </section>
          <section>
            <h2 style={{ marginBottom: '20px' }}>Featured Hotels</h2>
            <div>
              <ul style={{ display: 'flex', justifyContent: 'center', listStyle: 'none' }}>
                {hotels.map(hotel => (
                  <li
                    key={hotel.id} id="hotelList"
                    style={{
                      margin: '0 20px',
                      padding: '20px',
                      borderRadius: '5px',
                      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                      display: 'flex',
                      flexDirection: 'column',
                      alignItems: 'center',
                      textAlign: 'center',
                      background: '#fff',
                      color: '#333'
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
                    <h2 style={{ marginBottom: '0px', marginTop: '10px' }}>{hotel.name}</h2>
                    <p style={{ margin: '0px' }}>City: {hotel.city}</p>
                  </li>
                ))}
              </ul>
            </div>
          </section>
        </main>
        <section style={{ marginBottom: '20px' }}>
  <h2 style={{ marginBottom: '20px' }}>Explore Nearby Attractions</h2>
  <div style={{ display: 'flex', justifyContent: 'center' }}>
    <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
      <h3 style={{ marginBottom: '10px' }}>Local Landmarks</h3>
      <p style={{ margin: '0px' }}>Discover famous landmarks and cultural sites in the vicinity.</p>
    </div>
    <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
      <h3 style={{ marginBottom: '10px' }}>Outdoor Activities</h3>
      <p style={{ margin: '0px' }}>Experience thrilling outdoor adventures and recreational activities.</p>
    </div>
    {/* Add more sections or cards for nearby attractions */}
  </div>
</section>

        <footer style={{ background: '#333', color: '#fff', padding: '10px', marginTop: '20px' }}>
          <p>&copy; 2023 RoomVista. All rights reserved.</p>
        </footer>
      </div>
    );
    
};

export default HotelList;


