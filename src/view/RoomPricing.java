package view;

import helper.Config;
import model.Hotel;
import model.User;
import helper.DBConnector;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class RoomPricing {
    private Hotel hotel;

    public void pricing(String hotelName, User user) {
        Scanner input = new Scanner(System.in);

        if(Objects.equals(user.getType(), "Employee")) {
            hotel = Hotel.getHotelByName(hotelName);
            String query = "INSERT INTO room_price (pension_type,hotel_id,price) VALUES (?,?,?)";

            try {
                PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                for(String type : Config.PENSION_TYPES) {
                    System.out.print(type + " Price: ");
                    int price = input.nextInt();
                    pr.setString(1, type);
                    pr.setInt(2, hotel.getId());
                    pr.setInt(3, price);
                }
                pr.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
