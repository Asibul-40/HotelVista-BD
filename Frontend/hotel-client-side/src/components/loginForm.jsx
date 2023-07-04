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


const LoginForm = ()=> {
  const [user, setUSer] = useState({
    username: '',
    password: ''
  })

  const navigate = useNavigate();


  const HandleChange=(e)=>{
    const {name, value} = e.target;
    setUSer(user=>({
      ...user,
      [name]: value
    }));
  }

  const HandleLoginDetails = (e =>{
    e.preventDefault();

    // console.log(user);
    const url = 'http://localhost:8080/api/v1/auth/login';
    axios.post(url, user)
      .then(res=>{
        // console.log(res.data);

        const {token, username} = res.data;
        console.log(username, token);
        if(!username) {
          toast.error(res.data.token, {
            position: "top-right",
            autoClose: 3000,
            theme: "colored",
            });
        }
        else{
          localStorage.setItem("loggedIn", true);
          localStorage.setItem("user", JSON.stringify(user.username));
            navigate("/");
        }
        
      }).catch(err=>{
        console.log(err);
      })

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
              <h2 className="fw-bold mb-2 text-uppercase">Login</h2>
              <p className="text-white-50 mb-5">Please enter your username and password!</p>

              <form action="" onSubmit={HandleLoginDetails} style={{width:'100%'}}>

                <input type="text" name="username" id="username" onChange={HandleChange} value={user.username} style={{width:'70%', paddingBottom:'.3em' , paddingTop:'.3em', borderRadius:'5px'}}/> <br />
                <label htmlFor="username">Username</label> <br /> <br /> 

                <input type="password" name="password" id="password" onChange={HandleChange} value={user.password} style={{width:'70%', paddingBottom:'.3em', paddingTop:'.3em', borderRadius:'5px'}} /> <br />
                <label htmlFor="password">Password</label>

                <p className="small mb-3 pb-lg-2" style={{paddingTop:'3em'}}><a class="text-white-50" href="#!" >Forgot password?</a></p>
                  <div className="loginBt">
                    <button style={{ padding: '0.6em 2.5em', borderRadius: '7px', background: '#00b100',color: 'white'}}>
                      Login
                    </button>
                  </div>
                  <ToastContainer/>

                  {/* <MDBInput wrapperClass='mb-4 mx-5 w-100' labelClass='text-white' label='Username' id='username' type='text' size="lg" style={{marginLeft:'0rem'}}/>
                  <MDBInput wrapperClass='mb-4 mx-5 w-100' labelClass='text-white' label='Password' id='password' type='password' size="lg"/>
                  <p className="small mb-3 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
                  <MDBBtn type='submit' outline className='mx-2 px-5' color='white' size='lg' style={{backgroundColor:'#00ca00'}}>
                    Login
                  </MDBBtn> */}
              </form>

             

              <div style={{paddingTop: '3em'}}>
                <p className="mb-0">Don't have an account? 
                  <NavLink className="signup-Btn" to={"/register"} style={{textDecoration:'none', paddingLeft:'5px', paddingRight:'4px' ,color: 'yellow', fontSize:'17px'}}>
                    Register
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

export default LoginForm;