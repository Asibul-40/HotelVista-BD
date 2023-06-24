package dev.rexdawn.hotelbooking.Hotel;

import dev.rexdawn.hotelbooking.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review addReview(Review userReview, String hotelId){
//        Review review = reviewRepository.insert(new Review(userReview));
//        System.out.println("Here is REVIEW: "+review);

        mongoTemplate.update(Hotel.class)
                .matching(Criteria.where("hotelId").is(hotelId))
                .apply(new Update().push("reviews").value(userReview))
                .first();

        return userReview;
    }
}
