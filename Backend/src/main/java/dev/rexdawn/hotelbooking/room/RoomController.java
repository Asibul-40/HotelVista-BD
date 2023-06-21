package dev.rexdawn.hotelbooking.room;

import dev.rexdawn.hotelbooking.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping
    private ResponseEntity<List<Room>> allRooms()
    {
        return new ResponseEntity<List<Room>>(roomService.getAllRooms(), HttpStatus.OK);
    }
    @PostMapping("/addroom")
    public ResponseEntity<String> addRoom(@RequestBody Room room)
    {
        roomService.addNewRoom(room);
        return ResponseEntity.ok("New Room Added Successfully!");
    }
}
