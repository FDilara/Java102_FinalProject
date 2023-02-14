package view;

import model.User;
import helper.DBConnector;

import java.sql.*;
import java.util.Scanner;

public class ReservationList {

    private User user;

    public ReservationList(User user) {
        this.user = user;
    }

    public void reservations() {
        Scanner input = new Scanner(System.in);

        System.out.println("Name-Surname \t Room Number \t ");

        String query = "SELECT * FROM reservation";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int i = 0;
                System.out.println(
                rs.getString("guest_name") + " \t " +
                rs.getInt("room_id") + " \t " +
                rs.getString("contact_name") + " \t " +
                rs.getString("contact_phone") + " \t " +
                rs.getString("rez_note")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
