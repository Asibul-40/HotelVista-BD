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
    private String hotelId;
    private String title;
    private Number price;
    private Number maxPeople;
    private String desc;

    public ObjectId getId() {
        return id;
    }

    public String gethotelId() {
        return hotelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Number maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
