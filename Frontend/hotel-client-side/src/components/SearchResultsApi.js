import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './hotelsearch.css';

const SearchResultsPage = () => {
  const { date, city } = useParams();
  const [hotelRoomList, setHotelRoomList] = useState([]);

  useEffect(() => {
    // Fetch hotels by city
    axios.get(`http://localhost:8080/api/v1/hotels?city=${city}`)
      .then(response => {
        const hotels = response.data;
        // console.log(hotels);

        // Create an array to store hotel-room mappings
        const mappings = [];

        // Create an array to store promises for room requests
        const roomRequests = hotels.map(hotel => {
          // Fetch rooms for each hotel on the specific date
          return axios.get(`http://localhost:8080/api/v1/hotels/query?hotelId=${hotel.id}&date=${date}`)
            .then(response => {
              const rooms = response.data;
              // console.log("Room data for hotel", hotel.name);
              // console.log(rooms);

              // Map each room with its hotel name and store in the mappings array
              rooms.forEach(room => {
                mappings.push({
                  hotelName: hotel.name,
                  imageUrl: hotel.imageUrl,
                  room: room
                });
              });
            })
            .catch(error => console.error(error));
        });

        // Wait for all room requests to complete
        Promise.all(roomRequests)
          .then(() => {
            // Update the hotelRoomList state with the new mappings
            setHotelRoomList(mappings);
          })
          .catch(error => console.error(error));
      })
      .catch(error => console.error(error));
  }, [city, date]);

  return (
    <div className="hotel-list">
      {hotelRoomList.map((mapping, index) => (
        <div className="hotel-card" key={index}>
          <img src={mapping.imageUrl} alt="Hotel" className="hotel-image" />
          <div className="hotel-info">
            <h3 className="hotel-name">{mapping.hotelName}</h3>
            <p className="room-title">{mapping.room.title}</p>
            <p className="room-description">Room Capacity: {mapping.room.maxPeople}</p>
            <p>Price: {mapping.room.price}/-</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default SearchResultsPage;
