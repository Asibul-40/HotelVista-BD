package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.room.Room;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotels")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {
    @Autowired
    private HotelService hotelService;

//    ------------------------------------- HOTEL MODULE --------------------------------------------------

    @GetMapping
    // GET ALL HOTELS
    public ResponseEntity<List<Hotel>> GetAllHotel(){
        return new ResponseEntity<List<Hotel>>(hotelService.allHotel(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    // GET SINGLE HOTEL BY ID
    public ResponseEntity<Optional<Hotel>> GetSingleHotel(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Hotel>>(hotelService.singleHotel(id), HttpStatus.OK);
    }
    @GetMapping("/loc/{address}")
    // GET ALL HOTELS BY ADDRESS
    public ResponseEntity<Optional<List<Hotel>>> GetSameAddressHotels(@PathVariable String address) {
        return new ResponseEntity<Optional<List<Hotel>>>(hotelService.getSameAddressHotels(address), HttpStatus.OK);
    }
    @PostMapping("/add")
    // ADD NEW HOTEL
    public Hotel AddNewHotel(@RequestBody Hotel newHotel){
        return hotelService.newHotel(newHotel);
    }
    @PutMapping("/up/{id}")
    // UPDATE ANY HOTEL BY ID
    public ResponseEntity<Hotel> UpdateHotel(@PathVariable ObjectId id, @RequestBody Hotel updatedHotel){
        return new ResponseEntity<Hotel>(hotelService.updateHotel(id, updatedHotel), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    // DELETE ANY HOTEL BY ID
    public void DeleteHotel(@PathVariable("id") ObjectId id){
        hotelService.deleteHotel(id);
    }
//--------------------------------------------------------------------------------------------------------



//    ---------------------------------- HOTEL ROOM MODULE ------------------------------------------------

    @PostMapping("/addroom/{id}")
    // ADD NEW ROOM INSIDE ANY HOTEL, associated with unique HOTEL ID
    public ResponseEntity<String> AddHotelRoom(@RequestBody Room room, @PathVariable ObjectId id){
        return new ResponseEntity<String>(hotelService.addHotelRoom(room, id), HttpStatus.OK);
    }
    @PutMapping("/updateroom/{id}")
    // UPDATE ANY ROOM INSIDE ANY HOTEL, associated with unique HotelID, unique roomID
    public ResponseEntity<String>UpdateHotelRoom(@PathVariable ObjectId id, @RequestParam("roomId") String roomId, @RequestBody Room room){
        return new ResponseEntity<String>(hotelService.updateHotelRoom(id, roomId, room), HttpStatus.OK);
    }
    @DeleteMapping("/deleteroom/{id}")
    // DELETE ROOM INSIDE ANY HOTEL, associated with unique HotelID, unique roomID
    public ResponseEntity<String> DeleteHotelRoom(@PathVariable ObjectId id, @RequestParam("roomId") String roomId){
        return new ResponseEntity<String>(hotelService.deleteHotelRoom(id, roomId), HttpStatus.OK);
    }

//------------------------------------------------------------------------------------------------------------
}
