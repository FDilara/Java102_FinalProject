import model.Hotel;
import model.Room;
import model.User;
import view.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        list.add("Free Wifi");
        list.add("SPA");
        list.add("Fitness center");

        Hotel.add(
                "Çam",
                "Kızılcahamam",
                "Ankara",
                "Kızılcahamam",
                "xxxx@gmail.com",
                "03123256598",
                "5 Star",
                "Ultra All Inclusive",
                list
        );

        Hotel.getHotelById(2);
        Hotel.getHotelByName("Çam");
        System.out.println(Hotel.getHotelList());


        SignIn signIn = new SignIn();
        if(signIn.signIn()) {
            Login login = new Login();
            User user = login.login();
            switch (user.getType()) {
                case "Employee":
                    HotelList hotelList = new HotelList(user);
                    hotelList.hotels();
                    System.out.println("Enter the Hotel name:");
                    String hotelName = input.nextLine();
                    RoomAdd roomAdd = new RoomAdd();
                    roomAdd.roomAdd(hotelName, user);
                    RoomPricing roomPricing = new RoomPricing();
                    roomPricing.pricing(hotelName, user);
                    ReservationList reservationList = new ReservationList(user);
                    reservationList.reservations();
                    break;
                case "Customer":
                    HotelList hotelList1 = new HotelList(user);
                    hotelList1.hotels();
                    break;
                default:
                    break;
            }
        }

    }
}

