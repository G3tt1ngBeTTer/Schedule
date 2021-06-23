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
public class KTableModelGroups extends AbstractTableModel {
    private int colnum=5;
    private int rownum;
    private String[] colNames={
        "Айди группы","Сокращенное наименование","Полное наименование","Количество часов в неделе","Статус"
    };
    private  ArrayList<String[]> ResultSets;
     /** Creates a new instance of FoodTableModel */
    public KTableModelGroups(ResultSet rs) {
       ResultSets=new ArrayList<String[]>();  
       try{
        while(rs.next()){
                    String[] row={
                rs.getString("id_Groups"),rs.getString("short_name"),rs.getString("long_name"), rs.getString("max_hours"),rs.getString("group_status")                         
             };
            ResultSets.add(row);
          }   
      }
      catch(Exception e){
          System.out.println("Ошибка в получение информации из базы данных для таблицы Группы");
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
