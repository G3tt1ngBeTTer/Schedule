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
public class KTableModelKabinet extends AbstractTableModel {
    private int colnum=7;
    private int rownum;
    private String[] colNames={
        "Номер кабинета","Статус","Компьютеры","Доска","Проектор","Спортзал","Мастерские"};
    private  ArrayList<String[]> ResultSets;
     /** Creates a new instance of FoodTableModel */
    public KTableModelKabinet(ResultSet rs) {
       ResultSets=new ArrayList<String[]>();  
       try{
        while(rs.next()){
            String fc="";
            String fd="";
            String fp="";
            String fs="";
            String fm="";
            if (rs.getBoolean("flag_computers")) fc="Да"; else fc="Нет";
            if (rs.getBoolean("flag_docs")) fd="Да"; else fd="Нет";
            if (rs.getBoolean("flag_projector")) fp="Да"; else fp="Нет";
            if (rs.getBoolean("flag_sportzal")) fs="Да"; else fs="Нет";
            if (rs.getBoolean("flag_master")) fm="Да"; else fm="Нет";
                    String[] row={
                rs.getString("number_of_kabinet"),rs.getString("kabinet_status"),fc,fd,fp,fs,fm
                    };
            ResultSets.add(row);
          }   
      }
      catch(Exception e){
          System.out.println("Ошибка в получение информации из базы данных для таблицы Кабинеты");
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
