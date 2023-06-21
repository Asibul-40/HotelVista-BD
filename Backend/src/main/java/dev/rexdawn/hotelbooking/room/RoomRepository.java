package dev.rexdawn.hotelbooking.room;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, ObjectId> {
}
