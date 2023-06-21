package dev.rexdawn.hotelbooking.Hotel;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

// Service class er vitr e ekta repository er refernce lagbe
@Service // Database access methods are written inside this class
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    public List<Hotel> allHotel(){
        return hotelRepository.findAll();
    }

    public Hotel newHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    public Optional<Hotel> singleHotel(ObjectId id){
        return hotelRepository.findById(id);
    }
    public Optional<List<Hotel>>getSameAddressHotels(String address){
        return hotelRepository.findByAddress(address);
    }
    public Hotel updateHotel(ObjectId id, Hotel updatedHotel) {
        Optional<Hotel> srchedHotel = hotelRepository.findById(id);

        if(srchedHotel.isPresent()) {
            Hotel hotel = srchedHotel.get();
            hotel.setName(updatedHotel.getName());
            hotel.setCity(updatedHotel.getCity());
            hotel.setDesc(updatedHotel.getDesc());
            hotel.setAddress(updatedHotel.getAddress());
            hotel.setRooms(updatedHotel.getRooms());
            hotelRepository.save(hotel);
            return hotel;
        }
        else{
            throw new ResourceAccessException("Can't update hotel by this id: "+ id);
        }
    }

    public void deleteHotel(ObjectId id){
        Optional<Hotel>searchedHotel = hotelRepository.findById(id);
        System.out.println(searchedHotel);
        if(searchedHotel.isPresent()) {
            hotelRepository.deleteById(id);
        }
        else{
            throw new ResourceAccessException("Hotel can't be deleted by this id: "+id);
        }
    }
}
