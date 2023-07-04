package dev.rexdawn.hotelbooking.bookings;

import dev.rexdawn.hotelbooking.room.Room;
import dev.rexdawn.hotelbooking.room.RoomService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings()
    {
        return new ResponseEntity<List<Booking>>(bookingService.allBookings(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@RequestBody Booking booking)
    {
        bookingService.addNewBooking(booking);
        return ResponseEntity.ok("New Booking Added Successfully!");
    }
    @GetMapping("/search/{city}/{checkInDate}/{checkOutDate}")
    public ResponseEntity<List<Room>> findAvailableRooms(
            @PathVariable String city,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutdate)
    {
        List<String> allRoomId=bookingService.findAvailableRoomsOnDate(checkInDate,checkOutdate);

        return new ResponseEntity<List<Room>>(roomService.getAllAvailableRooms(allRoomId),HttpStatus.OK);
    }
}
