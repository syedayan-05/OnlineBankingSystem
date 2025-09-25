package ayan.dev;

public class Main {
    public static void main(String[] args) {
        System.out.println("=====Welcome---Banking---System=====");


        if (DBConnection.getConnection()!=null) {
            System.out.println("DataBase Connect successfully!!!");

            //testing the user...
            User user = new User(0,"Ayan","example123@email.com","example12345");
            UserDAO.registerUser(user);


            //testing the login user....
            UserDAO.loginUser("example123@email.com","example12345"); // correct detail
            UserDAO.loginUser("example123@email.com","example12345"); // incorrect detail

        }else{
            System.out.println("DataBase Not Connect :( ");
        }


    }
}
