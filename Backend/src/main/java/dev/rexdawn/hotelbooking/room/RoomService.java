package dev.rexdawn.hotelbooking.room;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Room> getAllRooms()
    {
        return roomRepository.findAll();
    }

    public List<Room> getAllAvailableRooms(List<ObjectId> allRoomid) {
        // Use the `in` operator to find rooms with matching IDs
        Criteria criteria = Criteria.where("_id").in(allRoomid);

        // Create a query with the criteria
        Query query = new Query(criteria);

        // Execute the query and return the matching rooms

        return mongoTemplate.find(query, Room.class);
    }

    public void addNewRoom(Room room) {
        roomRepository.save(room);
    }
}
