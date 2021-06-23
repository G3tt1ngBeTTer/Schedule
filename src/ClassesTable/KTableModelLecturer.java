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
 * @author noniko
 */
public class KTableModelLecturer extends AbstractTableModel {
    private int colnum=7;
    private int rownum;
    private String[] colNames={
        "Айди преподавателя","Фамилия","Имя","Отчество","Статус","Приорететный кабинет","Вещи необходимые в кабинете"
    };
    private  ArrayList<String[]> ResultSets;
     /** Creates a new instance of FoodTableModel */
    public KTableModelLecturer(ResultSet rs) {
       ResultSets=new ArrayList<String[]>();  
       try{
        while(rs.next()){
                    String[] row={
                rs.getString("id_Lecturer"),rs.getString("surname"),rs.getString("name"),rs.getString("second_name"),rs.getString("status"),rs.getString("priority_kabinet"),rs.getString("need_in_kabinet")                         
             };
            ResultSets.add(row);
          }   
      }
      catch(Exception e){
          System.out.println("Ошибка в получение информации из базы данных для таблицы Преподаватели");
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
