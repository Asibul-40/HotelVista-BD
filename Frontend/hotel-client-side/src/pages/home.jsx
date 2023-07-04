import {React, Fragment} from 'react';
import Header from '../components/header';
import DealOffers from '../components/deals_Offers';
import HotelList from '../components/hotelList';
import SearchHotel from '../components/searchHotel';
import FeaturedHotels from '../components/featuredHotels';
import Footer from '../components/footer';
import { useNavigate, NavLink } from 'react-router-dom';

const Home = () =>{
    const navigate = useNavigate();

    const handleAllHotel = (e) =>{
        e.preventDefault();
        navigate('/allHotel');
    }
    return (
        <Fragment>

            <Header>
            </Header>

            <SearchHotel>
            </SearchHotel>

            <HotelList>
            </HotelList>

            {/* <button >
                <NavLink to="/allHotel"> All Hotel </NavLink>
            </button> */}

            <DealOffers>
            </DealOffers>

            <FeaturedHotels>
            </FeaturedHotels>

            <Footer>
            </Footer>

        </Fragment>
    )    
}

export default Home;