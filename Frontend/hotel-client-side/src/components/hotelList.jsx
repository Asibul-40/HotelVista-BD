import React, { useState, useEffect, Fragment } from 'react';
import axios from 'axios';

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

    return (
        <Fragment>
            <main>
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
        </Fragment>
    )
}

export default HotelList;