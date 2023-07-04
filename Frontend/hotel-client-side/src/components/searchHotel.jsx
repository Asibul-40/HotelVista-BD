import React, { Fragment } from "react";
import { useNavigate } from "react-router-dom";

const SearchHotel = () =>{
    const navigate = useNavigate();

    const handleSearchSubmit = (event) => {
      event.preventDefault();
     const checkInDate = document.getElementById('checkInDate').value;
         const checkOutDate =document.getElementById('checkOutDate').value;
         const city = document.getElementById('city').value;
         if (checkInDate.trim() === '') {
           alert('Please enter the checkin date');
           return;
         }
         if(checkOutDate.trim() === ''){
           alert('Please enter the checkout date');
           return;
         }
         navigate(`/search-results/${city}/${checkInDate}/${checkOutDate}`);
    };

    return (
        <Fragment>
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
             <label htmlFor="date" style={{ marginRight: '10px' }}>Check-In-Date:</label>
             <input type="date" id="checkInDate" style={{ padding: '5px', marginRight: '10px' }} />
             <label htmlFor="date" style={{ marginRight: '10px' }}>Check-Out-Date:</label>
             <input type="date" id="checkOutDate" style={{ padding: '5px', marginRight: '10px' }} />
             <button type="submit" style={{ background: '#333', color: '#fff', padding: '5px 10px', border: 'none' }}>Search</button>
             </form>
          </section>
        </Fragment>
    )
}

export default SearchHotel;