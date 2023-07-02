package dev.rexdawn.hotelbooking.room;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    Room findById(ObjectId Id);
    void deleteById(ObjectId Id);
}
