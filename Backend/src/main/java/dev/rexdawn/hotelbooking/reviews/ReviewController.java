package dev.rexdawn.hotelbooking.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<Review>AddNewReview(@RequestBody Review review, @RequestParam String hotelId){
        System.out.println("REVIEW = "+review+ "   HotelID = "+hotelId);
        return new ResponseEntity<Review>(reviewService.addReview(review, hotelId), HttpStatus.OK);
    }
}
