import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const SearchResultsPage = () => {
  const { date } = useParams();
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/bookings/search/${date}`)
      .then(response => {
        console.log(response.data); // Log the fetched data
        setRooms(response.data);
      })
      .catch(error => console.error(error));
  }, [date]);

  return (
    <div>
      <h2>Search Results</h2>
      <p>Selected Date: {date}</p>

      <h3>Available Rooms:</h3>
      <ul>
        {rooms.map(room => (
          <li key={room.id}>
            <h1>Room Name : {room.title}</h1>
            <h2>Room Price : {room.price}</h2>
            <h2>Room capacity : {room.maxPeople}</h2>
            </li>
        ))}
      </ul>
    </div>
  );
};

export default SearchResultsPage;
