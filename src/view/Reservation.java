package view;

import helper.DBConnector;
import java.sql.*;
import java.util.Scanner;

public class Reservation {

    public void reservationInfo() {
        Scanner input = new Scanner(System.in);

        String contactName = input.nextLine();
        String contactReservationNote = input.nextLine();
        String contactPhone = input.nextLine();
        String contactEmail = input.nextLine();
        String customerName = input.nextLine();
        String customerTc = input.nextLine();
        int hotelId = input.nextInt();
        int roomId = input.nextInt();
        String checkIn = input.nextLine();
        String checkOut = input.nextLine();

        addReservation(contactName, contactPhone, contactReservationNote, contactEmail, customerName, customerTc, hotelId, roomId, checkIn, checkOut);
    }

    private boolean addReservation(String contactName, String contactPhone, String contactReservationNote, String contactEmail,String customerName, String customerTc, int hotel_id, int room_id, String checkIn, String checkOut){
        String query = "INSERT INTO reservation " + "(contact_name,contact_phone,contact_email,rez_note," +
                "guest_name,guest_nation,guest_identity,hotel_id,room_id,check_in,check_out)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,contactName);
            pr.setString(2,contactPhone);
            pr.setString(3,contactReservationNote);
            pr.setString(4,contactEmail);
            pr.setString(5,customerName);
            pr.setString(6,customerTc);
            pr.setInt(7,hotel_id);
            pr.setInt(8,room_id);
            pr.setDate(9, Date.valueOf(checkIn));
            pr.setDate(10, Date.valueOf(checkOut));

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
