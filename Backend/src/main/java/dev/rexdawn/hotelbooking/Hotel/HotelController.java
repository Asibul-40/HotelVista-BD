package dev.rexdawn.hotelbooking.Hotel;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return new ResponseEntity<List<Hotel>>(hotelService.allHotel(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public Hotel addNewHotel(@RequestBody Hotel newHotel){
        return hotelService.newHotel(newHotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hotel>> GetSingleHotel(@PathVariable ObjectId id)
    {
        return new ResponseEntity<Optional<Hotel>>(hotelService.singleHotel(id), HttpStatus.OK);
    }

    @GetMapping("/loc/{address}")
    public ResponseEntity<Optional<List<Hotel>>> GetSameAddressHotels(@PathVariable String address)
    {
        return new ResponseEntity<Optional<List<Hotel>>>(hotelService.getSameAddressHotels(address), HttpStatus.OK);
    }

    @PutMapping("/up/{id}")
    public ResponseEntity<Hotel> UpdateHotel(@PathVariable ObjectId id, @RequestBody Hotel updatedHotel){
        return new ResponseEntity<Hotel>(hotelService.updateHotel(id, updatedHotel), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void DeleteHotel(@PathVariable("id") ObjectId id){
        hotelService.deleteHotel(id);
    }


}
