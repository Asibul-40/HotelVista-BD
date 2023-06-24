package dev.rexdawn.hotelbooking.bookings;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, ObjectId> {

}
