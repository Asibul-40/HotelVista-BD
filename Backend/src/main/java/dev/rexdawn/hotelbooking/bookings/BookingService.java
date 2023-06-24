package dev.rexdawn.hotelbooking.bookings;

import dev.rexdawn.hotelbooking.room.Room;
import dev.rexdawn.hotelbooking.room.RoomRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    public List<Booking> allBookings()
    {
        return bookingRepository.findAll();
    }
    public void addNewBooking(Booking booking) {
        bookingRepository.save(booking);
    }
    public List<ObjectId> findAvailableRoomsOnCheckInDate(Date checkInDate) {
        List<Booking> bookings = bookingRepository.findAll();
        List<Room> rooms=roomRepository.findAll();
        List<ObjectId> unavailableRoomIds = bookings.stream()
                .filter(booking -> checkInDate.after(booking.getCheckInDate()) && checkInDate.before(booking.getCheckOutDate()))
                .map(Booking::getRoomId)
                .collect(Collectors.toList());

        List<ObjectId> allRoomIds = rooms.stream()
                .map(Room::getId)
                .distinct()
                .collect(Collectors.toList());

        return allRoomIds.stream()
                .filter(id -> !unavailableRoomIds.contains(id))
                .collect(Collectors.toList());
    }

}
