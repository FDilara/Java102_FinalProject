package view;

import model.Hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class HotelAdd {

    public void hotelAdd() {
        Scanner input = new Scanner(System.in);

        String name = input.nextLine();
        String address = input.nextLine();
        String city = input.nextLine();
        String region = input.nextLine();
        String email = input.nextLine();
        String phone = input.nextLine();
        String star = input.nextLine();
        String boardingHouse = input.nextLine();
        ArrayList<String> serviceSpecList = new ArrayList<>(); // {"", "", ""}
        serviceSpecList.add(input.nextLine());
        serviceSpecList.add(input.nextLine());
        serviceSpecList.add(input.nextLine());

        Hotel.add(name, address, city, region, email, phone, star, boardingHouse, serviceSpecList);
    }

}
