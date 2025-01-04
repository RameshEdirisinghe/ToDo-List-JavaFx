package Controller;

import DBConnection.DBConnection;


import java.sql.*;

public class MainController {
    public static MainController instance;

    private  MainController(){

    }

    public static MainController getInstance(){
        return instance==null?instance=new MainController():instance;
    }

    public boolean login(String userName,String Password){
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from Users");

            while (rst.next()){
                if (rst.getString(2).equals(userName)){
                    ResultSet rst1 = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from Users where Username='"+userName+"'");
                    if(rst.getString(4).equals(Password)){
                        return true;
                    }
                }

            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
