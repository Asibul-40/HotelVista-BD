package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.bookings.BookingService;
import dev.rexdawn.hotelbooking.room.Room;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotels")
@CrossOrigin("http://localhost:3000")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private BookingService bookingService;

//    ------------------------------------- HOTEL MODULE --------------------------------------------------

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Hotel>> GetAllHotels(){
        return ResponseEntity.ok(hotelService.allHotel());
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> GetHotels(@RequestParam(name = "city", required=false) String city) {
            // Get all hotels by City
            if(city!=null)
            {
                Optional<List<Hotel>> hotelsByCity = hotelService.getSameCityHotels(city);
                if (hotelsByCity.isPresent()) {
                    return new ResponseEntity<>(hotelsByCity.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            else {
                List<Hotel> allHotels = hotelService.allHotel();
                return new ResponseEntity<>(allHotels, HttpStatus.OK);
            }
    }
    @GetMapping("/query")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Room>> getRoomsFromHotel(
            @RequestParam String hotelId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
          //Extract All rooms by hotel Id
//        System.out.println(hotelId+" I am here!");
         List<Room> roomList=hotelService.getRoomList(hotelId,date);
         return new ResponseEntity<>(roomList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    // GET SINGLE HOTEL BY ID
    public ResponseEntity<Optional<Hotel>> GetSingleHotel(@PathVariable String id) {
        return new ResponseEntity<Optional<Hotel>>(hotelService.singleHotel(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    // ADD NEW HOTEL
    public Hotel AddNewHotel(@RequestBody Hotel newHotel){
        return hotelService.newHotel(newHotel);
    }
    @PutMapping("/up/{id}")
    // UPDATE ANY HOTEL BY ID
    public ResponseEntity<Hotel> UpdateHotel(@PathVariable String id, @RequestBody Hotel updatedHotel){
        return new ResponseEntity<Hotel>(hotelService.updateHotel(id, updatedHotel), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    // DELETE ANY HOTEL BY ID
    public void DeleteHotel(@PathVariable("id") String id){
        hotelService.deleteHotel(id);
    }
//--------------------------------------------------------------------------------------------------------



//    ---------------------------------- HOTEL ROOM MODULE ------------------------------------------------

    @PostMapping("/addroom/{id}")
    // ADD NEW ROOM INSIDE ANY HOTEL, associated with unique HOTEL ID
    public ResponseEntity<String> AddHotelRoom(@RequestBody Room room, @PathVariable String id){
        return new ResponseEntity<String>(hotelService.addHotelRoom(room, id), HttpStatus.OK);
    }
    @PutMapping("/updateroom/{id}")
    // UPDATE ANY ROOM INSIDE ANY HOTEL, associated with unique HotelID, unique roomID
    public ResponseEntity<String>UpdateHotelRoom(@PathVariable String id, @RequestParam("roomId") ObjectId roomId, @RequestBody Room room){
        return new ResponseEntity<String>(hotelService.updateHotelRoom(id, roomId, room), HttpStatus.OK);
    }
    @DeleteMapping("/deleteroom/{id}")
    // DELETE ROOM INSIDE ANY HOTEL, associated with unique HotelID, unique roomID
    public ResponseEntity<String> DeleteHotelRoom(@PathVariable String id, @RequestParam("roomId") ObjectId roomId){
        return new ResponseEntity<String>(hotelService.deleteHotelRoom(id, roomId), HttpStatus.OK);
    }

//------------------------------------------------------------------------------------------------------------
}
