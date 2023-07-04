import {React, useState, useEffect, Fragment} from 'react';
import Header from '../components/header';
import DealOffers from '../components/deals_Offers';
import HotelList from '../components/hotelList';
import SearchHotel from '../components/searchHotel';
import FeaturedHotels from '../components/featuredHotels';
import Footer from '../components/footer';

const Home = () =>{
    return (
        <Fragment>

            <Header>
            </Header>

            <SearchHotel>
            </SearchHotel>

            <HotelList>
            </HotelList>

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