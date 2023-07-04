import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './hotelsearch.css';

const SearchResultsPage = () => {
  const { checkInDate, city, checkOutDate } = useParams();
  const [hotelRoomList, setHotelRoomList] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const duration = Math.ceil((new Date(checkOutDate) - new Date(checkInDate)) / (1000 * 60 * 60 * 24)); 

  // Function to handle opening the modal
  const openModal = (room) => {
    setSelectedRoom(room);
    setIsModalOpen(true);
  };

  // Function to handle closing the modal
  const closeModal = () => {
    setIsModalOpen(false);
  };

  useEffect(() => {
    // Fetch hotels by city
    axios.get(`http://localhost:8080/api/v1/hotels?city=${city}`)
      .then(response => {
        const hotels = response.data;
        console.log("Here is check in date " + checkInDate);
        console.log("Here is check out date " + checkOutDate);
         console.log(hotels);

        // Create an array to store hotel-room mappings
        const mappings = [];

        // Create an array to store promises for room requests
        const roomRequests = hotels.map(hotel => {
          // Fetch rooms for each hotel on the specific date
          return axios.get(`http://localhost:8080/api/v1/hotels/query?hotelId=${hotel.id}&checkInDate=${checkInDate}&checkOutDate=${checkOutDate}`)
            .then(response => {
              const rooms = response.data;
              // console.log("Room data for hotel", hotel.name);
              // console.log(rooms);

              // Map each room with its hotel name and store in the mappings array
              rooms.forEach(room => {
                mappings.push({
                  hotelName: hotel.name,
                  imageUrl: hotel.imageUrl,
                  room: room,
                  duration: duration,
                  totalCost: duration*room.price
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
  }, [city, checkInDate]);

  const handleReservedRoom = (hotelId)=>{
    console.log(hotelId);
    const payload=
    {
      roomId: hotelId,
      checkInDate: checkInDate,
      checkOutDate: checkOutDate
    }
    axios.post(`http://localhost:8080/api/bookings/add`,payload)
       .then(response =>{
        console.log("Bookings added = "+ response.data);
       })
       .catch(error=>{
        console.log(error);
       })
  }

  return (
    <div className="hotel-list">
      {hotelRoomList.map((mapping, index) => (
        <a href="#" onClick={()=>openModal(mapping)} style={{textDecoration:"none"}}> 
        <div className="hotel-card" key={index}>
          <img src={mapping.imageUrl} className="hotel-image" />
          <div className="hotel-info">
            <h3 className="hotel-name">{mapping.hotelName}</h3>
            <p className="room-title">{mapping.room.title}</p>
            <p className="room-description">Room Capacity: {mapping.room.maxPeople}</p>
            <p>Price: {mapping.room.price}/-</p>
          </div>
        </div>
        </a>
      ))}
      {isModalOpen && (
      <div className="modal">
        <div className="modal-content">
          <span className="close" onClick={closeModal}>
            &times;
          </span>
          {selectedRoom && (
          <div>
            <h3 style={{ fontSize: '24px', fontWeight: 'bold', marginBottom: '10px' }}>{selectedRoom.hotelName}</h3>
            <p style={{ fontSize: '16px', fontWeight: 'bold' }}>{selectedRoom.room.title}</p>
            <p style={{ fontSize: '14px', fontStyle: 'italic', marginBottom: '5px' }}>Check In Date: {new Date(checkInDate).toLocaleDateString('en-GB')}</p>
            <p style={{ fontSize: '14px', fontStyle: 'italic', marginBottom: '5px' }}>Check Out Date: {new Date(checkOutDate).toLocaleDateString('en-GB')}</p>
            <p style={{ fontSize: '14px', marginBottom: '5px' }}>Room Capacity: {selectedRoom.room.maxPeople}</p>
            <p style={{ fontSize: '14px', marginBottom: '5px' }}>Nights: {selectedRoom.duration}</p>
            <p style={{ fontSize: '16px', fontWeight: 'bold' }}>Price: {selectedRoom.totalCost}/-</p>
          </div>
        )}
        <button onClick={()=>{handleReservedRoom(selectedRoom.room.id); closeModal();}}>Reserve Room</button>
        
        </div>
      </div>
    )}
    </div>
    
  );
};

export default SearchResultsPage;
