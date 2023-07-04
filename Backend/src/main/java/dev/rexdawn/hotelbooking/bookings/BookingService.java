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
    public List<String> findAvailableRoomsOnDate(Date checkInDate,Date checkOutDate) {
        List<Booking> bookings = bookingRepository.findAll();
        List<Room> rooms=roomRepository.findAll();
        List<String> unavailableRoomIds = bookings.stream()
                .filter(booking -> (checkInDate.after(booking.getCheckInDate()) && checkInDate.before(booking.getCheckOutDate()))
                        ||(checkOutDate.after(booking.getCheckInDate()) && checkOutDate.before(booking.getCheckOutDate()))
                        ||(booking.getCheckInDate().after(checkInDate) && booking.getCheckInDate().before(checkOutDate))
                        ||(booking.getCheckOutDate().after(checkInDate) && booking.getCheckOutDate().before(checkOutDate)))
                .map(Booking::getRoomId)
                .collect(Collectors.toList());

        List<String> allRoomIds = rooms.stream()
                .map(Room::getId)
                .distinct()
                .collect(Collectors.toList());

        return allRoomIds.stream()
                .filter(id -> !unavailableRoomIds.contains(id))
                .collect(Collectors.toList());
    }

}
