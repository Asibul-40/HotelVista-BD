package dev.rexdawn.hotelbooking.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    public List<Room> getAllRooms()
    {
        return roomRepository.findAll();
    }
    public void addNewRoom(Room room) {
        roomRepository.save(room);
    }
}
