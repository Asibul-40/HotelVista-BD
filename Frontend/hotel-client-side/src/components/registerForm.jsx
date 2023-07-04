import React, {useState} from 'react';
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
}
from 'mdb-react-ui-kit';
import 'bootstrap/dist/css/bootstrap.min.css'
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const RegisterForm = ()=> {
  const [user, setUSer] = useState({
    username: '',
    email:'',
    password: ''
  })

  const navigate = useNavigate();

  const LoginForm = (e=>{
    e.preventDefault();
    console.log("Login button clicked...");
    navigate(`/login`);
  })

  const HandleChange=(e)=>{
    const {name, value} = e.target;
        setUSer(user=>({
            ...user,
            [name]: value
        }));
  }

  const HandleRegisterDetails = (e =>{
    e.preventDefault();
    const passwordCheck = document.getElementById("confirm-password").value;
    
    if(!user.username){
        toast.warn("Please fill up all the fields",{
            position: "top-right",
            theme: "light" 
        })
    }
    else if(passwordCheck !== user.password){
        toast.warn('Type your password carefully!',{
            position: "top-right",
            theme:"colored"
        })
    }
    else{
    // console.log(user);
    const url = 'http://localhost:8080/api/v1/auth/register';
    axios.post(url, user)
      .then(res=>{
        const {token} = res.data;
        if(token){
            toast.success("Registration Successful",{
                position: "top-center",
                theme: "colored"
            })
            localStorage.setItem("user", JSON.stringify(user.username));
            navigate('/login');
        }
        
      }).catch(err=>{
        console.log(err);
      })
    }
  })

  return (
    <div className="">
      
      <div className="nav">
          <nav>
            <div className="logo">
              Logo
            </div>
            <div className="nav-items">
              <button type='submit' onClick={LoginForm}>
                Login
              </button>
            </div>
            <div className="home">
              
            </div>
          </nav>
        </div>

    <MDBContainer fluid>
      <MDBRow className='d-flex justify-content-center align-items-center h-100'>
        <MDBCol col='12' >
          <MDBCard className='bg-dark text-white my-5 mx-auto' style={{borderRadius: '1rem', maxWidth: '485px'}}>
            <MDBCardBody className='p-5 d-flex flex-column align-items-center mx-auto w-100'>
              <h2 className="fw-bold mb-2 text-uppercase">Create an account</h2>
              <p className="text-white-50 mb-5">Please fill up all the required fields.</p>

              <form action="" onSubmit={HandleRegisterDetails} style={{width:'100%'}}>

                <input type="text" name="username" id="username" onChange={HandleChange} value={user.username} style={{width:'85%', paddingBottom:'.3em' , paddingTop:'.3em', borderRadius:'5px'}}/> <br />
                <label htmlFor="username">Username</label> <br /> <br /> 

                <input type="email" name="email" id="email" onChange={HandleChange} value={user.email} style={{width:'85%', paddingBottom:'.3em', paddingTop:'.3em', borderRadius:'5px'}} /> <br />
                <label htmlFor="email">Email Address</label> <br /> <br />

                <input type="password" name="password" id="password" onChange={HandleChange} value={user.password} style={{width:'85%', paddingBottom:'.3em', paddingTop:'.3em', borderRadius:'5px'}} /> <br />
                <label htmlFor="password">Password</label> <br /> <br />

                <input type="password" name="confirm-password" id="confirm-password" style={{width:'85%', paddingBottom:'.3em', paddingTop:'.3em', borderRadius:'5px'}} /> <br />
                <label htmlFor="confirm-password">Confirm Password</label>

                <p className="small mb-3 pb-lg-2" style={{paddingTop:'3em'}}>
                    {/* <a class="text-white-50" href="#!" >Forgot password?</a> */}
                    </p>
                  <div className="registerBtn">
                    <button style={{ padding: '0.6em 2.5em', borderRadius: '7px', background: '#00b100',color: 'white'}}>
                      Sign up
                    </button>
                  </div>
                  <ToastContainer/>

              </form>

             

              <div style={{paddingTop: '3em'}}>
                <p className="mb-0">Already have account? 
                  <NavLink className="signup-Btn" to={"/login"} style={{textDecoration:'none', paddingLeft:'5px', paddingRight:'4px' ,color: 'yellow', fontSize:'17px'}}>
                    Login
                  </NavLink>
                  here!
                </p>
              </div>

            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>

    </MDBContainer>
    </div>
  );
}

export default RegisterForm;