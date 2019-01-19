/*
* Welcome to HappyBusSAM Number of Tickets Available are: 2
* Welcome to HappyBusJack Number of Tickets Available are: 2
* Sorry Mr.Jack... The number of seats requested(2) are not available
* CONGRATSSAM... The number of seats requested(2) are BOOKED
*
* It is confusing. Jack find that Tickets are available, but failed.
* */
public class HappyBus {
    public static void main(String[] args) {
        BusReservation br = new BusReservation();
        PassengerThread pt1 = new PassengerThread(2, br, "SAM");
        PassengerThread pt2 = new PassengerThread(2, br, "Jack");
        pt1.start();
        pt2.start();
    }
}
