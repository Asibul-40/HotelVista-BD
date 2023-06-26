package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.room.Room;
import dev.rexdawn.hotelbooking.room.RoomRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Service class er vitr e ekta repository er refernce lagbe
@Service // Database access methods are written inside this class
public class HotelService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
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



//    ----------------------------------- ROOM MODULE -------------------------------------------------
    public String addHotelRoom(Room room, ObjectId id){
        Room newRoom = roomRepository.insert(room);
        mongoTemplate.update(Hotel.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("rooms").value(newRoom))
                .first();
        return "Room Added...!";
    }
    public String updateHotelRoom(ObjectId hotelId, String roomId, Room modifiedroom){

        Optional<Hotel> searchedHotel = hotelRepository.findById(hotelId);
        if (searchedHotel.isPresent()) {
            Hotel hotel = searchedHotel.get();
            List<Room> rooms = hotel.getRooms();
            for (Room room : rooms) {
                System.out.println(room.getRoomId()+" "+roomId);
                if (room.getRoomId().equals(roomId)) {
                    if(modifiedroom.getPrice()!=null){
                        room.setPrice(modifiedroom.getPrice());
                    }
                    if(modifiedroom.getDesc()!=null){
                        room.setDesc(modifiedroom.getDesc());
                    }
                    if(modifiedroom.getMaxPeople()!=null){
                        room.setMaxPeople(modifiedroom.getMaxPeople());
                    }
                    if(modifiedroom.getTitle()!=null){
                        room.setTitle(modifiedroom.getTitle());
                    }
                    roomRepository.save(room);
                    hotel.setRooms(rooms);
                    hotelRepository.save(hotel);
//                    System.out.println(room.getRoomId()+","+room.getPrice()+","+room.getDesc());
                    break;
                }
            }
        }
        return "RoomID= "+roomId+ ", is Updated for HotelID= "+hotelId;
    }
    public String deleteHotelRoom(ObjectId hotelId, String roomId){
        Optional<Hotel>searchedHotel = hotelRepository.findById(hotelId);
        if(searchedHotel.isPresent()){
            Hotel hotel = searchedHotel.get();
            List<Room>rooms = hotel.getRooms();
            for(Room room:rooms){
                if(room.getRoomId().equals(roomId)) {
                    roomRepository.deleteByroomId(roomId);
                    break;
                }
            }
            hotel.setRooms(rooms);
            hotelRepository.save(hotel);
            return "Room Deleted with roomID="+roomId+", associated with hotelId="+hotelId;
        }
        else{
            return "Hotel Not found with this HotelId="+hotelId;
        }
    }

}
