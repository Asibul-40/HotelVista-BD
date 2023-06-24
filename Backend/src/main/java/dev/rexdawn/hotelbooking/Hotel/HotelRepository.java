package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.Hotel.Hotel;
import dev.rexdawn.hotelbooking.room.Room;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, ObjectId> {
    Optional<List<Hotel>>findByAddress(String address);
}
