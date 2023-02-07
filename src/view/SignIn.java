package view;

import helper.DBConnector;
import model.User;

import java.sql.*;
import java.util.Scanner;

public class SignIn {
    private static final Scanner input = new Scanner(System.in);

    public boolean signIn() {
        boolean status = false;

        System.out.println("First Name: ");
        String first_name = input.nextLine();
        System.out.println("Last Name: ");
        String last_name = input.nextLine();
        System.out.println("User Name: ");
        String uname = input.nextLine();
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Password: ");
        String pass = input.nextLine();
        System.out.println("Password (Again): ");
        String passAgain = input.nextLine();
        System.out.println("Type: ");
        String type = input.nextLine();

        if(first_name==null || last_name==null || uname==null || email==null || pass==null || passAgain==null || type==null){
            System.out.println("Enter all fields!");
            status = false;
        } else {
            if(passAgain.equals(pass)) {
                if(addNewUser(first_name,last_name,uname,email,pass,type)) {
                    System.out.println("User added successfully.");
                    status = true;
                }
            }
        }

        return status;

    }

    private boolean addNewUser(String first_name, String last_name, String uname, String email, String pass,String type){
        String query = "INSERT INTO user (first_name,last_name,uname,email,pass,type) VALUES (?,?,?,?,?,?)";

        User findUser = User.getUser(uname, pass);
        if (findUser != null){
            System.out.println("This username has been taken. Please enter a different user.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,first_name);
            pr.setString(2,last_name);
            pr.setString(3,uname);
            pr.setString(4,email);
            pr.setString(5,pass);
            pr.setObject(6,type,Types.OTHER);


            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }

}
