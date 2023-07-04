package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.bookings.Booking;
import dev.rexdawn.hotelbooking.bookings.BookingService;
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

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// Service class er vitr e ekta repository er refernce lagbe
@Service // Database access methods are written inside this class
public class HotelService {
    @Autowired
    private BookingService bookingService;
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
    public Optional<List<Hotel>>getSameCityHotels(String city){
//        System.out.println(city);
        return hotelRepository.findByCity(city);
    }
    public Hotel updateHotel(ObjectId id, Hotel updatedHotel) {
        Optional<Hotel> srchedHotel = hotelRepository.findById(id);

        if(srchedHotel.isPresent()) {
            Hotel hotel = srchedHotel.get();
            if(updatedHotel.getName()!=null) {
                hotel.setName(updatedHotel.getName());
            }
            if(updatedHotel.getCity()!=null) {
                hotel.setCity(updatedHotel.getCity());
            }
            if(updatedHotel.getDesc()!=null) {
                hotel.setDesc(updatedHotel.getDesc());
            }
            if(updatedHotel.getAddress()!=null) {
                hotel.setAddress(updatedHotel.getAddress());
            }
            if(updatedHotel.getRooms()!=null) {
                hotel.setRooms(updatedHotel.getRooms());
            }
            if(updatedHotel.getImageUrl()!=null) {
                hotel.setImageUrl(updatedHotel.getImageUrl());
            }
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
                    if(modifiedroom.getTitle()!=null) {
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

    //Extract All Rooms of a Hotel by Id
    public List<Room> getRoomList(String hotelId, Date checkInDate,Date checkOutDate)
    {
       Optional<Hotel> hotel=hotelRepository.findById(hotelId);
       List<Room> rooms= hotel.get().getRooms();
       List<Booking> bookings=bookingService.allBookings();
        List<String> unavailableRoomIds = bookings.stream()
                .filter(booking -> (checkInDate.after(booking.getCheckInDate()) && checkInDate.before(booking.getCheckOutDate()))
                        ||(checkOutDate.after(booking.getCheckInDate()) && checkOutDate.before(booking.getCheckOutDate()))
                        ||(booking.getCheckInDate().after(checkInDate) && booking.getCheckInDate().before(checkOutDate))
                        ||(booking.getCheckOutDate().after(checkInDate) && booking.getCheckOutDate().before(checkOutDate)))
                .map(Booking::getRoomId)
                .collect(Collectors.toList());

        List<Room> availableRooms = rooms.stream()
                .filter(room -> !unavailableRoomIds.contains(room.getId()))
                .collect(Collectors.toList());

        return availableRooms;
    }

}
