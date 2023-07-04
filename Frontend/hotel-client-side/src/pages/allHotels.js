import axios from 'axios';
import React from 'react'
import { useEffect} from 'react'

const AllHotels = () => {
    const token = localStorage.getItem('token');

  useEffect( () =>{
    // console.log("ashce", token)
    const url = 'http://localhost:8080/api/v1/hotels/all'
    // const header = {
    //     "Content-type": "application/json",
    //     "Authorization": `Bearer ${token}`
    // }
    // console.log(header.Authorization);

    axios.get(url).then(res=>{
        console.log("----~~",res.data);
    }).catch(err=>{
        console.log("ERROR is: ",err);
    })
  }, [token])
    
  return (
    <div>
        All Hotels page
    </div>
  )
}

export default AllHotels