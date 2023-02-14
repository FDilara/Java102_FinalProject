package model;

import helper.DBConnector;

import java.sql.*;
import java.sql.PreparedStatement;

public class Room {
    private int id;
    private String room_type;
    private int bed_num;
    private int stock;
    private boolean tv;
    private boolean till;

    public Room(int id, String room_type, int bed_num, int stock, boolean tv, boolean till) {
        this.id = id;
        this.room_type = room_type;
        this.bed_num = bed_num;
        this.stock = stock;
        this.tv = tv;
        this.till = till;
    }

    public static boolean add(String room_type, int bed_num, int stock, boolean tv, boolean safe, int hotel_id){
        String query = "INSERT INTO room (room_type, bed_num, stock, tv, safe, hotel_id) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,room_type);
            pr.setInt(2,bed_num);
            pr.setInt(3,stock);
            pr.setBoolean(4,tv);
            pr.setBoolean(5,safe);
            pr.setInt(6,hotel_id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int isAddRoomType(String roomType, int hotelId){
        String query = "SELECT id FROM room WHERE room_type = ? AND hotel_id = ?";
        int id = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,roomType);
            pr.setInt(2,hotelId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    public static int getHotelIdByRoomId(int id){
        String query = "SELECT hotel_id from room WHERE id = " + id;
        int result = 0;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            result = rs.getInt("hotel_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getBed_num() {
        return bed_num;
    }

    public void setBed_num(int bed_num) {
        this.bed_num = bed_num;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isTill() {
        return till;
    }

    public void setTill(boolean till) {
        this.till = till;
    }
}
