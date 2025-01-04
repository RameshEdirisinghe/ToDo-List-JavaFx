package Controller;

import DBConnection.DBConnection;
import model.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class taskController {
    public static taskController instance;

    private taskController(){

    }

    public static taskController getInstance() {
        return instance==null?instance=new taskController():instance;
    }

    public boolean saveToDb(int id,String taskName,String Date) {
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO tasks(UserID, TaskName,Description, DueDate) VALUES (?,?,?,?)");
            stm.setInt(1,1);
            stm.setString(2,taskName);
            stm.setString(3,null);
            stm.setString(4,Date);

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<task> getAllTasks(){
        List<task> taskList = null;
        try {
            taskList = new ArrayList<>();
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from tasks");

            while (rst.next()){
                taskList.add(new task(rst.getInt(1),rst.getInt(2),rst.getString(3), rst.getString(5)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return taskList;

    }

    public boolean remove(String taskName){
        return false;
    }
}
