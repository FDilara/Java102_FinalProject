package model;

import helper.DBConnector;

import java.sql.*;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String uname;
    private String email;
    private String pass;
    private String type;

    public User() {}

    public User(int id, String first_name, String last_name, String uname, String email, String pass, String type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.type = type;
    }


    public static User getUser(String uname_email, String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname=? AND pass=?";
        if (uname_email.contains("@")){
            query = "SELECT * FROM user WHERE email=? AND pass=?";
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname_email);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new User(rs.getInt("id"),rs.getString("first_name"),
                        rs.getString("last_name"),rs.getString("uname"),
                        rs.getString("email"),rs.getString("pass"),rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
