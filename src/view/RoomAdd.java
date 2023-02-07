package view;

import helper.Config;
import model.Hotel;
import model.Room;
import model.User;

import java.util.Objects;
import java.util.Scanner;

public class RoomAdd {

    private Hotel hotel;

    public void roomAdd(String hotelName, User user) {
        Scanner input = new Scanner(System.in);

        if(Objects.equals(user.getType(), "Employee")) {
            hotel = Hotel.getHotelByName(hotelName);

            for(String type : Config.ROOM_TYPE_LIST) {
                System.out.print(type + " ");
            }

            String roomType = input.nextLine();
            int bedNum = input.nextInt();
            int stock = input.nextInt();
            boolean tv = input.nextBoolean();
            boolean till = input.nextBoolean();


            Room.add(roomType, bedNum, stock, tv, till, hotel.getId());
        }

    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
