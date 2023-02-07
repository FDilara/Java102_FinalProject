package model;

import helper.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String city;
    private String region;
    private String email;
    private String phone_num;
    private String star;
    private String boarding_house;
    private Array service_spec;

    public Hotel() {
    }

    public Hotel(int id, String name, String address, String city, String region, String email, String phone_num, String star, String boarding_house, Array service_spec) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.email = email;
        this.phone_num = phone_num;
        this.star = star;
        this.boarding_house = boarding_house;
        this.service_spec = service_spec;
    }

    public static boolean add(String name, String address, String city, String region, String email, String phone_num, String star, String boarding_house, ArrayList<String> service_spec) {
        String query = "INSERT INTO hotel (name, address, city , region, email, phone_num, star, boarding_house, service_spec) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Array array = DBConnector.getInstance().createArrayOf("VARCHAR", service_spec.toArray());
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, address);
            pr.setString(3, city);
            pr.setString(4, region);
            pr.setString(5, email);
            pr.setString(6, phone_num);
            pr.setString(7, star);
            pr.setString(8, boarding_house);
            pr.setArray(9, array);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Hotel getHotelById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE id = " + id;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()){
                obj =  new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("city"),rs.getString("region"), rs.getString("email"),rs.getString("phone_num"),
                        rs.getString("star"), rs.getString("boarding_house"), rs.getArray("service_spec"));
            }

            assert obj != null;
            System.out.println("-------------- " +obj.getRegion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Hotel getHotelByName(String hotelName){
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hotelName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj =  new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("city"),rs.getString("region"), rs.getString("email"),rs.getString("phone_num"),
                        rs.getString("star"), rs.getString("boarding_house"), rs.getArray("service_spec"));
            }

            assert obj != null;
            System.out.println("-------------- " +obj.getRegion());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Hotel> getHotelList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        String sql = "SELECT * FROM hotel";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obj =  new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),
                        rs.getString("city"),rs.getString("region"), rs.getString("email"),rs.getString("phone_num"),
                        rs.getString("star"), rs.getString("boarding_house"), rs.getArray("service_spec"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getBoarding_house() {
        return boarding_house;
    }

    public void setBoarding_house(String boarding_house) {
        this.boarding_house = boarding_house;
    }

    public Array getService_spec() {
        return service_spec;
    }

    public void setService_spec(Array service_spec) {
        this.service_spec = service_spec;
    }
}
