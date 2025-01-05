package Controller;

import DBConnection.DBConnection;
import model.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class completeController {
    public static completeController instance;
    private completeController(){

    }

    public static completeController getInstance(){
        return instance==null?instance=new completeController():instance;
    }

    public List<task> getAllComTasks(int usrId){
        List<task> taskList = null;
        try {
            taskList = new ArrayList<>();
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from completedtasks where UserID='"+usrId+"'");

            while (rst.next()){
                taskList.add(new task(rst.getInt(1),rst.getInt(2),rst.getString(3), rst.getString(4)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return taskList;

    }

    public boolean remove(String taskName){
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM completedtasks WHERE TaskName = '"+taskName+"'");
            return stm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
