package dev.rexdawn.hotelbooking.Hotel;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, ObjectId> {
    Optional<List<Hotel>>findByCity(String city);
    Optional<Hotel> findByName(String name);
    Optional<Hotel> findById(String id);

    void deleteById(String id);
}
