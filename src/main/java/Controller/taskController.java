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
            stm.setInt(1,id);
            stm.setString(2,taskName);
            stm.setString(3,null);
            stm.setString(4,Date);

            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<task> getAllTasks(int usrId){
        List<task> taskList = null;
        try {
            taskList = new ArrayList<>();
            System.out.println(usrId);
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from tasks where UserID='"+usrId+"'");

            while (rst.next()){
                taskList.add(new task(rst.getInt(1),rst.getInt(2),rst.getString(3), rst.getString(5)));

            }
            System.out.println(taskList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return taskList;

    }
    public String getUserName(int usrId){
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select Username from users where UserID='"+usrId+"'");
            if (rst.next()) {
                return rst.getString(1);
            }
            return "hi";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean remove(String taskName){
        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM tasks WHERE TaskName = '"+taskName+"'");
            return stm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getTaskCount(int userId){
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from tasks where UserID='"+userId+"'");
            int count=0;
            while (rst.next()){
                count++;
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean addCompleteTable(String taskName){
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from tasks WHERE TaskName ='"+taskName+"'");

            if(rst.next()){
                task tsk1 = new task(rst.getInt(1),rst.getInt(2),rst.getString(3), rst.getString(5));
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO completedtasks(UserID, TaskName) VALUES (?,?)");
                stm.setInt(1,tsk1.getUserId());
                stm.setString(2, tsk1.getTaskName());

                return stm.executeUpdate()>0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
