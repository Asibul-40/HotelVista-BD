package dev.rexdawn.hotelbooking.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Document(collection = "rooms")
@Data
@TypeAlias("room")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private ObjectId id;
    private String roomId;
    private int price;
    private int maxPeople;
    private String desc;

}
