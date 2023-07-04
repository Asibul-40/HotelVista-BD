import {React, useState} from "react";
import { NavLink, useNavigate } from "react-router-dom";
// import './css/homepage.css';

const Header = () =>{
    const username = JSON.parse( localStorage.getItem("user") );
    const [menuOpen, setMenuOpen] = useState(false);

    const navigate = useNavigate();

    const handleToggleMenu = () =>{
      console.log(menuOpen);
       setMenuOpen(!menuOpen);
       console.log(menuOpen);
    }

    const handleProfile = (e)=>{

    }
    const handleLogout = (e) =>{
      e.preventDefault();
        localStorage.removeItem("user")
        localStorage.removeItem("loggedIn")
        localStorage.removeItem("token")
        localStorage.removeItem("power")
        navigate("/");
    }

    return (
        <header style={{ background: '#333', color: '#fff', padding: '20px', marginBottom: '20px' }}>

            <div className="nav">
            <nav>
                <div className="logo">
                    {/* Logo */}
                </div>
                <div className="nav-items">
            

            {username ? (
              <div className="user-menu">
                <div onClick={handleToggleMenu} style={{cursor: 'pointer'}}>
                  {/* {username} &#9662;  */}
                  {username} {menuOpen ? '▲' : '▼'}
                </div>
                {
                    menuOpen && 
                        <div className="dropmenu">
                            <ul>
                                <li>  <NavLink to="" onClick={handleProfile}> Profile </NavLink>  </li>
                                <li>  <NavLink onClick={handleLogout}> Logout </NavLink>  </li>
                            </ul>
                        </div>
                }
              </div>
            ) : (
              <NavLink to="/login">Sign in</NavLink>
            )}
                   
                </div>
            </nav>
            </div>

          <h1 style={{ marginBottom: '10px' }}>Welcome to RoomVista</h1>
          <p style={{ fontSize: '18px' }}>Find and book the best hotel rooms for your next adventure!</p>
        </header>
    )
}

export default Header;

