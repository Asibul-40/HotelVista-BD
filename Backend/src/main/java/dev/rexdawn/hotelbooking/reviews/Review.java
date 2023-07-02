package dev.rexdawn.hotelbooking.reviews;

import dev.rexdawn.hotelbooking.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "hotelReview")
public class Review {
    @Id
    private ObjectId id;
    private String rating;
    private String review;

//    public Review(Review userReview) {
//        this.rating = userReview.rating;
//        this.review = userReview.review;
//    }
}
