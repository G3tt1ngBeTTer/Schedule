/*
 * CarTableModel.java
 *
 * Created on 2005/01/17, 15:31
 */

package ClassesTable;

import Database.MyDBConnection;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noniko
 */
public class KTableModelNag extends AbstractTableModel {
    private int colnum=6;
    private int rownum;
    private String[] colNames={
        "Дисциплина","Группа", "Кол-во пар в верхней неделе", "Кол-во пар в нижней неделе", "Учебная практика", "Производственная практика"};
    private  ArrayList<String[]> ResultSets;
     /** Creates a new instance of FoodTableModel */
    public KTableModelNag(ResultSet rs) {
        mdbc = new MyDBConnection();
        mdbc.init();
        Connection conn = mdbc.getMyConnection();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(KTableModelDispline.class.getName()).log(Level.SEVERE, null, ex);
        }
       ResultSets=new ArrayList<String[]>();  
       try{
      while(rs.next()){
            PreparedStatement pst=mdbc.myConnection.prepareStatement("SELECT name FROM `discipline` WHERE discipline.id_Discipline=?");
            pst.setString(1,rs.getString("id_Discipline"));
            ResultSet rsk=pst.executeQuery();
            String name="";
            while (rsk.next()){
            name=rsk.getString("name");
            }
            int index=0;
            index=name.lastIndexOf(" ");
            if (index!=0){
            name=name.substring(0, index);}
            String st="";
            if (rs.getBoolean("st_practic")==true){
            st="Да";
            } else {
            st="Нет";}
            String pr="";
            if (rs.getBoolean("pr_practic")==true){
            pr="Да";
            } else {
            pr="Нет";
            }
                    String[] row={
                name,rs.getString("short_name"),rs.getString("hours_of_top_week"),rs.getString("hours_of_down_week"), st, pr
             };
            ResultSets.add(row);
          }   
      }
      catch(Exception e){
          System.out.println("Ошибка в получение информации из базы данных для таблицы Нагрузки");
          e.printStackTrace();
            }
    }
    public Object getValueAt(int rowindex, int columnindex) {
    String[] row=ResultSets.get(rowindex);
    return row[columnindex];
    }
    public int getRowCount() {
    return ResultSets.size();
    }
    public int getColumnCount() {
    return colnum;
    }
    public String getColumnName(int param) {
    return colNames[param];
    }
    private MyDBConnection mdbc;
    private java.sql.Statement stmt;
}
