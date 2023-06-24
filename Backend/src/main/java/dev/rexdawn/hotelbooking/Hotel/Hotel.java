package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.room.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection="hotels")
@Data // extension of Lombok. works for All getter/setter/toString methods for private property.
@AllArgsConstructor // All private var arguments will be associated for constructor call.
@NoArgsConstructor
public class Hotel {
    @Id
    private ObjectId id;
    private String hotelId;
    private String name;
    private String address;
    private String city;
    private String desc;
    private String imageUrl;

    @DocumentReference // manual reference relationship
    private List<Room>rooms;

    private List<Object> reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
