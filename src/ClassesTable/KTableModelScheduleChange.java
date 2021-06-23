/*
 * CarTableModel.java
 *
 * Created on 2005/01/17, 15:31
 */

package ClassesTable;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import MainLobby.MainForm_v2;

/**
 *
 * @author white fenics
 */
public class KTableModelScheduleChange extends AbstractTableModel {
    private int colnum=8;
    private int rownum;
    private String[] colNames={
        "Группа", "Дисциплина", "Преподаватель", "Пар сегодня", "Осталось пар в семестре", "Осталось пар на этой неделе", "Должно быть пар на этой неделе", "Баланс пар", 
    };
    private  ArrayList<String[]> ResultSets;
     /** Creates a new instance of FoodTableModel */
    public KTableModelScheduleChange(ResultSet rs) {
       ResultSets=new ArrayList<String[]>();  
       try{
        while(rs.next()){
                String[]   row={
                rs.getString("short_name"),rs.getString("name"),rs.getString("surname"), rs.getString("hours_today"), rs.getString("hours_count_s"), rs.getString("hours_count_t"), rs.getString("hours_t"), rs.getString("debt")
             };
                String[]   row1={
                 rs.getString("short_name"),rs.getString("name"),rs.getString("surname"), rs.getString("hours_today"), rs.getString("hours_count_s"), rs.getString("hours_count_d"), rs.getString("hours_d"), rs.getString("debt")
                };
             if (MainForm_v2.week==1){
            ResultSets.add(row);}
            else {
             ResultSets.add(row1);}
      }}
      catch(Exception e){
          e.printStackTrace(MainForm_v2.er);
          System.out.println("Ошибка в получение информации из базы данных для таблицы Расписание Замен");
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
    
}
