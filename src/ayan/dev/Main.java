package ayan.dev;

public class Main {
    public static void main(String[] args) {
        System.out.println("=====Welcome---Banking---System=====");


        if (DBConnection.getConnection()!=null) {
            System.out.println("DataBase Connect successfully!!!");
        }else{
            System.out.println("DataBase Not Connect :( ");
        }

    }
}
