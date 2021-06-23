/*
 * CarTableModel.java
 *
 * Created on 2005/01/17, 15:31
 */

package ClassesTable;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author whitefenics
 */
public class KTableModelScheduleFull extends AbstractTableModel {
    private int colnum=6;
    private int rownum;
    private String[] colNames={
        "Неделя","День","Пара", "Группа", "Дисциплина", "Преподаватель"
    };
    private  ArrayList<String[]> ResultSets;
     /** Creates a new instance of FoodTableModel */
    public KTableModelScheduleFull(ResultSet rs) {
       ResultSets=new ArrayList<String[]>();  
       try{
        while(rs.next()){
                    String[] row={
                rs.getString("week"),rs.getString("day"),rs.getString("para"), rs.getString("gpt"), rs.getString("name"), rs.getString("surname")
             };
            ResultSets.add(row);
          }   
      }
      catch(Exception e){
          System.out.println("Ошибка в получение информации из базы данных для таблицы Расписание");
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
