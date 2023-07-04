import {React, useState, useEffect} from "react";
import { NavLink } from "react-router-dom";

const Header = () =>{
    const username = JSON.parse( localStorage.getItem("user") );
    const loggedIn = JSON.parse( localStorage.getItem("loggedIn"))

    // useEffect(() =>{
    //     if(!username){
    //         username = "";
    //     }
    // })

    return (
        <header style={{ background: '#333', color: '#fff', padding: '20px', marginBottom: '20px' }}>

            <div className="nav">
            <nav>
                <div className="logo">
                Logo, {username}
                </div>
                <div className="nav-items">

                {username ? username : (
                    <NavLink to="/login">  Sign in  </NavLink> 
                )}

                     {/* <NavLink to="/login">  Sign in  </NavLink>  */}
                   
                </div>
            </nav>
            </div>

          <h1 style={{ marginBottom: '10px' }}>Welcome to RoomVista</h1>
          <p style={{ fontSize: '18px' }}>Find and book the best hotel rooms for your next adventure!</p>
        </header>
    )
}

export default Header;

