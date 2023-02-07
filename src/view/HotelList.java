package view;

import model.Hotel;
import model.User;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class HotelList {
    private User user;

    public HotelList(User user) {
        this.user = user;
    }

    public void hotels(){
        Scanner input = new Scanner(System.in);

        System.out.println("Hotel Name \t Address \t City \t Region \t Email \t Phone Number \t Boarding House \t Star \t Service Spec");

        for(Hotel obj: Hotel.getHotelList()){

            System.out.println(
            obj.getName() + " \t " +
            obj.getAddress() + " \t " +
            obj.getCity() + " \t " +
            obj.getRegion() + " \t " +
            obj.getEmail() + " \t " +
            obj.getPhone_num() + " \t " +
            obj.getBoarding_house() + " \t " +

            obj.getStar() + " \t " +
            parseArray(obj.getService_spec())
            );
        }

        if(Objects.equals(user.getType(), "Employee")) {
            System.out.println("Do you want to add hotel? (Y-N)");
            if(Objects.equals(input.next(), "Y")) {
                HotelAdd hotelAdd = new HotelAdd();
                hotelAdd.hotelAdd();
            }
        }
    }
    private String parseArray(Array service_spec){
        StringBuilder stringBuilder = new StringBuilder();
        String[] temp;
        try {
            temp = (String[]) service_spec.getArray();
            for (String s:temp) {
                stringBuilder.append(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
