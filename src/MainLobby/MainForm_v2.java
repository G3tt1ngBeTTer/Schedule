/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLobby;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.table.TableModel;
import javax.swing.ImageIcon;
import Database.MyDBConnection;
import ClassesTable.KTableModelDispline;
import ClassesTable.KTableModelGroups;
import ClassesTable.KTableModelKabinet;
import ClassesTable.KTableModelLecturer;
import ClassesTable.KTableModelNag;
import ClassesTable.KTableModelScheduleFull;
import ClassesTable.KTableModelScheduleChange;
import ClassesTable.KTableModelScheduleChangeTrue;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import javax.swing.JTable;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author G3tt1ng_BeTTer
 */
public class MainForm_v2 extends javax.swing.JFrame {

    public ResultSet rs;

    public MainForm_v2() throws Exception {
        Calendar calendar = Calendar.getInstance();
        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        int Month = calendar.get(Calendar.MONTH);
        day_n = Day;
        month = Month;
        new File("C:\\Schedulesrc").mkdirs();
        er = new PrintWriter(error, "CP1251");
        mdbc = new MyDBConnection();
        mdbc.init();
        Connection conn = mdbc.getMyConnection();
        stmt = conn.createStatement();
        ImageIcon icon = new ImageIcon("src/images/service_logo.png");
        setIconImage(icon.getImage());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        vert = sSize.height;
        hor = sSize.width;
        hor_small = hor / 3;
        vert_small = vert / 2;
        hor_mid = hor / 2;
        vert_mid = vert / 2;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        String url="jdbc:mysql://localhost:3306/schedule_death_db?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
         String user="root";
         String password="";
        conn=DriverManager.getConnection(url, user, password);
        initComponents();


    }

    public ResultSet getResultFromDispline() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT id_Discipline, d.name as NAME123, concat(surname,' ',L.name,' ',second_name) as Lecturer123 FROM `discipline` d,`lecturer` L where Lecturer=id_Lecturer");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }
        return rs;
    }

    public ResultSet getResultFromLecturer() {
        ResultSet rs_teacher = null;
        try {
            rs_teacher = stmt.executeQuery("Select * from Lecturer");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }
        return rs_teacher;
    }

    public ResultSet getResultFromGroups() {
        ResultSet rs_groups = null;
        try {
            rs_groups = stmt.executeQuery("Select * from Groups");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }
        return rs_groups;
    }

    public ResultSet getResultFromNag() {
        ResultSet rs_nag = null;
        try {
            rs_nag = stmt.executeQuery("Select nag.id_Discipline, name, short_name, hours_of_top_week, hours_of_down_week, hours_count_t, hours_count_d, st_practic, pr_practic from nag, groups, discipline WHERE nag.id_Discipline=discipline.id_Discipline and nag.id_Groups=groups.id_Groups Order BY  nag.id_Groups");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }

        return rs_nag;
    }

    public ResultSet getResultFromKabinet() {
        ResultSet rs_kab = null;
        try {
            rs_kab = stmt.executeQuery("Select * from kabinet");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }
        return rs_kab;
    }

    public ResultSet getResultFromSchedule() {
        ResultSet rs_schedule = null;
        try {
            rs_schedule = stmt.executeQuery("SELECT week, day, para, groups.short_name as gpt, discipline.name, lecturer.surname FROM schedule_true, groups, discipline, Lecturer WHERE schedule_true.Group=groups.id_Groups and schedule_true.Displine=discipline.id_Discipline and schedule_true.Lecturer=lecturer.id_Lecturer ORDER BY week, day_n, gpt, para");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }
        return rs_schedule;
    }

    public ResultSet getResultFromScheduleChangeTrue() {
        ResultSet rs_schedule_change_true = null;
        try {
            rs_schedule_change_true = stmt.executeQuery("SELECT `Group` as gp, `Disp` as dp, `Lecturer` as lp, `para` as pr, `kabinet` as kab FROM `schedule_change_true` Group BY schedule_change_true.Group, schedule_change_true.para");
        } catch (SQLException e) {
            e.printStackTrace(er);
        }
        return rs_schedule_change_true;
    }

    public static boolean CheckString(String fullname, String shortname) {
        if (fullname.equals("") | shortname.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        GroupsDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        AddButtonForGroup = new javax.swing.JButton();
        EditButtonForGroup = new javax.swing.JButton();
        DeleteButtonForGroup = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        GroupComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LongNameField = new javax.swing.JTextField();
        ShortNameField = new javax.swing.JTextField();
        IdGroupField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        GroupsTable = new javax.swing.JTable();
        TeachersDialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        AddButtonForTeachers = new javax.swing.JButton();
        EditButtonForTeachers = new javax.swing.JButton();
        DeleteButtonForTeachers = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        WorkStatus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LongNameField1 = new javax.swing.JTextField();
        ShortNameField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        SecondNameField = new javax.swing.JTextField();
        prioritykabField = new javax.swing.JTextField();
        PCFlag = new javax.swing.JCheckBox();
        DeskFlag = new javax.swing.JCheckBox();
        ShowFlag = new javax.swing.JCheckBox();
        SportFlag = new javax.swing.JCheckBox();
        MasterFlag = new javax.swing.JCheckBox();
        jLabel36 = new javax.swing.JLabel();
        IdTeacherField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TeachersTable = new javax.swing.JTable();
        StatusTeacherDialog = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        WorkStatus1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TeacherListBox = new javax.swing.JComboBox<>();
        NagDialog = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        AddButtonForGroup1 = new javax.swing.JButton();
        EditButtonForGroup1 = new javax.swing.JButton();
        DeleteButtonForGroup1 = new javax.swing.JButton();
        DCBOX = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        DownWeekHoursField = new javax.swing.JTextField();
        GroupNagBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        TopWeekHoursField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        RemainsHourField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        praktickcheckbox = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        DispTableForever = new javax.swing.JTable();
        DispDailog = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        AddButtonForGroup2 = new javax.swing.JButton();
        EditButtonForDisp = new javax.swing.JButton();
        DeleteButtonForGroup2 = new javax.swing.JButton();
        SecondTeacherBox = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        DispNameFull1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        DispTeacher2 = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        IdDispField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DispTableForever1 = new javax.swing.JTable();
        KabinetDialog = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        AddButtonForTeachers1 = new javax.swing.JButton();
        EditButtonForTeachers1 = new javax.swing.JButton();
        DeleteButtonForTeachers1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        StatusKabBox = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        PCFlag1 = new javax.swing.JCheckBox();
        DeskFlag1 = new javax.swing.JCheckBox();
        ShowFlag1 = new javax.swing.JCheckBox();
        SportFlag1 = new javax.swing.JCheckBox();
        MasterFlag1 = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        KabinetTable = new javax.swing.JTable();
        NewPracticDialog = new javax.swing.JDialog();
        jPanel18 = new javax.swing.JPanel();
        AddButtonForGroup4 = new javax.swing.JButton();
        DispCoBox = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        GroupPracticBox = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        daysField = new javax.swing.JTextField();
        prType = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        MessageDialog = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        MessLabel1 = new javax.swing.JLabel();
        goodDialog = new javax.swing.JDialog();
        jPanel25 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        EasyButton = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        weekBox = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        dayBox = new javax.swing.JComboBox<>();
        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        GroupsButton = new javax.swing.JButton();
        TeachersButton = new javax.swing.JButton();
        DispButton = new javax.swing.JButton();
        HardButton = new javax.swing.JButton();
        KabinetButton = new javax.swing.JButton();

        GroupsDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        GroupsDialog.setTitle("Группы");
        GroupsDialog.setMinimumSize(new java.awt.Dimension(900, 480));

        jPanel4.setLayout(new java.awt.BorderLayout());
        GroupsDialog.getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel7.setLayout(new java.awt.GridLayout(2, 1, 4, 4));

        jPanel3.setBackground(new java.awt.Color(153, 204, 0));

        AddButtonForGroup.setText("Добавить ");
        AddButtonForGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonForGroupActionPerformed(evt);
            }
        });

        EditButtonForGroup.setText("Изменить");
        EditButtonForGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonForGroupActionPerformed(evt);
            }
        });

        DeleteButtonForGroup.setText("Удалить");
        DeleteButtonForGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonForGroupActionPerformed(evt);
            }
        });

        jLabel3.setText("Сокращенное название");

        GroupComboBox.setBackground(new java.awt.Color(0, 255, 204));
        GroupComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Учится", "В карантине", " " }));
        GroupComboBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        GroupComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Полное название");

        jLabel5.setText("Статус");

        IdGroupField.setEnabled(false);

        jLabel33.setText("Айди группы, для изменения или удаления");

        jLabel57.setText("Количество часов в неделю");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LongNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(ShortNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(GroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel33))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(AddButtonForGroup)
                                .addGap(59, 59, 59)
                                .addComponent(EditButtonForGroup)
                                .addGap(38, 38, 38)
                                .addComponent(DeleteButtonForGroup))
                            .addComponent(jLabel5)
                            .addComponent(jLabel57))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(IdGroupField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(278, 278, 278))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LongNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ShortNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(GroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)
                        .addComponent(IdGroupField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButtonForGroup)
                    .addComponent(EditButtonForGroup)
                    .addComponent(DeleteButtonForGroup))
                .addGap(51, 51, 51))
        );

        jPanel7.add(jPanel3);

        ResultSet rs_groups=getResultFromGroups();
        GroupsTable.setModel(new KTableModelGroups(rs_groups));
        try {rs_groups.close();} catch (Exception e){ System.out.println(e); }
        GroupsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GroupsTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GroupsTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(GroupsTable);

        jPanel7.add(jScrollPane1);

        GroupsDialog.getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        TeachersDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TeachersDialog.setTitle("Преподаватели");
        TeachersDialog.setMinimumSize(new java.awt.Dimension(820, 560));
        TeachersDialog.getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        jPanel6.setLayout(new java.awt.BorderLayout(15, 15));

        jPanel10.setLayout(new java.awt.GridLayout(2, 1, 4, 4));

        jPanel5.setBackground(new java.awt.Color(153, 204, 0));

        AddButtonForTeachers.setText("Добавить ");
        AddButtonForTeachers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonForTeachersActionPerformed(evt);
            }
        });

        EditButtonForTeachers.setText("Изменить");
        EditButtonForTeachers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonForTeachersActionPerformed(evt);
            }
        });

        DeleteButtonForTeachers.setText("Удалить");
        DeleteButtonForTeachers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonForTeachersActionPerformed(evt);
            }
        });

        jLabel6.setText("Вещи необходимые в кабинете:");

        WorkStatus.setBackground(new java.awt.Color(0, 255, 204));
        WorkStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Работает", "В коммандировке", "В отпуске", "На больничном" }));
        WorkStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Предпочитаемый кабинет");

        jLabel8.setText("Статус");

        jLabel9.setText("Фамилия");

        jLabel10.setText("Имя");

        jLabel11.setText("Отчество (при наличии)");

        SecondNameField.setText("Отсутсвует");

        PCFlag.setText("Компьютеры");

        DeskFlag.setText("Доска");

        ShowFlag.setText("Проектор");

        SportFlag.setText("Спортзал");

        MasterFlag.setText("Мастерские");

        jLabel36.setText("Айди, используется для удаления или изменения");

        IdTeacherField.setText(" ");
        IdTeacherField.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(DeskFlag)
                        .addGap(10, 10, 10)
                        .addComponent(ShowFlag)
                        .addGap(10, 10, 10)
                        .addComponent(MasterFlag))
                    .addComponent(jLabel6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SecondNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(WorkStatus, 0, 1, Short.MAX_VALUE)
                                    .addComponent(LongNameField1)
                                    .addComponent(ShortNameField1)
                                    .addComponent(prioritykabField))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IdTeacherField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(AddButtonForTeachers)
                        .addGap(96, 96, 96)
                        .addComponent(EditButtonForTeachers)
                        .addGap(101, 101, 101)
                        .addComponent(DeleteButtonForTeachers))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(PCFlag)
                        .addGap(28, 28, 28)
                        .addComponent(SportFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(LongNameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(IdTeacherField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(ShortNameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(SecondNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(WorkStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addComponent(prioritykabField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PCFlag)
                    .addComponent(SportFlag))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeskFlag)
                    .addComponent(MasterFlag)
                    .addComponent(ShowFlag))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButtonForTeachers)
                    .addComponent(EditButtonForTeachers)
                    .addComponent(DeleteButtonForTeachers))
                .addContainerGap())
        );

        jPanel10.add(jPanel5);

        ResultSet rs_teacher=getResultFromLecturer();
        TeachersTable.setModel(new KTableModelLecturer(rs_teacher));
        try {rs_teacher.close();}
        catch (Exception e){
            System.out.println(e);
        }
        TeachersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeachersTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TeachersTableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(TeachersTable);

        jPanel10.add(jScrollPane2);

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        TeachersDialog.getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        StatusTeacherDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        StatusTeacherDialog.setTitle("Изменение статуса преподавателя");
        StatusTeacherDialog.setMinimumSize(new java.awt.Dimension(415, 248));

        jPanel8.setBackground(new java.awt.Color(153, 204, 0));

        jLabel13.setText("Преподаватель");

        jButton2.setText("Изменить статус преподавателя");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        WorkStatus1.setBackground(new java.awt.Color(0, 255, 204));
        WorkStatus1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Работает", "В коммандировке", "В отпуске", "На больничном" }));
        WorkStatus1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        WorkStatus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WorkStatus1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Статус");

        jLabel15.setText("Преподаватель");

        String a="";
        a="Select surname, name, second_name from Lecturer";
        try{
            ResultSet rs;
            rs=stmt.executeQuery(a);
            while (rs.next()) {
                String b=rs.getString("surname");
                String c=rs.getString("name");
                String d=rs.getString("second_name");
                String l = "";
                l=b+" "+c.charAt(0)+". "+d.charAt(0)+".";
                TeacherListBox.addItem(l);
                l="";
            }}
            catch(Exception e){
                e.printStackTrace();
            }

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(139, 139, 139)
                            .addComponent(jLabel13))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(WorkStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TeacherListBox, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(21, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(99, 99, 99))
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel13)
                    .addGap(20, 20, 20)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(TeacherListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(WorkStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(41, 41, 41)
                    .addComponent(jButton2)
                    .addContainerGap(46, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout StatusTeacherDialogLayout = new javax.swing.GroupLayout(StatusTeacherDialog.getContentPane());
            StatusTeacherDialog.getContentPane().setLayout(StatusTeacherDialogLayout);
            StatusTeacherDialogLayout.setHorizontalGroup(
                StatusTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            StatusTeacherDialogLayout.setVerticalGroup(
                StatusTeacherDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            NagDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            NagDialog.setTitle("Нагрузки");
            NagDialog.setMinimumSize(new java.awt.Dimension(900, 560));

            jPanel9.setBackground(new java.awt.Color(153, 204, 0));

            AddButtonForGroup1.setText("Добавить ");
            AddButtonForGroup1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    AddButtonForGroup1ActionPerformed(evt);
                }
            });

            EditButtonForGroup1.setText("Изменить");
            EditButtonForGroup1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    EditButtonForGroup1ActionPerformed(evt);
                }
            });

            DeleteButtonForGroup1.setText("Удалить");
            DeleteButtonForGroup1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DeleteButtonForGroup1ActionPerformed(evt);
                }
            });

            String a100="";
            a100="SELECT discipline.`id_Discipline` as id, discipline.name as dn, surname as s, lecturer.name as n, lecturer.second_name as sn FROM discipline, lecturer WHERE discipline.Lecturer=lecturer.id_Lecturer order by dn";
            try{
                ResultSet rs8;
                rs8=stmt.executeQuery(a100);
                String DC=" ";
                while (rs8.next()) {
                    DC=rs8.getString("dn");
                    int index=0;
                    index=DC.lastIndexOf(" ");
                    DC=DC.substring(0, index);
                    DCBOX.addItem(rs8.getString("id")+" - "+DC+" - "+rs8.getString("s")+" "+rs8.getString("n").charAt(0)+". "+rs8.getString("sn").charAt(0)+".");
                    DC="123";
                }}
                catch(Exception e){
                    e.printStackTrace();
                }
                DCBOX.setBackground(new java.awt.Color(0, 255, 204));
                DCBOX.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                DCBOX.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        DCBOXActionPerformed(evt);
                    }
                });

                jLabel17.setText("Группа");

                jLabel18.setText("Дисциплина");

                String a2="";
                a2="Select short_name from Groups";
                try{
                    ResultSet rs1;
                    rs1=stmt.executeQuery(a2);
                    while (rs1.next()) {
                        String l2=rs1.getString("short_name");
                        GroupNagBox.addItem(l2);
                        l2="";
                    }}
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    GroupNagBox.setBackground(new java.awt.Color(0, 255, 204));
                    GroupNagBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                    jLabel16.setText("Нижняя неделя");

                    jLabel24.setText("Верхняя неделя");

                    jLabel25.setText("Часов в семестре");

                    jLabel26.setText("часов");

                    jLabel27.setText("часов");

                    praktickcheckbox.setText("Учебная практика");
                    praktickcheckbox.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            praktickcheckboxItemStateChanged(evt);
                        }
                    });

                    jCheckBox1.setText("Производственная практика");
                    jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            jCheckBox1ItemStateChanged(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                    jPanel9.setLayout(jPanel9Layout);
                    jPanel9Layout.setHorizontalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(163, 163, 163)
                                    .addComponent(EditButtonForGroup1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                    .addComponent(DeleteButtonForGroup1)
                                    .addGap(37, 37, 37))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(GroupNagBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(38, 38, 38)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(DownWeekHoursField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TopWeekHoursField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(RemainsHourField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(DCBOX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(praktickcheckbox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBox1)
                            .addGap(41, 41, 41))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(AddButtonForGroup1)
                            .addGap(155, 155, 155))
                    );
                    jPanel9Layout.setVerticalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel17)
                                .addComponent(GroupNagBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(praktickcheckbox)
                                .addComponent(jCheckBox1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TopWeekHoursField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26)
                                .addComponent(jLabel24))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DownWeekHoursField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel27))
                            .addGap(3, 3, 3)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(RemainsHourField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(35, 35, 35)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(AddButtonForGroup1)
                                .addComponent(EditButtonForGroup1)
                                .addComponent(DeleteButtonForGroup1))
                            .addGap(29, 29, 29))
                    );

                    ResultSet rs_nag=getResultFromNag();
                    DispTableForever.setModel(new KTableModelNag(rs_nag));
                    try {rs_nag.close();}  catch (Exception e){  System.out.println(e);  }
                    DispTableForever.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            DispTableForeverMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            DispTableForeverMousePressed(evt);
                        }
                    });
                    jScrollPane3.setViewportView(DispTableForever);

                    javax.swing.GroupLayout NagDialogLayout = new javax.swing.GroupLayout(NagDialog.getContentPane());
                    NagDialog.getContentPane().setLayout(NagDialogLayout);
                    NagDialogLayout.setHorizontalGroup(
                        NagDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    NagDialogLayout.setVerticalGroup(
                        NagDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NagDialogLayout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                    );

                    DispDailog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    DispDailog.setTitle("Дисциплины");
                    DispDailog.setMinimumSize(new java.awt.Dimension(700, 550));

                    jPanel11.setBackground(new java.awt.Color(153, 204, 0));

                    AddButtonForGroup2.setText("Добавить ");
                    AddButtonForGroup2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            AddButtonForGroup2ActionPerformed(evt);
                        }
                    });

                    EditButtonForDisp.setText("Изменить");
                    EditButtonForDisp.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            EditButtonForDispActionPerformed(evt);
                        }
                    });

                    DeleteButtonForGroup2.setText("Удалить");
                    DeleteButtonForGroup2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            DeleteButtonForGroup2ActionPerformed(evt);
                        }
                    });

                    SecondTeacherBox.setBackground(new java.awt.Color(0, 255, 204));
                    SecondTeacherBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
                    SecondTeacherBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                    SecondTeacherBox.setEnabled(false);
                    SecondTeacherBox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            SecondTeacherBoxActionPerformed(evt);
                        }
                    });

                    jLabel20.setText("Полное название");

                    jLabel21.setText("Преподаватель");
                    jLabel21.setEnabled(false);

                    jLabel22.setText("Преподаватель");

                    String ad="";
                    ad="Select surname, name, second_name from Lecturer";
                    try{
                        ResultSet rs;
                        rs=stmt.executeQuery(ad);
                        while (rs.next()) {
                            String b=rs.getString("surname");
                            String c=rs.getString("name");
                            String d=rs.getString("second_name");
                            String l = "";
                            l=b+" "+c.charAt(0)+". "+d.charAt(0)+".";
                            DispTeacher2.addItem(l);
                            l="";
                        }}
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        DispTeacher2.setBackground(new java.awt.Color(0, 255, 204));
                        DispTeacher2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                        jCheckBox2.setText("Два преподавателя");
                        jCheckBox2.setEnabled(false);
                        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jCheckBox2ActionPerformed(evt);
                            }
                        });

                        IdDispField.setEnabled(false);

                        jLabel23.setText("Айди дисциплины, для удаления или изменения");

                        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                        jPanel11.setLayout(jPanel11Layout);
                        jPanel11Layout.setHorizontalGroup(
                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(AddButtonForGroup2)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel21)
                                                    .addComponent(jLabel22))
                                                .addGap(19, 19, 19)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(106, 106, 106)
                                                .addComponent(EditButtonForDisp)
                                                .addGap(75, 75, 75)
                                                .addComponent(DeleteButtonForGroup2))
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(SecondTeacherBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 147, Short.MAX_VALUE)
                                                    .addComponent(DispTeacher2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox2))))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel23))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(DispNameFull1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(IdDispField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(135, Short.MAX_VALUE))
                        );
                        jPanel11Layout.setVerticalGroup(
                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addContainerGap(20, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(IdDispField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(DispNameFull1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(DispTeacher2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(SecondTeacherBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AddButtonForGroup2)
                                    .addComponent(EditButtonForDisp)
                                    .addComponent(DeleteButtonForGroup2))
                                .addContainerGap())
                        );

                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                        jPanel12.setLayout(jPanel12Layout);
                        jPanel12Layout.setHorizontalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 4, Short.MAX_VALUE)
                        );
                        jPanel12Layout.setVerticalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                        );

                        ResultSet rs=getResultFromDispline();
                        DispTableForever1.setModel(new KTableModelDispline(rs));
                        try {rs.close();}
                        catch (Exception e){
                            System.out.println(e);
                        }
                        DispTableForever1.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                DispTableForever1MouseClicked(evt);
                            }
                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                DispTableForever1MousePressed(evt);
                            }
                        });
                        jScrollPane4.setViewportView(DispTableForever1);

                        javax.swing.GroupLayout DispDailogLayout = new javax.swing.GroupLayout(DispDailog.getContentPane());
                        DispDailog.getContentPane().setLayout(DispDailogLayout);
                        DispDailogLayout.setHorizontalGroup(
                            DispDailogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DispDailogLayout.createSequentialGroup()
                                .addGroup(DispDailogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        DispDailogLayout.setVerticalGroup(
                            DispDailogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DispDailogLayout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(DispDailogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))
                        );

                        KabinetDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        KabinetDialog.setTitle("Кабинеты");
                        KabinetDialog.setMinimumSize(new java.awt.Dimension(800, 550));

                        jPanel14.setBackground(new java.awt.Color(153, 204, 0));

                        AddButtonForTeachers1.setText("Добавить ");
                        AddButtonForTeachers1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                AddButtonForTeachers1ActionPerformed(evt);
                            }
                        });

                        EditButtonForTeachers1.setText("Изменить");
                        EditButtonForTeachers1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                EditButtonForTeachers1ActionPerformed(evt);
                            }
                        });

                        DeleteButtonForTeachers1.setText("Удалить");
                        DeleteButtonForTeachers1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                DeleteButtonForTeachers1ActionPerformed(evt);
                            }
                        });

                        jLabel30.setText("Вещи в кабинете");

                        StatusKabBox.setBackground(new java.awt.Color(0, 255, 204));
                        StatusKabBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Работает", "В ремонте", "Занят под мероприятие", "Экзамен" }));
                        StatusKabBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                        jLabel32.setText("Статус");

                        PCFlag1.setText("Компьютеры");

                        DeskFlag1.setText("Доска");

                        ShowFlag1.setText("Проектор");

                        SportFlag1.setText("Спортзал");

                        MasterFlag1.setText("Мастерские");

                        jLabel29.setText("Номер кабинета");

                        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                        jPanel14.setLayout(jPanel14Layout);
                        jPanel14Layout.setHorizontalGroup(
                            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel32))
                                        .addGap(89, 89, 89)
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(StatusKabBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel30)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGap(349, 349, 349)
                                        .addComponent(DeleteButtonForTeachers1))
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(MasterFlag1)
                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                    .addComponent(PCFlag1)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(DeskFlag1)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(ShowFlag1))
                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                    .addGap(13, 13, 13)
                                                    .addComponent(AddButtonForTeachers1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(EditButtonForTeachers1)))
                                            .addGap(18, 18, 18)
                                            .addComponent(SportFlag1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(357, Short.MAX_VALUE))
                        );
                        jPanel14Layout.setVerticalGroup(
                            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(StatusKabBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PCFlag1)
                                    .addComponent(SportFlag1)
                                    .addComponent(ShowFlag1)
                                    .addComponent(DeskFlag1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MasterFlag1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AddButtonForTeachers1)
                                    .addComponent(EditButtonForTeachers1)
                                    .addComponent(DeleteButtonForTeachers1))
                                .addGap(125, 125, 125))
                        );

                        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                        jPanel15.setLayout(jPanel15Layout);
                        jPanel15Layout.setHorizontalGroup(
                            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                        );
                        jPanel15Layout.setVerticalGroup(
                            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 391, Short.MAX_VALUE)
                        );

                        ResultSet rs_kabinet=getResultFromKabinet();
                        KabinetTable.setModel(new KTableModelKabinet (rs_kabinet));
                        try {rs_kabinet.close();}  catch (Exception e){  System.out.println(e);  }
                        KabinetTable.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                KabinetTableMouseClicked(evt);
                            }
                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                KabinetTableMousePressed(evt);
                            }
                        });
                        jScrollPane5.setViewportView(KabinetTable);

                        javax.swing.GroupLayout KabinetDialogLayout = new javax.swing.GroupLayout(KabinetDialog.getContentPane());
                        KabinetDialog.getContentPane().setLayout(KabinetDialogLayout);
                        KabinetDialogLayout.setHorizontalGroup(
                            KabinetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KabinetDialogLayout.createSequentialGroup()
                                .addGroup(KabinetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        KabinetDialogLayout.setVerticalGroup(
                            KabinetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(KabinetDialogLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(KabinetDialogLayout.createSequentialGroup()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        );

                        NewPracticDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        NewPracticDialog.setTitle("Практики");
                        NewPracticDialog.setMinimumSize(new java.awt.Dimension(400, 250));

                        jPanel18.setBackground(new java.awt.Color(153, 204, 0));

                        AddButtonForGroup4.setText("Подтвердить");
                        AddButtonForGroup4.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                AddButtonForGroup4ActionPerformed(evt);
                            }
                        });

                        DispCoBox.setBackground(new java.awt.Color(0, 255, 204));
                        DispCoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
                        DispCoBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                        DispCoBox.removeAllItems();
                        String adp="";
                        adp="SELECT DISTINCT nag.id_Discipline as id, discipline.name as dn FROM nag, discipline WHERE nag.id_Discipline=discipline.id_Discipline AND (nag.st_practic='1' OR nag.pr_practic='1')";
                        DispCoBox.setBackground(new java.awt.Color(0, 255, 204));
                        DispCoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
                        DispCoBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                        DispCoBox.removeAllItems();
                        try{
                            ResultSet rsadp;
                            rsadp=stmt.executeQuery(adp);
                            while (rsadp.next()) {
                                String DN=rsadp.getString("dn");
                                int index=0;
                                index=DN.lastIndexOf(" ");
                                DN=DN.substring(0, index);
                                String ladp=rsadp.getString("id")+"  -  "+DN;
                                DispCoBox.addItem(ladp);
                                System.out.println(ladp);
                                ladp="";        }
                        }
                        catch(Exception e){
                            e.printStackTrace();        }
                        DispCoBox.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                DispCoBoxItemStateChanged(evt);
                            }
                        });
                        DispCoBox.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                DispCoBoxActionPerformed(evt);
                            }
                        });

                        jLabel37.setText("Преподаватель 1:");

                        jLabel38.setText("Группа");

                        String agp="";
                        agp="SELECT DISTINCT nag.id_Groups as ig , groups.short_name as sn FROM nag, groups WHERE nag.id_Groups=groups.id_Groups AND (nag.st_practic='1' OR nag.pr_practic='1') and groups.group_status='Учится'";
                        try{
                            ResultSet rsagp;
                            rsagp=stmt.executeQuery(agp);
                            while (rsagp.next()) {
                                String lagp=rsagp.getString("ig")+" - "+rsagp.getString("sn");
                                GroupPracticBox.addItem(lagp);
                                System.out.println(lagp);
                                lagp="";        }}
                        catch(Exception e){         e.printStackTrace();        }
                        GroupPracticBox.setBackground(new java.awt.Color(0, 255, 204));
                        GroupPracticBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                        GroupPracticBox.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                GroupPracticBoxItemStateChanged(evt);
                            }
                        });

                        jLabel48.setText("Дисциплина");

                        jLabel54.setText("Преподаватель 2:");

                        jComboBox6.setBackground(new java.awt.Color(0, 255, 204));
                        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
                        jComboBox6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                        jComboBox6.removeAllItems();
                        String lp="";
                        lp="SELECT DISTINCT lecturer.id_Lecturer as il, lecturer.surname as ln1, lecturer.name as ln2, lecturer.second_name as ln3 FROM nag, lecturer, discipline WHERE nag.id_Discipline=discipline.id_Discipline AND discipline.Lecturer=lecturer.id_Lecturer AND (nag.st_practic='1' OR nag.pr_practic='1') AND lecturer.status='Работает'";
                        try{
                            ResultSet rslp;
                            rslp=stmt.executeQuery(lp);
                            while (rslp.next()) {
                                String llp=rslp.getString("il")+"   -   "+rslp.getString("ln1")+" "+rslp.getString("ln2")+" "+rslp.getString("ln3");
                                jComboBox6.addItem(llp);
                                System.out.println(llp);
                                llp="";        }}
                        catch(Exception e){         e.printStackTrace();        }
                        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboBox6ItemStateChanged(evt);
                            }
                        });
                        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBox6ActionPerformed(evt);
                            }
                        });

                        jComboBox7.setBackground(new java.awt.Color(0, 255, 204));
                        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
                        jComboBox7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                        jComboBox7.setEnabled(false);
                        jComboBox7.removeAllItems(); 
                        String lp2="";                 lp2="SELECT DISTINCT lecturer.id_Lecturer as il, lecturer.surname as ln1, lecturer.name as ln2, lecturer.second_name as ln3 FROM lecturer WHERE lecturer.status='Работает'";                try{                 ResultSet rslp2;                 rslp2=stmt.executeQuery(lp2);                 while (rslp2.next()) {                 String llp2=rslp2.getString("il")+"   -   "+rslp2.getString("ln1")+" "+rslp2.getString("ln2")+" "+rslp2.getString("ln3");                 jComboBox7.addItem(llp2);         System.out.println(llp2);         llp2="";        }}                 catch(Exception e){         e.printStackTrace();        }
                        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboBox7ItemStateChanged(evt);
                            }
                        });
                        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBox7ActionPerformed(evt);
                            }
                        });

                        jCheckBox3.setText("Два преподавателя");
                        jCheckBox3.setEnabled(false);
                        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jCheckBox3ItemStateChanged(evt);
                            }
                        });

                        jLabel55.setText("Оставшееся кол-во дней");

                        jLabel56.setText("Тип практики");

                        daysField.setEnabled(false);

                        prType.setEnabled(false);

                        jButton21.setText("Завершить");
                        jButton21.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton21ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                        jPanel18.setLayout(jPanel18Layout);
                        jPanel18Layout.setHorizontalGroup(
                            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel38)
                                            .addComponent(jLabel37)
                                            .addComponent(jLabel48)
                                            .addComponent(jLabel55)
                                            .addComponent(jLabel54))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(daysField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(prType, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(GroupPracticBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE)
                                                    .addComponent(DispCoBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jComboBox7, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jComboBox6, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jCheckBox3))
                                            .addComponent(jLabel61)))
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(AddButtonForGroup4)
                                        .addGap(39, 39, 39)
                                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                        );
                        jPanel18Layout.setVerticalGroup(
                            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(GroupPracticBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DispCoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55)
                                    .addComponent(daysField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(prType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AddButtonForGroup4)
                                    .addComponent(jButton21))
                                .addContainerGap(118, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout NewPracticDialogLayout = new javax.swing.GroupLayout(NewPracticDialog.getContentPane());
                        NewPracticDialog.getContentPane().setLayout(NewPracticDialogLayout);
                        NewPracticDialogLayout.setHorizontalGroup(
                            NewPracticDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        NewPracticDialogLayout.setVerticalGroup(
                            NewPracticDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        MessageDialog.setSize(hor_mid, vert_small);
                        MessageDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        MessageDialog.setTitle("Обратная связь");
                        MessageDialog.setMinimumSize(new java.awt.Dimension(250, 200));

                        MessLabel1.setText("message");

                        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                        jPanel22.setLayout(jPanel22Layout);
                        jPanel22Layout.setHorizontalGroup(
                            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MessLabel1)
                                .addContainerGap(344, Short.MAX_VALUE))
                        );
                        jPanel22Layout.setVerticalGroup(
                            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MessLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(264, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout MessageDialogLayout = new javax.swing.GroupLayout(MessageDialog.getContentPane());
                        MessageDialog.getContentPane().setLayout(MessageDialogLayout);
                        MessageDialogLayout.setHorizontalGroup(
                            MessageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        MessageDialogLayout.setVerticalGroup(
                            MessageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        goodDialog.setTitle("Электронные таблицы и расписание замен");
                        goodDialog.getContentPane().setLayout(new java.awt.BorderLayout(15, 15));

                        jPanel25.setBackground(new java.awt.Color(153, 255, 51));
                        jPanel25.setLayout(new java.awt.GridLayout(9, 1, 5, 5));

                        jLabel60.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel60.setText("Создание основного расписания");
                        jPanel25.add(jLabel60);

                        EasyButton.setText("Сделать основное расписание");
                        EasyButton.addAncestorListener(new javax.swing.event.AncestorListener() {
                            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                                EasyButtonAncestorAdded(evt);
                            }
                            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                            }
                            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                            }
                        });
                        EasyButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                EasyButtonActionPerformed(evt);
                            }
                        });
                        jPanel25.add(EasyButton);

                        jButton7.setText("Сформировать основное расписание");
                        jButton7.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton7ActionPerformed(evt);
                            }
                        });
                        jPanel25.add(jButton7);

                        jButton6.setText("Преобразовать основное расписание");
                        jButton6.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton6ActionPerformed(evt);
                            }
                        });
                        jPanel25.add(jButton6);

                        jLabel59.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel59.setText("Создание расписания замен, только после создания основного расписания, если это не было сделано ранее");
                        jPanel25.add(jLabel59);

                        jButton16.setText("Сделать расписание замен");
                        jButton16.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton16ActionPerformed(evt);
                            }
                        });
                        jPanel25.add(jButton16);

                        jButton17.setText("Преобразовать расписание замен");
                        jButton17.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton17ActionPerformed(evt);
                            }
                        });
                        jPanel25.add(jButton17);

                        jButton19.setText("Подтвердить последнее сделанное расписание замен");
                        jButton19.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton19ActionPerformed(evt);
                            }
                        });
                        jPanel25.add(jButton19);

                        jPanel26.setBackground(new java.awt.Color(153, 255, 51));
                        jPanel26.setLayout(new java.awt.GridLayout(1, 2, 15, 15));

                        jLabel31.setText("Неделя");
                        jPanel26.add(jLabel31);

                        weekBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "верхняя", "нижняя" }));
                        jPanel26.add(weekBox);

                        jLabel58.setText("День недели");
                        jPanel26.add(jLabel58);

                        dayBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" }));
                        jPanel26.add(dayBox);

                        jPanel25.add(jPanel26);

                        goodDialog.getContentPane().add(jPanel25, java.awt.BorderLayout.CENTER);

                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                        setTitle("Главное меню");

                        mainPanel.setBackground(new java.awt.Color(204, 0, 204));

                        jPanel1.setBackground(new java.awt.Color(153, 255, 51));

                        jLabel1.setText("Выберете режим работы");

                        jButton5.setText("Экспорт расписания в электронные таблицы");
                        jButton5.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton5ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jPanel2.setBackground(new java.awt.Color(204, 255, 102));

                        jLabel2.setText("Настройки");

                        GroupsButton.setText("Группы");
                        GroupsButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                GroupsButtonActionPerformed(evt);
                            }
                        });

                        TeachersButton.setText("Преподаватели");
                        TeachersButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TeachersButtonActionPerformed(evt);
                            }
                        });

                        DispButton.setText("Дисциплины");
                        DispButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                DispButtonActionPerformed(evt);
                            }
                        });

                        HardButton.setText("Нагрузки");
                        HardButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                HardButtonActionPerformed(evt);
                            }
                        });

                        KabinetButton.setText("Кабинеты");
                        KabinetButton.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                KabinetButtonActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(GroupsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TeachersButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DispButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(HardButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(KabinetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(GroupsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TeachersButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DispButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HardButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(KabinetButton)
                                .addContainerGap(94, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
                        mainPanel.setLayout(mainPanelLayout);
                        mainPanelLayout.setHorizontalGroup(
                            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        mainPanelLayout.setVerticalGroup(
                            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void EasyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EasyButtonActionPerformed
        /**
         * Создаём папку, если её нет
         */
        new File("C:\\Schedulesrc").mkdirs();
        /**
         * Прописываем путь к исполняем файлам и создаём переменные
         */
        File filetopweek = new File("src/ScheduleC#/netcoreapp3.1t/ScheduleDownWeek.exe");
        File filedownweek = new File("src/ScheduleC#/netcoreapp3.1d/ScheduleDownWeek.exe");
        String groupsline = "";
        String teacherline = "";
        try {
            int ig;
            int htw;
            int it;
            /**
             * Создаём два файла для групп и для преподавателей
             */
            File filewG = new File("C://Schedulesrc/GroupsTopWeek.txt");
            PrintWriter w1g = null;
            File fileT = new File("C://Schedulesrc/TeachersTopWeek.txt");
            PrintWriter w1t = null;
            /**
             * Делаем запрос и получаем значения
             */
            PreparedStatement preparedStatementtrue = mdbc.myConnection.prepareStatement("SELECT nag.id_Groups, discipline.Lecturer, hours_of_top_week, "
                    + "hours_of_down_week FROM nag, discipline WHERE nag.id_Discipline=discipline.id_Discipline and st_practic='0' and pr_practic='0'");
            ResultSet rs = preparedStatementtrue.executeQuery();
            while (rs.next()) {
                ig = rs.getInt("nag.id_Groups");
                it = rs.getInt("discipline.Lecturer");
                htw = rs.getInt("hours_of_top_week");
                /**
                 * Записываем их в строку, а строки в файл
                 */
                for (int j = 0; j < htw; j++) {
                    groupsline = groupsline + ig + ",";
                    teacherline = teacherline + it + ",";
                }
            }
            groupsline = groupsline.substring(0, groupsline.length() - 1);
            teacherline = teacherline.substring(0, teacherline.length() - 1);
            w1g = new PrintWriter(filewG, "CP1251");
            w1g.println(groupsline);
            w1g.close();
            w1t = new PrintWriter(fileT, "CP1251");
            w1t.println(teacherline);
            w1t.close();
            System.out.println("Верхняя неделя успешно построенна");
            /**
             * Реакция на ошибки
             */
        } catch (IOException ex) {
            Logger.getLogger(MainForm_v2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные, если это не поможет вызовете администратора");
        }

        try {
            int ig;
            int hdw;
            int it;
            groupsline = "";
            teacherline = "";
            // File filewG = new File("src/Date/Groups.txt");
            File filewG = new File("C://Schedulesrc/GroupsDownWeek.txt");
            PrintWriter w2g = null;
            //File fileT = new File("src/Date/Teachers.txt");
            File fileT = new File("C://Schedulesrc/TeachersDownWeek.txt");
            PrintWriter w2t = null;
            PreparedStatement preparedStatementtrue = mdbc.myConnection.prepareStatement("SELECT nag.id_Groups, discipline.Lecturer, hours_of_top_week, hours_of_down_week FROM nag, discipline WHERE nag.id_Discipline=discipline.id_Discipline and st_practic='0' and pr_practic='0' ");
            ResultSet rs1 = preparedStatementtrue.executeQuery();
            while (rs1.next()) {
                ig = rs1.getInt("nag.id_Groups");
                it = rs1.getInt("discipline.Lecturer");
                hdw = rs1.getInt("hours_of_down_week");
                for (int j = 0; j < hdw; j++) {
                    groupsline = groupsline + ig + ",";
                    teacherline = teacherline + it + ",";
                }
            }
            groupsline = groupsline.substring(0, groupsline.length() - 1);
            teacherline = teacherline.substring(0, teacherline.length() - 1);
            w2g = new PrintWriter(filewG, "CP1251");
            w2g.println(groupsline);
            w2g.close();
            w2t = new PrintWriter(fileT, "CP1251");
            w2t.println(teacherline);
            w2t.close();
            System.out.println("Нижняя неделя успешно построенна");
            Desktop.getDesktop().open(filetopweek);
            Desktop.getDesktop().open(filedownweek);
            System.out.println("Программа открыта и запущена");
        } catch (IOException ex) {
            Logger.getLogger(MainForm_v2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_EasyButtonActionPerformed

    private void GroupsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupsButtonActionPerformed
        try {
            GroupsDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_GroupsButtonActionPerformed

    private void TeachersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TeachersButtonActionPerformed
        try {
            TeachersDialog.setVisible(true);
            TeachersDialog.setSize(hor, vert);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_TeachersButtonActionPerformed

    private void DCBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DCBOXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DCBOXActionPerformed

    private void SecondTeacherBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecondTeacherBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SecondTeacherBoxActionPerformed

    private void KabinetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KabinetButtonActionPerformed
        try {
            KabinetDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_KabinetButtonActionPerformed

    private void EasyButtonAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_EasyButtonAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_EasyButtonAncestorAdded

    private void DispCoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DispCoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DispCoBoxActionPerformed

    private void DispButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DispButtonActionPerformed
        try {
            DispDailog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_DispButtonActionPerformed

    private void HardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HardButtonActionPerformed
        try {
            NagDialog.setVisible(true);
            NagDialog.setSize(hor, vert);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_HardButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            goodDialog.setSize(hor_mid, vert_mid);
            goodDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        try {
            if (i == 0) {
                i = 1;
                SecondTeacherBox.setEnabled(true);
            } else if (i == 1) {
                i = 0;
                SecondTeacherBox.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed
    /**
     * Добавление в таблицу группы
     */
    private void AddButtonForGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonForGroupActionPerformed
        try {
            /**
             * Забираем переменные из полей
             */
            String LN = LongNameField.getText();
            String SN = ShortNameField.getText();
            String ST = GroupComboBox.getSelectedItem().toString();
            String kolvo = jTextField3.getText();
            /**
             * Выполняем запрос
             */
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Insert INTO groups SET long_name = ?, "
                    + "short_name = ?, group_status = ?, max_hours=?");
            preparedStatement.setString(1, LN);
            preparedStatement.setString(2, SN);
            preparedStatement.setString(3, ST);
            preparedStatement.setString(4, kolvo);
            preparedStatement.executeUpdate();
            /**
             * Перезапуск формы
             */
            GroupsDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            GroupsDialog.dispose();
            GroupsDialog.setVisible(true);
        } /**
         * Реакция на ошибки
         */
        catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные, если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_AddButtonForGroupActionPerformed

    private void GroupComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GroupComboBoxActionPerformed
    /**
     * Кнопка изменения групп
     */
    private void EditButtonForGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonForGroupActionPerformed
        /**
         * Заполняем переменные из полей
         */
        try {
            int ID = Integer.parseInt(IdGroupField.getText());
            String LN = LongNameField.getText();
            String SN = ShortNameField.getText();
            String ST = GroupComboBox.getSelectedItem().toString();
            String kolvo = jTextField3.getText();
            /**
             * Заполняем запрос и выполняем его
             */
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Update groups SET long_name = ?, "
                    + "short_name = ?, group_status = ?, max_hours=? where id_Groups = ?");
            preparedStatement.setString(1, LN);
            preparedStatement.setString(2, SN);
            preparedStatement.setString(3, ST);
            preparedStatement.setInt(5, ID);
            preparedStatement.setString(4, kolvo);
            preparedStatement.executeUpdate();
            /**
             * Перезапускаем форму
             */
            GroupsDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            GroupsDialog.dispose();
            GroupsDialog.setVisible(true);
        } /**
         * Реакция на ошибку
         */
        catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные,если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_EditButtonForGroupActionPerformed
    /**
     * Кнопка удаления группы
     */
    private void DeleteButtonForGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonForGroupActionPerformed
        /**
         * Забираем айди из поля
         */
        int ID = Integer.parseInt(IdGroupField.getText());
        try {
            /**
             * Создаём запрос и выполняем его
             */
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Delete from groups where id_Groups = ?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            GroupsDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            GroupsDialog.dispose();
            GroupsDialog.setVisible(true);
        } /**
         * Реакция на ошибку
         */
        catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные, если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_DeleteButtonForGroupActionPerformed
    /**
     * Добавление в таблицу преподователи
     */
    private void AddButtonForTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonForTeachersActionPerformed
        try {
            String LN = LongNameField1.getText();
            String SN = ShortNameField1.getText();
            String SeN = SecondNameField.getText();
            String PK = prioritykabField.getText();
            String ST = WorkStatus.getSelectedItem().toString();
            Boolean FK = PCFlag.isSelected();
            Boolean FD = DeskFlag.isSelected();
            Boolean FS = ShowFlag.isSelected();
            Boolean FF = SportFlag.isSelected();
            Boolean FM = MasterFlag.isSelected();
            String Need_in_kab = "Без предпочтений,";
            int nik = 0;
            if (FM == false) {
                if (FF == false) {
                    if (FK == true) {
                        Need_in_kab = "";
                        Need_in_kab = Need_in_kab + "Компьютеры, ";
                        nik = 6;
                        if (FD == true) {
                            Need_in_kab = Need_in_kab + "Доска, ";
                            nik = 7;
                            if (FS == true) {
                                Need_in_kab = Need_in_kab + "Проектор,";
                                nik = 5;
                            }
                        }
                    } else {
                        if (FD == true) {
                            Need_in_kab = "";
                            Need_in_kab = Need_in_kab + "Доска, ";
                            nik = 8;
                            if (FS == true) {
                                Need_in_kab = Need_in_kab + "Проектор,";
                                nik = 4;
                            }
                        } else {
                            if (FS == true) {
                                Need_in_kab = "";
                                Need_in_kab = Need_in_kab + "Проектор, ";
                                nik = 3;
                            }
                        }
                    }
                } else {
                    Need_in_kab = "Спортзал,";
                    nik = 2;
                }
            } else {
                Need_in_kab = "Мастерские,";
                nik = 1;
            }
            Need_in_kab=Need_in_kab.substring(0, Need_in_kab.length()-1);
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("INSERT INTO `lecturer` (`surname`, `name`, `second_name`, `status`, `priority_kabinet`, `need_in_kabinet`, `need_in_kab_id`) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, LN);
            preparedStatement.setString(2, SN);
            preparedStatement.setString(3, SeN);
            preparedStatement.setString(5, PK);
            preparedStatement.setString(4, ST);
            preparedStatement.setString(6, Need_in_kab);
            preparedStatement.setInt(7, nik);
            preparedStatement.executeUpdate();
            TeachersDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            TeachersDialog.dispose();
            TeachersDialog.setVisible(true);
            TeachersDialog.setSize(hor, vert);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_AddButtonForTeachersActionPerformed
    /**
     * Изменения в базе для преподавателя
     */
    private void EditButtonForTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonForTeachersActionPerformed
        int ID = Integer.parseInt(IdTeacherField.getText());
        String LN = LongNameField1.getText();
        String SN = ShortNameField1.getText();
        String SeN = SecondNameField.getText();
        String PK = prioritykabField.getText();
        String ST = WorkStatus.getSelectedItem().toString();
        Boolean FK = PCFlag.isSelected();
        Boolean FD = DeskFlag.isSelected();
        Boolean FS = ShowFlag.isSelected();
        Boolean FF = SportFlag.isSelected();
        Boolean FM = MasterFlag.isSelected();
        String Need_in_kab = "Без предпочтений";
        int nik = 0;
        if (FM == false) {
            if (FF == false) {
                if (FK == true) {
                    Need_in_kab = "";
                    Need_in_kab = Need_in_kab + "Компьютеры,";
                    nik = 6;
                    if (FD == true) {
                        Need_in_kab = Need_in_kab + " Доска,";
                        nik = 7;
                        if (FS == true) {
                            Need_in_kab = Need_in_kab + " Проектор,";
                            nik = 5;
                        }
                    }
                } else {
                    if (FD == true) {
                        Need_in_kab = "";
                        Need_in_kab = Need_in_kab + "Доска,";
                        nik = 8;
                        if (FS == true) {
                            Need_in_kab = Need_in_kab + " Проектор,";
                            nik = 4;
                        }
                    } else {
                        if (FS == true) {
                            Need_in_kab = "";
                            Need_in_kab = Need_in_kab + "Проектор,";
                            nik = 3;
                        }
                    }
                }
            } else {
                Need_in_kab = "Спортзал,";
                nik = 2;
            }
        } else {
            Need_in_kab = "Мастерские,";
            nik = 1;
        }
        Need_in_kab=Need_in_kab.substring(0, Need_in_kab.length()-1);
        try {
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Update `lecturer` Set `surname`=?, `name`=?, `second_name`=?, `status`=?, `priority_kabinet`=?, `need_in_kabinet`=?, `need_in_kab_id`=?  where id_Lecturer=?");
            preparedStatement.setString(1, LN);
            preparedStatement.setString(2, SN);
            preparedStatement.setString(3, SeN);
            preparedStatement.setString(5, PK);
            preparedStatement.setString(4, ST);
            preparedStatement.setString(6, Need_in_kab);
            preparedStatement.setInt(8, ID);
            preparedStatement.setInt(7, nik);
            preparedStatement.executeUpdate();
            TeachersDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            TeachersDialog.dispose();
            TeachersDialog.setVisible(true);
            TeachersDialog.setSize(hor, vert);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_EditButtonForTeachersActionPerformed
    /**
     * Удаление из базы данных преподавателя
     */
    private void DeleteButtonForTeachersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonForTeachersActionPerformed
        try {
            int ID = Integer.parseInt(IdTeacherField.getText());
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Delete from `lecturer` where id_Lecturer = ?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            TeachersDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            TeachersDialog.dispose();
            TeachersDialog.setVisible(true);
            TeachersDialog.setSize(hor, vert);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_DeleteButtonForTeachersActionPerformed
    /**
     * Изменение статуса, в обход таблицы
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            int index = TeacherListBox.getSelectedItem().toString().indexOf(" ");
            String F = TeacherListBox.getSelectedItem().toString();
            String OF = F.substring(0, index);
            System.out.println(index);
            System.out.println(F);
            System.out.println(OF);
            String WorkSt = WorkStatus1.getSelectedItem().toString();

            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Update `lecturer` Set `status`=? where surname=?");
            preparedStatement.setString(2, OF);
            preparedStatement.setString(1, WorkSt);
            preparedStatement.executeUpdate();
            System.out.println("Успешно");
            getContentPane().removeAll();
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Добавление дисциплины
     */
    private void AddButtonForGroup2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonForGroup2ActionPerformed
        try {
            String DNF = DispNameFull1.getText();
            int index = DispTeacher2.getSelectedItem().toString().indexOf(" ");
            String F = DispTeacher2.getSelectedItem().toString();
            String OF = F.substring(0, index);
            String OD = F.substring(0, 1);
            System.out.print(F + " " + index + " " + OF);
            int Id = 0;

            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("Select id_Lecturer from lecturer where surname=?");
            preparedStatement1.setString(1, OF);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                Id = rs.getInt("id_Lecturer");
            }

            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Insert INTO discipline SET name = ?, Lecturer = ?");
            preparedStatement.setString(1, DNF+" "+OD);
            preparedStatement.setInt(2, Id);
            preparedStatement.executeUpdate();
            DispDailog.dispose();
            getContentPane().removeAll();
            initComponents();
            DispDailog.dispose();
            DispDailog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_AddButtonForGroup2ActionPerformed

    private void WorkStatus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WorkStatus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WorkStatus1ActionPerformed
    /**
     * Изменение дисциплины
     */
    private void EditButtonForDispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonForDispActionPerformed
        try {
            int IDD = Integer.parseInt(IdDispField.getText().toString());
            String DNF = DispNameFull1.getText();
            int index = DispTeacher2.getSelectedItem().toString().indexOf(" ");
            String F = DispTeacher2.getSelectedItem().toString();
            String OF = F.substring(0, index);
            String OD = F.substring(0, 1);
            int Id = 0;
            System.out.print(F + " " + index + " " + OF);

            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("Select id_Lecturer from lecturer where surname=?");
            preparedStatement1.setString(1, OF);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                Id = rs.getInt("id_Lecturer");
            }
            try {
                PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Update discipline SET name = ?, Lecturer = ? where id_Discipline=?");
                preparedStatement.setString(1, DNF+" "+OD);
                preparedStatement.setInt(2, Id);
                preparedStatement.setInt(3, IDD);
                preparedStatement.executeUpdate();
                DispDailog.dispose();
                getContentPane().removeAll();
                initComponents();
                DispDailog.dispose();
                DispDailog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                MessageDialog.setVisible(true);
                MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_EditButtonForDispActionPerformed
    /**
     * Удаление дисциплины из таблицы
     */
    private void DeleteButtonForGroup2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonForGroup2ActionPerformed
        try {
            int ID = Integer.parseInt(IdDispField.getText());
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Delete from `schedule_need_change` where schedule_need_change.Displine=?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            preparedStatement = mdbc.myConnection.prepareStatement("Delete from `schedule_true` where schedule_true.Displine=?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            preparedStatement = mdbc.myConnection.prepareStatement("Delete from `discipline` where id_Discipline= ?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();

            DispDailog.dispose();
            getContentPane().removeAll();
            initComponents();
            DispDailog.dispose();
            DispDailog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_DeleteButtonForGroup2ActionPerformed
    /**
     * Добавление кабинета
     */
    private void AddButtonForTeachers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonForTeachers1ActionPerformed
        try {
            int N = Integer.parseInt(jTextField1.getText().toString());
            String ST = StatusKabBox.getSelectedItem().toString();
            Boolean FK = PCFlag1.isSelected();
            Boolean FD = DeskFlag1.isSelected();
            Boolean FS = ShowFlag1.isSelected();
            Boolean FF = SportFlag1.isSelected();
            Boolean FM = MasterFlag1.isSelected();

            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("INSERT INTO `kabinet` (`number_of_kabinet`, `flag_computers`, `flag_docs`, `flag_projector`, `flag_sportzal`, `flag_master`,`kabinet_status`) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, N);
            preparedStatement.setBoolean(2, FK);
            preparedStatement.setBoolean(3, FD);
            preparedStatement.setBoolean(4, FS);
            preparedStatement.setBoolean(5, FF);
            preparedStatement.setBoolean(6, FM);
            preparedStatement.setString(7, ST);
            preparedStatement.executeUpdate();
            KabinetDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            KabinetDialog.dispose();
            KabinetDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_AddButtonForTeachers1ActionPerformed
    /**
     * Измение кабинеты
     */
    private void EditButtonForTeachers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonForTeachers1ActionPerformed
        try {
            int N = Integer.parseInt(jTextField1.getText().toString());
            String ST = StatusKabBox.getSelectedItem().toString();
            Boolean FK = PCFlag1.isSelected();
            Boolean FD = DeskFlag1.isSelected();
            Boolean FS = ShowFlag1.isSelected();
            Boolean FF = SportFlag1.isSelected();
            Boolean FM = MasterFlag1.isSelected();

            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("UPDATE `kabinet` Set `kabinet_status`=?, `flag_computers`=?, `flag_docs`=?, `flag_projector`=?, `flag_sportzal`=?, `flag_master`=? where `number_of_kabinet`=?");
            preparedStatement.setInt(7, N);
            preparedStatement.setBoolean(2, FK);
            preparedStatement.setBoolean(3, FD);
            preparedStatement.setBoolean(4, FS);
            preparedStatement.setBoolean(5, FF);
            preparedStatement.setBoolean(6, FM);
            preparedStatement.setString(1, ST);
            preparedStatement.executeUpdate();
            KabinetDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            KabinetDialog.dispose();
            KabinetDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_EditButtonForTeachers1ActionPerformed
    /**
     * Удаление кабинета
     */
    private void DeleteButtonForTeachers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonForTeachers1ActionPerformed
        try {
            int N = Integer.parseInt(jTextField1.getText().toString());

            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Delete from `kabinet` where `number_of_kabinet`=?");
            preparedStatement.setInt(1, N);
            preparedStatement.executeUpdate();
            KabinetDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            KabinetDialog.dispose();
            KabinetDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_DeleteButtonForTeachers1ActionPerformed
    /**
     * Добавление нагрузки
     */
    private void AddButtonForGroup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonForGroup1ActionPerformed
        boolean st = praktickcheckbox.isSelected();
        boolean pr = jCheckBox1.isSelected();
        String GC = GroupNagBox.getSelectedItem().toString();
        String DCB = DCBOX.getSelectedItem().toString();
        int indexd=DCB.indexOf("-")-1;
        String DC=DCB.substring(0, indexd);
        double TWi = Double.parseDouble(TopWeekHoursField.getText());
        double DWi = Double.parseDouble(DownWeekHoursField.getText());
        int SH = Integer.parseInt(RemainsHourField.getText())/2;
        int TW = (int) Math.ceil(TWi/2);
        int DW = (int) Math.floor(DWi/2);
        int GCi = 0;
        int DCi = 0;
        int LCI = 0;
        try {
            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT id_Groups  from `groups` where short_name=?");
            preparedStatement1.setString(1, GC);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                GCi = rs.getInt("id_Groups");
            }

            preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT id_Groups  from `groups` where short_name=?");
            preparedStatement1.setString(1, GC);
            rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                GCi = rs.getInt("id_Groups");
            }
            preparedStatement1 = mdbc.myConnection.prepareStatement("Select id_Discipline, Lecturer from `discipline` where id_Discipline=?");
            preparedStatement1.setString(1, DC);
            ResultSet rs4 = preparedStatement1.executeQuery();
            while (rs4.next()) {
                DCi = rs4.getInt("id_Discipline");
                LCI = rs4.getInt("Lecturer");
            }

            PreparedStatement prs = mdbc.myConnection.prepareStatement("SELECT hours_of_top_week as c, hours_of_down_week as d FROM nag WHERE nag.id_Groups=? and pr_practic=0 and st_practic=0");
            prs.setInt(1, GCi);
            ResultSet rsq;
            rsq = prs.executeQuery();
            int c = 0;
            int d = 0;
            while (rsq.next()) {
                c = c + rsq.getInt("c");
                d = d + rsq.getInt("d");
            }
            prs = mdbc.myConnection.prepareStatement("SELECT groups.max_hours as hs FROM groups WHERE groups.id_Groups=?");
            prs.setInt(1, GCi);
            rsq = prs.executeQuery();
            int max = 0;
            while (rsq.next()) {
                max = rsq.getInt("hs");
            }

            PreparedStatement prt = mdbc.myConnection.prepareStatement("SELECT `hours_of_top_week` as ht, `hours_of_down_week` as hd, lecturer.id_Lecturer FROM `nag`, lecturer, discipline WHERE nag.id_Discipline=discipline.id_Discipline and discipline.Lecturer=lecturer.id_Lecturer and nag.st_practic=0 and nag.pr_practic=0 and lecturer.id_Lecturer=?");
            prt.setInt(1, LCI);
            ResultSet rsqt;
            rsqt = prt.executeQuery();
            int ct = 0;
            int dt = 0;
            while (rsqt.next()) {
                ct = ct + rsqt.getInt("ht");
                dt = dt + rsqt.getInt("hd");
            }
            int maxt = 30;

            if (praktickcheckbox.isSelected() || jCheckBox1.isSelected()) {
                if (praktickcheckbox.isSelected() || jCheckBox1.isSelected()) {
                    SH = SH * 18;
                } else {
                    SH = SH;
                }
                if (praktickcheckbox.isSelected() || jCheckBox1.isSelected()) {
                    System.out.print(GCi + " " + DCi + " " + TW + " " + DW + " " + SH);
                    PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("INSERT INTO `nag` (`id_Discipline`, `id_Groups`, `hours_of_top_week`, `hours_of_down_week`, `hours_count_t`, `hours_count_d`, `hours_count_s`, `st_practic`, `pr_practic`) VALUES (?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1, DCi);
                    preparedStatement.setInt(2, GCi);
                    preparedStatement.setInt(3, TW);
                    preparedStatement.setInt(4, DW);
                    preparedStatement.setInt(5, TW);
                    preparedStatement.setInt(6, DW);
                    preparedStatement.setInt(7, SH);
                    preparedStatement.setBoolean(8, st);
                    preparedStatement.setBoolean(9, pr);
                    preparedStatement.executeUpdate();

                    PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement("INSERT INTO `storage` (`Group`, `Disciplane`, `Lecturer`, `hours_t`, `hours_d`, `debt`, `hours_s`, `st_practic`,`pr_practic`) VALUES (?,?,?,?,?,?,?,?,?)");
                    preparedStatement3.setInt(1, GCi);
                    preparedStatement3.setInt(2, DCi);
                    preparedStatement3.setInt(3, LCI);
                    preparedStatement3.setInt(4, TW);
                    preparedStatement3.setInt(5, DW);
                    preparedStatement3.setInt(6, 0);
                    preparedStatement3.setInt(7, SH);
                    preparedStatement3.setBoolean(8, st);
                    preparedStatement3.setBoolean(9, pr);
                    preparedStatement3.executeUpdate();

                

                    NagDialog.dispose();
                    getContentPane().removeAll();
                    initComponents();
                    NagDialog.dispose();
                    NagDialog.setVisible(true);
                }

            } else {
                if ((c + TW) <= Math.ceil(max/2)) {
                    if ((d + DW) <= Math.floor(max/2)) {
                        if ((ct + TW) <= maxt/2) {
                            if ((dt + DW) <= maxt/2) {
                                System.out.print(GCi + " " + DCi + " " + TW + " " + DW + " " + SH);
                                PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("INSERT INTO `nag` (`id_Discipline`, `id_Groups`, `hours_of_top_week`, `hours_of_down_week`, `hours_count_t`, `hours_count_d`, `hours_count_s`, `st_practic`, `pr_practic`) VALUES (?,?,?,?,?,?,?,?,?)");
                                preparedStatement.setInt(1, DCi);
                                preparedStatement.setInt(2, GCi);
                                preparedStatement.setInt(3, TW);
                                preparedStatement.setInt(4, DW);
                                preparedStatement.setInt(5, TW);
                                preparedStatement.setInt(6, DW);
                                preparedStatement.setInt(7, SH);
                                preparedStatement.setBoolean(8, st);
                                preparedStatement.setBoolean(9, pr);
                                preparedStatement.executeUpdate();

                                PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement("INSERT INTO `storage` (`Group`, `Disciplane`, `Lecturer`, `hours_t`, `hours_d`, `debt`, `hours_s`, `st_practic`,`pr_practic`) VALUES (?,?,?,?,?,?,?,?,?)");
                                preparedStatement3.setInt(1, GCi);
                                preparedStatement3.setInt(2, DCi);
                                preparedStatement3.setInt(3, LCI);
                                preparedStatement3.setInt(4, TW);
                                preparedStatement3.setInt(5, DW);
                                preparedStatement3.setInt(6, 0);
                                preparedStatement3.setInt(7, SH);
                                preparedStatement3.setBoolean(8, st);
                                preparedStatement3.setBoolean(9, pr);
                                preparedStatement3.executeUpdate();

                                NagDialog.dispose();
                                getContentPane().removeAll();
                                initComponents();
                                NagDialog.dispose();
                                NagDialog.setVisible(true);
                            } else {
                                MessageDialog.setVisible(true);
                                MessLabel1.setText("Количество пар выставленных для нижней недели, превышает максимально количество за неделю для преподавателя");
                            }
                        } else {
                            MessageDialog.setVisible(true);
                            MessLabel1.setText("Количество пар выставленных для нижней недели, превышает максимально количество за неделю для преподавателя");
                        }
                    } else {
                        MessageDialog.setVisible(true);
                        MessLabel1.setText("Количество пар выставленных для нижней недели, превышает максимально количество за неделю для группы");
                    }
                } else {
                    MessageDialog.setVisible(true);
                    MessLabel1.setText("Количество пар выставленных для верхней недели, превышает максимально количество за неделю для группы");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_AddButtonForGroup1ActionPerformed

    /**
     * Изменение нагрузки
     */
    private void EditButtonForGroup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonForGroup1ActionPerformed
        boolean st = praktickcheckbox.isSelected();
        boolean pr = jCheckBox1.isSelected();
        String GC = GroupNagBox.getSelectedItem().toString();
        String DCB = DCBOX.getSelectedItem().toString();
        int indexd=DCB.indexOf("-")-1;
        String DC=DCB.substring(0, indexd);
        double TWi = Double.parseDouble(TopWeekHoursField.getText());
        double DWi = Double.parseDouble(DownWeekHoursField.getText());
        int SH = Integer.parseInt(RemainsHourField.getText())/2;
        int TW = (int) Math.ceil(TWi/2);
        int DW = (int) Math.floor(DWi/2);
        int GCi = 0;
        int DCi = 0;
        int LCI = 0;
        try {
            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT id_Groups  from `groups` where short_name=?");
            preparedStatement1.setString(1, GC);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                GCi = rs.getInt("id_Groups");
            }

            preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT id_Groups  from `groups` where short_name=?");
            preparedStatement1.setString(1, GC);
            rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                GCi = rs.getInt("id_Groups");
            }
            preparedStatement1 = mdbc.myConnection.prepareStatement("Select id_Discipline, Lecturer from `discipline` where id_Discipline=?");
            preparedStatement1.setString(1, DC);
            ResultSet rs4 = preparedStatement1.executeQuery();
            while (rs4.next()) {
                DCi = rs4.getInt("id_Discipline");
                LCI = rs4.getInt("Lecturer");
            }

            PreparedStatement prs = mdbc.myConnection.prepareStatement("SELECT hours_of_top_week as c, hours_of_down_week as d FROM nag WHERE nag.id_Groups=? and pr_practic=0 and st_practic=0");
            prs.setInt(1, GCi);
            ResultSet rsq;
            rsq = prs.executeQuery();
            int c = 0;
            int d = 0;
            while (rsq.next()) {
                c = c + rsq.getInt("c");
                d = d + rsq.getInt("d");
            }
            prs = mdbc.myConnection.prepareStatement("SELECT groups.max_hours as hs FROM groups WHERE groups.id_Groups=?");
            prs.setInt(1, GCi);
            rsq = prs.executeQuery();
            int max = 0;
            while (rsq.next()) {
                max = rsq.getInt("hs");
            }

            PreparedStatement prt = mdbc.myConnection.prepareStatement("SELECT `hours_of_top_week` as ht, `hours_of_down_week` as hd, lecturer.id_Lecturer FROM `nag`, lecturer, discipline WHERE nag.id_Discipline=discipline.id_Discipline and discipline.Lecturer=lecturer.id_Lecturer and nag.st_practic=0 and nag.pr_practic=0 and lecturer.id_Lecturer=?");
            prt.setInt(1, LCI);
            ResultSet rsqt;
            rsqt = prt.executeQuery();
            int ct = 0;
            int dt = 0;
            while (rsqt.next()) {
                ct = ct + rsqt.getInt("ht");
                dt = dt + rsqt.getInt("hd");
            }
            int maxt = 30;

            prt = mdbc.myConnection.prepareStatement("SELECT `hours_of_top_week` as ht, `hours_of_down_week` as hd, lecturer.id_Lecturer FROM `nag`, lecturer, discipline WHERE nag.id_Discipline=discipline.id_Discipline and discipline.Lecturer=lecturer.id_Lecturer and nag.st_practic=0 and nag.pr_practic=0 and lecturer.id_Lecturer=? and discipline.id_Discipline=? AND nag.id_Groups=?");
            prt.setInt(1, LCI);
            prt.setInt(2, DCi);
            prt.setInt(3, GCi);
            rsqt = prt.executeQuery();
            int htt = 0;
            int hdd = 0;
            while (rsqt.next()) {
                htt = rsqt.getInt("ht");
                hdd = rsqt.getInt("hd");
            }

            prt = mdbc.myConnection.prepareStatement("SELECT hours_of_top_week as ht, hours_of_down_week as hd FROM nag WHERE nag.id_Groups=? and nag.id_Discipline=? and pr_practic=0 and st_practic=0");
            prt.setInt(2, DCi);
            prt.setInt(1, GCi);
            rsqt = prt.executeQuery();
            int ht = 0;
            int hd = 0;
            while (rsqt.next()) {
                ht = rsqt.getInt("ht");
                hd = rsqt.getInt("hd");
            }

            if (praktickcheckbox.isSelected() || jCheckBox1.isSelected()) {
                System.out.print(GCi + " " + DCi + " " + TW + " " + DW + " " + SH);
                PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement("Update `storage` Set  `hours_t`=?, `hours_d`=?,  `hours_s`=?, `st_practic`=?, `pr_practic`=? where `Group`=? and `Disciplane`=? and `Lecturer`=?");
                preparedStatement3.setInt(1, TW);
                preparedStatement3.setInt(2, DW);
                preparedStatement3.setInt(3, SH);
                preparedStatement3.setInt(6, GCi);
                preparedStatement3.setInt(7, DCi);
                preparedStatement3.setInt(8, LCI);
                preparedStatement3.setBoolean(4, st);
                preparedStatement3.setBoolean(5, pr);
                preparedStatement3.executeUpdate();
                PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("UPDATE `nag` Set `hours_of_top_week`=?, `hours_of_down_week`=?, `hours_count_t`=?, `hours_count_d`=?, `hours_count_s`=?, `st_practic`=?, `pr_practic`=? where `id_DIscipline`=? and `id_Groups`=?");
                preparedStatement.setInt(8, DCi);
                preparedStatement.setInt(9, GCi);
                preparedStatement.setInt(1, TW);
                preparedStatement.setInt(2, DW);
                preparedStatement.setInt(3, TW);
                preparedStatement.setInt(4, DW);
                preparedStatement.setInt(5, SH);
                preparedStatement.setBoolean(6, st);
                preparedStatement.setBoolean(7, pr);
                preparedStatement.executeUpdate();


                NagDialog.dispose();
                getContentPane().removeAll();
                initComponents();
                NagDialog.dispose();
                NagDialog.setVisible(true);

            } else {
                System.out.print(GCi + " " + DCi + " " + LCI + " " + c + TW + ht + " " + SH);
                if ((c + TW - ht) <= Math.ceil(max/2)) {
                    if ((d + DW - hd) <= Math.floor(max/2)) {
                        if ((ct + TW - htt) <= Math.ceil(maxt/2)) {
                            if ((dt + DW - hdd) <= Math.floor(maxt/2)) {
                                PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement("Update `storage` Set  `hours_t`=?, `hours_d`=?,  `hours_s`=?, `st_practic`=?, `pr_practic`=? where `Group`=? and `Disciplane`=? and `Lecturer`=?");
                                preparedStatement3.setInt(1, TW);
                                preparedStatement3.setInt(2, DW);
                                preparedStatement3.setInt(3, SH);
                                preparedStatement3.setInt(6, GCi);
                                preparedStatement3.setInt(7, DCi);
                                preparedStatement3.setInt(8, LCI);
                                preparedStatement3.setBoolean(4, st);
                                preparedStatement3.setBoolean(5, pr);
                                preparedStatement3.executeUpdate();
                                PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("UPDATE `nag` Set `hours_of_top_week`=?, `hours_of_down_week`=?, `hours_count_t`=?, `hours_count_d`=?, `hours_count_s`=?, `st_practic`=?, `pr_practic`=? where `id_DIscipline`=? and `id_Groups`=?");
                                preparedStatement.setInt(8, DCi);
                                preparedStatement.setInt(9, GCi);
                                preparedStatement.setInt(1, TW);
                                preparedStatement.setInt(2, DW);
                                preparedStatement.setInt(3, TW);
                                preparedStatement.setInt(4, DW);
                                preparedStatement.setInt(5, SH);
                                preparedStatement.setBoolean(6, st);
                                preparedStatement.setBoolean(7, pr);
                                preparedStatement.executeUpdate();
                                NagDialog.dispose();
                                getContentPane().removeAll();
                                initComponents();
                                NagDialog.dispose();
                                NagDialog.setVisible(true);
                            } else {
                                MessageDialog.setVisible(true);
                                MessLabel1.setText("Количество пар выставленных для нижней недели, превышает максимально количество за неделю для преподавателя");
                            }
                        } else {
                            MessageDialog.setVisible(true);
                            MessLabel1.setText("Количество пар выставленных для нижней недели, превышает максимально количество за неделю для преподавателя");
                        }
                    } else {
                        MessageDialog.setVisible(true);
                        MessLabel1.setText("Количество пар выставленных для нижней недели, превышает максимально количество за неделю для группы");
                    }
                } else {
                    MessageDialog.setVisible(true);
                    MessLabel1.setText("Количество пар выставленных для верхней недели, превышает максимально количество за неделю для группы");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_EditButtonForGroup1ActionPerformed
    /**
     * Удаление нагрузки
     */
    private void DeleteButtonForGroup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonForGroup1ActionPerformed
        String GC = GroupNagBox.getSelectedItem().toString();
        String DCB = DCBOX.getSelectedItem().toString();
        int indexd=DCB.indexOf("-")-1;
        String DC=DCB.substring(0, indexd);
        int GCi = 0;
        int DCi = 0;
        int LCI = 0;
        try {
            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT id_Groups  from `groups` where short_name=?");
            preparedStatement1.setString(1, GC);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                GCi = rs.getInt("id_Groups");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
        try {
            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("Select id_Discipline, Lecturer from `discipline` where id_Discipline=?");
            preparedStatement1.setString(1, DC);
            ResultSet rs4 = preparedStatement1.executeQuery();
            while (rs4.next()) {
                DCi = rs4.getInt("id_Discipline");
                LCI = rs4.getInt("Lecturer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
        try {
            PreparedStatement preparedStatement = mdbc.myConnection.prepareStatement("Delete from `storage` where `DIsciplane`=? and `Group`=?");
            preparedStatement.setInt(1, DCi);
            preparedStatement.setInt(2, GCi);
            preparedStatement.executeUpdate();
            System.out.println("Выполнено удаление из хранилища");
            preparedStatement = mdbc.myConnection.prepareStatement("Delete from `schedule_change` where `DIspline`=? and `Group`=?");
            preparedStatement.setInt(1, DCi);
            preparedStatement.setInt(2, GCi);
            preparedStatement.executeUpdate();
            System.out.println("Выполнено удаление из расписания замен");
            preparedStatement = mdbc.myConnection.prepareStatement("Delete from `nag` where `id_DIscipline`=? and `id_Groups`=?");
            preparedStatement.setInt(1, DCi);
            preparedStatement.setInt(2, GCi);
            preparedStatement.executeUpdate();


            NagDialog.dispose();
            getContentPane().removeAll();
            initComponents();
            NagDialog.dispose();
            NagDialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_DeleteButtonForGroup1ActionPerformed

    public static int substringCount(String s, String pattern) {
        // можно сначала поделать проверки на аргументы
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + pattern.length()).equalsIgnoreCase(pattern)) {
                result++;
                i += pattern.length();
            }
        }
        return result;
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            PreparedStatement preparedStatement5;
            try {
                preparedStatement5 = mdbc.myConnection.prepareStatement("UPDATE `nag` SET hours_count_t=hours_of_top_week");
                preparedStatement5.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainForm_v2.class.getName()).log(Level.SEVERE, null, ex);
            }

            File file1 = new File("C://Schedulesrc/ScheduleTopWeek.csv");
            File file = new File("C://Schedulesrc/SchedulePerTopWeek.csv");
            PrintWriter wtS = null;
            int DNi = 0;
            wtS = new PrintWriter(file1, "CP1251");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            int k = 0;
            int l = 0;
            String check2 = "";
            String check4 = "";
            int id_DIsp = 0;
            String dayt = "";
            int parat = 0;
            int dayit = 0;
            try {
                PreparedStatement preparedStatemend = mdbc.myConnection.prepareStatement("DELETE FROM schedule_true ");
                preparedStatemend.executeUpdate();
                /**
                 * Цикл работает до того, пока строка не пустая
                 */
                while (line != null) {
                    /**
                     * Считываем только нужные нам строки
                     */
                    if (k != 36) {
                        String self = line;
                        String result = self.replaceAll("\\s", ""); // удаляем пробелы
                        check2 = self;
                        String s = check4;
                        int daysgone = self.indexOf("Day ");
                        if (daysgone != 0) {
                            String para1 = String.valueOf(self.charAt(5));
                            parat = Integer.parseInt(para1);
                        } else {
                            dayit = Integer.parseInt(self.replaceAll("Day ", ""));
                            if (dayit == 0) {
                                dayt = "Понедельник";
                            } else if (dayit == 1) {
                                dayt = "Вторник";
                            } else if (dayit == 2) {
                                dayt = "Среда";
                            } else if (dayit == 3) {
                                dayt = "Четверг";
                            } else if (dayit == 4) {
                                dayt = "Пятница";
                            } else if (dayit == 5) {
                                dayt = "Суббота";
                            }
                        }
                        /**
                         * Заменяем лишнии дефисы
                         */
                        String resultspace = result.replaceAll("Group-Teacher:", ";");
                        String[] resultcheck = resultspace.split(";");
                        for (int i = 0; i < resultcheck.length; i++) {
                            System.out.println(resultcheck[i]);
                            /**
                             * Работаем с подстрокой, в которой есть дефис
                             */
                            if (resultcheck[i].contains("-")) {
                                String DN = "";
                                String GN = "";
                                String TN = "";
                                String TNi = "";
                                String GNi = "";
                                /**
                                 * Разделяем строку и забираем требуемые айди
                                 */
                                String[] gandt = resultcheck[i].split("-");
                                int gid = Integer.parseInt(gandt[0]);
                                int tid = Integer.parseInt(gandt[1]);
                                /**
                                 * Получаем название групп вместо их айди
                                 */
                                PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT short_name from "
                                        + "`groups` where id_Groups  =?");
                                preparedStatement1.setInt(1, gid);
                                ResultSet rs = preparedStatement1.executeQuery();
                                while (rs.next()) {
                                    GN = rs.getString("short_name");
                                }
                                /**
                                 * Получаем фамилии вместо айди
                                 */
                                PreparedStatement preparedStatement2 = mdbc.myConnection.prepareStatement("SELECT nag.id_Groups, discipline.id_Discipline as DNI, discipline.name as DN, lecturer.surname as s, nag.hours_count_t as htw from nag, groups, discipline, lecturer WHERE nag.id_Discipline=discipline.id_Discipline and discipline.Lecturer=lecturer.id_Lecturer and nag.id_Groups=groups.id_Groups AND nag.id_Groups=? AND lecturer.id_Lecturer=? AND nag.hours_count_t!='0' GROUP BY 1");
                                preparedStatement2.setInt(1, gid);
                                preparedStatement2.setInt(2, tid);
                                ResultSet rs2 = preparedStatement2.executeQuery();
                                while (rs2.next()) {
                                    TN = rs2.getString("s");
                                    DN = rs2.getString("DN");
                                    DNi = rs2.getInt("DNI");
                                }
                                int index=0;
                                index=DN.lastIndexOf(" ");
                                if (index!=-1){
                                DN=DN.substring(0, index);}
                                TNi = String.valueOf(tid);
                                GNi = String.valueOf(gid);
                                /**
                                 * Выполняем преобразования строки и заменяем в
                                 * ней айди на название и айди на фамилию
                                 */
                                check4 = check2.replaceAll("p-T", "p&T");
                                String check3 = check4.replaceAll("-", "___");
                                String check1 = check3.replaceAll(GNi + "_", GN);
                                check2 = check1.replaceAll("_" + TNi, DN + "   " + TN + "     ");
                                check4 = check2;
                                PreparedStatement preparedStatement6t = mdbc.myConnection.prepareStatement("INSERT INTO `schedule_true` (`day`, `para`, `Group`, `Lecturer`, `Displine`, `week`, `day_n`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                                preparedStatement6t.setString(1, dayt);
                                preparedStatement6t.setInt(2, parat);
                                preparedStatement6t.setInt(3, gid);
                                preparedStatement6t.setInt(4, tid);
                                preparedStatement6t.setInt(5, DNi);
                                preparedStatement6t.setString(6, "верхняя");
                                preparedStatement6t.setInt(7, dayit);
                                preparedStatement6t.executeUpdate();
                                System.out.println(check2);
                                PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement("UPDATE `nag` SET `hours_count_t` = (`hours_count_t`-'1') WHERE `nag`.`id_Discipline` = ? AND `nag`.`id_Groups` =  ?");
                                preparedStatement3.setInt(1, DNi);
                                preparedStatement3.setInt(2, gid);
                                preparedStatement3.executeUpdate();
                            }
                        }
                        wtS.println(check2);
                        k++;
                    }
                    // считываем остальные строки в цикле
                    line = reader.readLine();
                }

                PreparedStatement preparedStatement4 = mdbc.myConnection.prepareStatement("UPDATE `nag` SET hours_count_t=hours_of_top_week");
                preparedStatement4.executeUpdate();
                wtS.close();
                System.out.println("Верхняя неделя успешно сформирована.");
            } catch (SQLException e) {
                e.printStackTrace();
                MessageDialog.setVisible(true);
                MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
                MessageDialog.setVisible(true);
                MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные,"
                        + "Если это не поможет вызовете администратора");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        } catch (IOException e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
        

        
        try {
            try{
            PreparedStatement preparedStatement0 = mdbc.myConnection.prepareStatement("UPDATE `nag` SET hours_count_d=hours_of_down_week");
            preparedStatement0.executeUpdate();
            } catch (Exception e ){
            e.printStackTrace();
            }
            File file1 = new File("C://Schedulesrc/ScheduleDownWeek.csv");
            File file = new File("C://Schedulesrc/SchedulePerDownWeek.csv");
            PrintWriter wtS = null;
            wtS = new PrintWriter(file1, "CP1251");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            int k = 0;
            int l = 0;
            String check2 = "";
            String check4 = "";
            int id_DIsp = 0;
            String day = "";
            int para = 0;
            int dayi = 0;
            try {
                /**
                 * Цикл работает до того, пока строка не пустая
                 */
                while (line != null) {
                    /**
                     * Считываем только нужные нам строки
                     */
                    if (k != 36) {
                        String self = line;
                        String result = self.replaceAll("\\s", ""); // удаляем пробелы
                        check2 = self;

                        int daysgone = self.indexOf("Day ");
                        if (daysgone != 0) {
                            String para1 = String.valueOf(self.charAt(5));
                            para = Integer.parseInt(para1);
                        } else {
                            dayi = Integer.parseInt(self.replaceAll("Day ", ""));
                            if (dayi == 0) {
                                day = "Понедельник";
                            } else if (dayi == 1) {
                                day = "Вторник";
                            } else if (dayi == 2) {
                                day = "Среда";
                            } else if (dayi == 3) {
                                day = "Четверг";
                            } else if (dayi == 4) {
                                day = "Пятница";
                            } else if (dayi == 5) {
                                day = "Суббота";
                            }
                        }
                        /**
                         * Заменяем лишнии дефисы
                         */
                        String resultspace = result.replaceAll("Group-Teacher:", ";");
                        String[] resultcheck = resultspace.split(";");
                        for (int i = 0; i < resultcheck.length; i++) {
                            System.out.println(resultcheck[i]);
                            /**
                             * Работаем с подстрокой, в которой есть дефис
                             */
                            if (resultcheck[i].contains("-")) {
                                String DN = "";
                                String GN = "";
                                String TN = "";
                                String TNi = "";
                                String GNi = "";
                                int DNi = 0;
                                /**
                                 * Разделяем строку и забираем требуемые айди
                                 */
                                String[] gandt = resultcheck[i].split("-");
                                int gid = Integer.parseInt(gandt[0]);
                                int tid = Integer.parseInt(gandt[1]);
                                /**
                                 * Получаем название групп вместо их айди
                                 */
                                PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT short_name from "
                                        + "`groups` where id_Groups  =?");
                                preparedStatement1.setInt(1, gid);
                                ResultSet rs = preparedStatement1.executeQuery();
                                while (rs.next()) {
                                    GN = rs.getString("short_name");
                                }
                                /**
                                 * Получаем фамилии вместо айди и добавляем
                                 * дисциплины
                                 */
                                PreparedStatement preparedStatement2 = mdbc.myConnection.prepareStatement("SELECT nag.id_Groups, discipline.id_Discipline as DNI, discipline.name as DN, lecturer.surname as s, nag.hours_count_d as htw from nag, groups, discipline, lecturer WHERE nag.id_Discipline=discipline.id_Discipline and discipline.Lecturer=lecturer.id_Lecturer and nag.id_Groups=groups.id_Groups AND nag.id_Groups=? AND lecturer.id_Lecturer=? AND nag.hours_count_d!='0' GROUP BY 1");
                                preparedStatement2.setInt(1, gid);
                                preparedStatement2.setInt(2, tid);
                                ResultSet rs2 = preparedStatement2.executeQuery();
                                while (rs2.next()) {
                                    TN = rs2.getString("s");
                                    DN = rs2.getString("DN");
                                    DNi = rs2.getInt("DNI");
                                }

                                TNi = String.valueOf(tid);
                                GNi = String.valueOf(gid);
                                /**
                                 * Выполняем преобразования строки и заменяем в
                                 * ней айди на название и айди на фамилию
                                 */
                                check4 = check2.replaceAll("p-T", "p&T");
                                String check3 = check4.replaceAll("-", "___");
                                String check1 = check3.replaceAll(GNi + "_", GN);
                                check2 = check1.replaceAll("_" + TNi, DN + "   " + TN + "     ");
                                check4 = check2;
                                System.out.println(DNi);
                                PreparedStatement preparedStatement6 = mdbc.myConnection.prepareStatement("INSERT INTO `schedule_true` (`day`, `para`, `Group`, `Lecturer`, `Displine`, `week`, `day_n`) VALUES (?, ?, ?, ?, ?, ?,?)");
                                preparedStatement6.setString(1, day);
                                preparedStatement6.setInt(2, para);
                                preparedStatement6.setInt(3, gid);
                                preparedStatement6.setInt(4, tid);
                                preparedStatement6.setInt(5, DNi);
                                preparedStatement6.setString(6, "нижняя");
                                preparedStatement6.setInt(7, dayi);
                                preparedStatement6.executeUpdate();
                                System.out.println(check2);
                                PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement("UPDATE `nag` SET `hours_count_d` = (`hours_count_d`-'1') WHERE `nag`.`id_Discipline` = ? AND `nag`.`id_Groups` =  ?");
                                preparedStatement3.setInt(1, DNi);
                                preparedStatement3.setInt(2, gid);
                                preparedStatement3.executeUpdate();
                            }
                        }
                        wtS.println(check2);
                        k++;
                    }
                    // считываем остальные строки в цикле
                    line = reader.readLine();
                }

                PreparedStatement preparedStatement4 = mdbc.myConnection.prepareStatement("UPDATE `nag` SET hours_count_d=hours_of_down_week");
                preparedStatement4.executeUpdate();
                wtS.close();
            } catch (SQLException e) {
                e.printStackTrace();
                MessageDialog.setVisible(true);
                MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        } catch (IOException e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void praktickcheckboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_praktickcheckboxItemStateChanged
        if (jCheckBox1.isSelected() && praktickcheckbox.isSelected()) {
            MessageDialog.setVisible(true);
            MessLabel1.setText("Можно выбрать только один вид практики");
        } else {
            if (praktickcheckbox.isSelected()) {
                TopWeekHoursField.setEnabled(false);
                DownWeekHoursField.setEnabled(false);
                TopWeekHoursField.setText("36");
                DownWeekHoursField.setText("36");
            } else {
                TopWeekHoursField.setEnabled(true);
                DownWeekHoursField.setEnabled(true);
                TopWeekHoursField.setText("");
                DownWeekHoursField.setText("");
            }
        }
    }//GEN-LAST:event_praktickcheckboxItemStateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (jCheckBox1.isSelected() && praktickcheckbox.isSelected()) {
            MessageDialog.setVisible(true);
            MessLabel1.setText("Можно выбрать только один вид практики");
        } else {
            if (jCheckBox1.isSelected()) {
                TopWeekHoursField.setEnabled(false);
                DownWeekHoursField.setEnabled(false);
                TopWeekHoursField.setText("36");
                DownWeekHoursField.setText("36");
            } else {
                TopWeekHoursField.setEnabled(true);
                DownWeekHoursField.setEnabled(true);
                TopWeekHoursField.setText("");
                DownWeekHoursField.setText("");
            }
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed
    /**
     * Два преподавателя
     */
    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
        if (jCheckBox3.isSelected()) {
            jComboBox7.setEnabled(true);
        } else {
            jComboBox7.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox3ItemStateChanged

    /**
     * Подтверждение добавления практики
     */
    private void AddButtonForGroup4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonForGroup4ActionPerformed
        try {
            String type = prType.getText().toString();
            String groupName="";
            int lpi1 = 0;
            int lpi2 = 0;
            int gpi = 0;
            int dpi = 0;
            int ht = 0;
            int db = 0;
            int dbs = 0;
            int hsc = 0;
            int nht = 0;
            int nhd = 0;
            int dbc = 0;
            int nhs = 0;
            int hds = 0;
            int hst = 0;
            PreparedStatement preparedStatement = null;
            PreparedStatement preparedStatement1 = null;
            ResultSet rs;
            ResultSet rs1;
            gpi = GroupPracticBox.getSelectedItem().toString().indexOf(" ");
            dpi = DispCoBox.getSelectedItem().toString().indexOf(" ");
            lpi1 = jComboBox6.getSelectedItem().toString().indexOf(" ");
            lpi2 = jComboBox7.getSelectedItem().toString().indexOf(" ");
            int LP1 = Integer.parseInt(jComboBox6.getSelectedItem().toString().substring(0, lpi1));
            int LP2 = Integer.parseInt(jComboBox7.getSelectedItem().toString().substring(0, lpi2));
            int GP = Integer.parseInt(GroupPracticBox.getSelectedItem().toString().substring(0, gpi));
            int DP = Integer.parseInt(DispCoBox.getSelectedItem().toString().substring(0, dpi));

            if (type.equals("Производственная")) {
                preparedStatement = mdbc.myConnection.prepareStatement("SELECT groups.short_name as sn FROM groups WHERE groups.id_Groups=?");
                preparedStatement.setInt(1, GP);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    groupName=rs.getString("sn");
                    PreparedStatement preparedStatementes = mdbc.myConnection.prepareStatement("DELETE FROM schedule_need_change WHERE schedule_need_change.Group=?");
                    preparedStatementes.setInt(1, GP);
                    preparedStatementes.executeUpdate();

                    for (int i=0; i<3; i++){
                    preparedStatementes = mdbc.myConnection.prepareStatement("INSERT INTO `schedule_need_change`(`day`, `day_n`, `para`, `Group`, `Lecturer`, `Displine`, `week`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                     preparedStatementes.setString(1, dayBox.getSelectedItem().toString());
                        preparedStatementes.setInt(2, 0);
                        preparedStatementes.setInt(3, i);
                        preparedStatementes.setInt(4,   GP);
                        preparedStatementes.setInt(6,   DP);
                        preparedStatementes.setInt(5,   LP1);
                        preparedStatementes.setString(7,  weekBox.getSelectedItem().toString());
                    preparedStatementes.executeUpdate();
                            }
                    
                }}
            else {
                preparedStatement = mdbc.myConnection.prepareStatement("SELECT groups.short_name as sn FROM groups WHERE groups.id_Groups=?");
                preparedStatement.setInt(1, GP);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    PreparedStatement preparedStatementes = mdbc.myConnection.prepareStatement("DELETE FROM schedule_need_change WHERE schedule_need_change.Group=?");
                    preparedStatementes.setInt(1, GP);
                    preparedStatementes.executeUpdate();
                    for (int i=0; i<3; i++){
                    preparedStatementes = mdbc.myConnection.prepareStatement("INSERT INTO `schedule_need_change`(`day`, `day_n`, `para`, `Group`, `Lecturer`, `Displine`, `week`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                     preparedStatementes.setString(1, dayBox.getSelectedItem().toString());
                        preparedStatementes.setInt(2, 0);
                        preparedStatementes.setInt(3, i);
                        preparedStatementes.setInt(4,   GP);
                        preparedStatementes.setInt(6,   DP);
                        preparedStatementes.setInt(5,   LP1);
                        preparedStatementes.setString(7,  weekBox.getSelectedItem().toString());
                    preparedStatementes.executeUpdate();

                            }
                    
                }
                                practik_check=1;
            }
            
jLabel61.setText("Добавление выполнено");
System.out.println("Успешно");
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла критическая ошибка, проверьте введеные данные," + "Если это не поможет вызовете администратора");
        }
    }//GEN-LAST:event_AddButtonForGroup4ActionPerformed

    private void GroupPracticBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GroupPracticBoxItemStateChanged
        PracticCheck();
    }//GEN-LAST:event_GroupPracticBoxItemStateChanged

    private void DispCoBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DispCoBoxItemStateChanged
        PracticCheck();
    }//GEN-LAST:event_DispCoBoxItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        PracticCheck();
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged
        PracticCheck();
    }//GEN-LAST:event_jComboBox7ItemStateChanged

    private void DispTableForeverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DispTableForeverMouseClicked
        int rows = DispTableForever.getSelectedRow();
        String DC=" ";
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 5; i++) {
            System.out.println(DispTableForever.getValueAt(rows, i));
            values1 = values1 + DispTableForever.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        GroupNagBox.setSelectedItem(ring[1]);
              try{
       PreparedStatement pst=mdbc.myConnection.prepareStatement("SELECT id_Discipline as id, discipline.name as dn, surname as s, lecturer.name as n, lecturer.second_name as sn FROM discipline, lecturer WHERE discipline.Lecturer=lecturer.id_Lecturer and discipline.name LIKE ?");
       pst.setString(1, ring[0]+"%");
       ResultSet rs8;
       rs8=pst.executeQuery();
       while (rs8.next()) {
       int index = 0;
       DC=rs8.getString("dn");
       index = DC.lastIndexOf(" ");
       if (index!=-1){
       DC=DC.substring(0, index);
       }
        System.out.print(rs8.getString("id")+" - "+DC+" - "+rs8.getString("s")+" "+rs8.getString("n").charAt(0)+". "+rs8.getString("sn").charAt(0)+".");
        DCBOX.setSelectedItem(rs8.getString("id")+" - "+DC+" - "+rs8.getString("s")+" "+rs8.getString("n").charAt(0)+". "+rs8.getString("sn").charAt(0)+".");
               }}
       catch(Exception e){
        e.printStackTrace();
       }
        TopWeekHoursField.setText(String.valueOf(Integer.parseInt(ring[2])*2));
        DownWeekHoursField.setText(String.valueOf(Integer.parseInt(ring[3])*2));
        if (ring[4].equals("Да")) {
            praktickcheckbox.setSelected(true);
        } else {
            praktickcheckbox.setSelected(false);
        }
        if (ring[5].equals("Да")) {
            jCheckBox1.setSelected(true);
        } else {
            jCheckBox1.setSelected(false);
        }
    }//GEN-LAST:event_DispTableForeverMouseClicked
    /**
     * Получение данных данных
     */
    private void DispTableForever1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DispTableForever1MouseClicked
        int rows = DispTableForever1.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 2; i++) {
            System.out.println(DispTableForever1.getValueAt(rows, i));
            values1 = values1 + DispTableForever1.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        IdDispField.setText(ring[0]);
        String[] teacher = ring[2].split(" ");

        String full = String.valueOf(teacher[0] + " " + teacher[1].charAt(0) + ". " + teacher[2].charAt(0) + ".");
        DispTeacher2.setSelectedItem(full);
 
        DispNameFull1.setText(ring[1]);
    }//GEN-LAST:event_DispTableForever1MouseClicked

    /**
     * Нажатие для таблицы преподавателей
     */
    private void TeachersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeachersTableMouseClicked
        int rows = TeachersTable.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 6; i++) {
            System.out.println(TeachersTable.getValueAt(rows, i));
            values1 = values1 + TeachersTable.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        IdTeacherField.setText(ring[0]);
        LongNameField1.setText(ring[1]);
        ShortNameField1.setText(ring[2]);
        SecondNameField.setText(ring[3]);
        WorkStatus.setSelectedItem(ring[4]);
        prioritykabField.setText(ring[5]);
        try{
        PreparedStatement psn = mdbc.myConnection.prepareStatement("SELECT need_in_kab_id as nk FROM lecturer WHERE lecturer.id_Lecturer=?");
        psn.setString(1, ring[0]);
        ResultSet rs;
        rs=psn.executeQuery();
        while (rs.next()){
        int nk=rs.getInt("nk");
        switch (nk) {
                                        case 0:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            
                                            break;
                                        case 1:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(true);
                                            break;
                                        case 2:
            PCFlag.setSelected(false);
            SportFlag.setSelected(true);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                        case 3:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(true);
            MasterFlag.setSelected(false);
                                            break;
                                        case 4:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(true);
            MasterFlag.setSelected(false);
                                            break;
                                        case 5:
            PCFlag.setSelected(true);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(true);
            MasterFlag.setSelected(false);
                                            
                                            break;
                                        case 6:
            PCFlag.setSelected(true);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                        case 7:
            PCFlag.setSelected(true);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                        case 8:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                    } 
        }
        
        } catch (SQLException se){
        se.printStackTrace();
        }
    }//GEN-LAST:event_TeachersTableMouseClicked

    /**
     * Нажатие для таблицы группы
     */
    private void GroupsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GroupsTableMouseClicked
        int rows = GroupsTable.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 4; i++) {
            System.out.println(GroupsTable.getValueAt(rows, i));
            values1 = values1 + GroupsTable.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        IdGroupField.setText(ring[0]);
        LongNameField.setText(ring[2]);
        ShortNameField.setText(ring[1]);
        jTextField3.setText(ring[3]);
        GroupComboBox.setSelectedItem(ring[4]);
    }//GEN-LAST:event_GroupsTableMouseClicked

    /**
     * Нажатие на таблицу кабинет
     */
    private void KabinetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabinetTableMouseClicked
        int rows = KabinetTable.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 6; i++) {
            System.out.println(KabinetTable.getValueAt(rows, i));
            values1 = values1 + KabinetTable.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        jTextField1.setText(ring[0]);
        StatusKabBox.setSelectedItem(ring[1]);
        if (ring[2].equals("Да")) {
            PCFlag1.setSelected(true);
        } else {
            PCFlag1.setSelected(false);
        }
        if (ring[3].equals("Да")) {
            DeskFlag1.setSelected(true);
        } else {
            DeskFlag1.setSelected(false);
        }
        if (ring[4].equals("Да")) {
            ShowFlag1.setSelected(true);
        } else {
            ShowFlag1.setSelected(false);
        }
        if (ring[5].equals("Да")) {
            SportFlag1.setSelected(true);
        } else {
            SportFlag1.setSelected(false);
        }
        if (ring[6].equals("Да")) {
            MasterFlag1.setSelected(true);
        } else {
            MasterFlag1.setSelected(false);
        }
    }//GEN-LAST:event_KabinetTableMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ResultSet rsg;
        ResultSet rsh;
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Верхняя неделя");
        CellStyle myStyle = book.createCellStyle();
        myStyle.setRotation((short)90);
        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        name.setCellValue("День/Группа");
        name = row.createCell(1);
        name.setCellValue("Номер пары");
        try {
            sheet.addMergedRegion(new CellRangeAddress(1,5,0,0));
            sheet.addMergedRegion(new CellRangeAddress(6,10,0,0));
            sheet.addMergedRegion(new CellRangeAddress(11,15,0,0));
            sheet.addMergedRegion(new CellRangeAddress(16,20,0,0));
            sheet.addMergedRegion(new CellRangeAddress(21,26,0,0));
            sheet.addMergedRegion(new CellRangeAddress(27,31,0,0));

            PreparedStatement psg=mdbc.myConnection.prepareStatement("Select * from `groups`");
            rsg=psg.executeQuery();
            int i=2;
            while (rsg.next()){
                name = row.createCell(i);
                name.setCellValue(rsg.getString("short_name"));
                i++;
            }

            for (int d=0;d<=5;d++){
                int index=0;
                for (int j=0; j<=4;j++){
                    psg=mdbc.myConnection.prepareStatement("Select * from groups");
                    rsg=psg.executeQuery();
                    System.out.println(j);
                    switch (d) {
                        case 0:
                        row=sheet.createRow(j+1);
                        break;
                        case 1:
                        row=sheet.createRow(j+6);
                        break;
                        case 2:
                        row=sheet.createRow(j+11);
                        break;
                        case 3:
                        row=sheet.createRow(j+16);
                        break;
                        case 4:
                        row=sheet.createRow(j+21);
                        break;
                        case 5:
                        row=sheet.createRow(j+27);
                        break;
                    }

                    if ((j+1==1) & (d==0)){
                        Cell day = row.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Понедельник");
                    }
                    if ((j+1==1) & (d==1)){
                        Cell day = row.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Вторник");
                    }
                    if ((j+1==1) & (d==2)){
                        Cell day = row.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Среда");
                    }
                    if ((j+1==1) & (d==3)){
                        Cell day = row.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Четверг");
                    }
                    if ((j+1==1) & (d==4)){
                        Cell day = row.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Пятница");
                    }
                    if ((j+1==1) & (d==5)){
                        Cell day = row.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Суббота");
                    }
                    Cell para = row.createCell(1);
                    para.setCellValue(j+1);

                    int k=2;
                    while (rsg.next()){
                        System.out.println("Зашел сюда");
                        PreparedStatement psh=mdbc.myConnection.prepareStatement("SELECT COUNT(para) as p, discipline.name as dn, lecturer.surname as ls, lecturer.name as ln, lecturer.second_name as lsn FROM schedule_true, discipline, lecturer WHERE schedule_true.day_n=? and para=? and week='верхняя' and discipline.id_Discipline=schedule_true.Displine and lecturer.id_Lecturer=schedule_true.Lecturer and schedule_true.Group=? ORDER BY schedule_true.Group");
                        psh.setInt(1, d);
                        psh.setInt(2, j);
                        psh.setInt(3, rsg.getInt("id_Groups"));
                        rsh=psh.executeQuery();
                        while (rsh.next()){
                            System.out.println("Зашел туда");
                            index=rsh.getInt("p");
                            if (index==0){
                                System.out.println(index+"!!");
                                name=row.createCell(k);
                                name.setCellValue("");
                                k++;
                            } else {
                                name=row.createCell(k);
                                int indexd=0;
                                String dn=rsh.getString("dn");
                                indexd=dn.lastIndexOf(" ");
                                if (indexd!=-1){
                                dn=dn.substring(0, indexd);
                                }
                                System.out.println(dn+" "+rsh.getString("ls")+" "+rsh.getString("ln").charAt(0)+". "+rsh.getString("lsn").charAt(0)+".");
                                name.setCellValue(dn+" "+rsh.getString("ls")+" "+rsh.getString("ln").charAt(0)+". "+rsh.getString("lsn").charAt(0)+".");
                                k++;
                            }
                        }
                    }
                }
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        for (int i=0; i<999;i++){
            sheet.autoSizeColumn(i);
        }

        myStyle.setRotation((short)90);
        Sheet sheetd = book.createSheet("Нижняя неделя");
        Row rowd = sheetd.createRow(0);
        Cell named = rowd.createCell(0);
        named.setCellValue("День/Группа");
        named = rowd.createCell(1);
        named.setCellValue("Номер пары");
        try {
            sheetd.addMergedRegion(new CellRangeAddress(1,5,0,0));
            sheetd.addMergedRegion(new CellRangeAddress(6,10,0,0));
            sheetd.addMergedRegion(new CellRangeAddress(11,15,0,0));
            sheetd.addMergedRegion(new CellRangeAddress(16,20,0,0));
            sheetd.addMergedRegion(new CellRangeAddress(21,26,0,0));
            sheetd.addMergedRegion(new CellRangeAddress(27,31,0,0));

            PreparedStatement psg=mdbc.myConnection.prepareStatement("Select * from groups");
            rsg=psg.executeQuery();
            int i=2;
            while (rsg.next()){
                named = rowd.createCell(i);
                named.setCellValue(rsg.getString("short_name"));
                i++;
            }
            for (int d=0;d<=5;d++){
                int index=0;
                for (int j=0; j<=4;j++){
                    psg=mdbc.myConnection.prepareStatement("Select * from groups");
                    rsg=psg.executeQuery();
                    System.out.println(j);
                    switch (d) {
                        case 0:
                        rowd=sheetd.createRow(j+1);
                        break;
                        case 1:
                        rowd=sheetd.createRow(j+6);
                        break;
                        case 2:
                        rowd=sheetd.createRow(j+11);
                        break;
                        case 3:
                        rowd=sheetd.createRow(j+16);
                        break;
                        case 4:
                        rowd=sheetd.createRow(j+21);
                        break;
                        case 5:
                        rowd=sheetd.createRow(j+27);
                        break;
                    }

                    if ((j+1==1) & (d==0)){
                        Cell day = rowd.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Понедельник");
                    }
                    if ((j+1==1) & (d==1)){
                        Cell day = rowd.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Вторник");
                    }
                    if ((j+1==1) & (d==2)){
                        Cell day = rowd.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Среда");
                    }
                    if ((j+1==1) & (d==3)){
                        Cell day = rowd.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Четверг");
                    }
                    if ((j+1==1) & (d==4)){
                        Cell day = rowd.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Пятница");
                    }
                    if ((j+1==1) & (d==5)){
                        Cell day = rowd.createCell(0);
                        day.setCellStyle(myStyle);
                        day.setCellValue("Суббота");
                    }

                    Cell parad = rowd.createCell(1);
                    parad.setCellValue(j+1);

                    int k=2;
                    while (rsg.next()){
                        System.out.println("Зашел сюда");
                        PreparedStatement psh=mdbc.myConnection.prepareStatement("SELECT COUNT(para) as p, discipline.name as dn, lecturer.surname as ls, lecturer.name as ln, lecturer.second_name as lsn FROM schedule_true, discipline, lecturer WHERE schedule_true.day_n=? and para=? and week='нижняя' and discipline.id_Discipline=schedule_true.Displine and lecturer.id_Lecturer=schedule_true.Lecturer and schedule_true.Group=? ORDER BY schedule_true.Group");
                        psh.setInt(1, d);
                        psh.setInt(2, j);
                        psh.setInt(3, rsg.getInt("id_Groups"));
                        rsh=psh.executeQuery();
                        while (rsh.next()){
                            System.out.println("Зашел туда");
                            index=rsh.getInt("p");
                            if (index==0){
                                System.out.println(index+"!!");
                                named=rowd.createCell(k);
                                named.setCellValue("");
                                k++;
                            } else {
                                named=rowd.createCell(k);
                                System.out.println(rsh.getString("dn")+" "+rsh.getString("ls")+" "+rsh.getString("ln").charAt(0)+". "+rsh.getString("lsn").charAt(0)+".");
                                named.setCellValue(rsh.getString("dn")+" "+rsh.getString("ls")+" "+rsh.getString("ln").charAt(0)+". "+rsh.getString("lsn").charAt(0)+".");
                                k++;
                            }
                        }
                    }
                }
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        for (int i=0; i<999;i++){
            sheetd.autoSizeColumn(i);
        }

        try {

            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Выбор директории для сохранения файла");
            file.setMultiSelectionEnabled(false);
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            file.setFileHidingEnabled(false);
            if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                java.io.File f = file.getSelectedFile();

                FileOutputStream out = new FileOutputStream(new File(f.getPath()+"/Основное расписание.xls"));
                book.write(out);
                book.close();
                out.close();
                File filetopweek = new File(f.getPath()+"/Основное расписание.xls");
                Desktop.getDesktop().open(filetopweek);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Сделано");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        startScheduleChange();
        practik_check=0;
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            nextScheduleChange();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        PreparedStatement pss, psu; ResultSet rss;
        try {
            pss=mdbc.myConnection.prepareStatement("SELECT testchange1.hours_today as ht, testchange1.Group as g, groups.short_name, testchange1.Displine as d, discipline.name, testchange1.Lecturer as l, lecturer.surname  FROM testchange1 , groups, discipline, lecturer WHERE testchange1.Group=groups.id_Groups and testchange1.Displine=discipline.id_Discipline and testchange1.Lecturer=lecturer.id_Lecturer GROUP BY testchange1.Group, testchange1.Displine, testchange1.Lecturer");
            rss=pss.executeQuery();
            while (rss.next()){
                psu=mdbc.myConnection.prepareStatement("UPDATE `storage` SET `hours_s`=`hours_s`-? WHERE `Group`=? and `Disciplane`=? and`Lecturer`=?");
                psu.setInt(1,rss.getInt("ht"));
                psu.setInt(2,rss.getInt("g"));
                psu.setInt(3,rss.getInt("d"));
                psu.setInt(4,rss.getInt("l"));
                psu.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        NewPracticDialog.dispose();
        jLabel61.setText("");
        /**Создаём переменные  дял работы*/
        String week1=weekBox.getSelectedItem().toString();
        String day1=dayBox.getSelectedItem().toString();
        ResultSet rsm, rst, rsgl, rht; PreparedStatement pst; PreparedStatement psm;
        try{
        /**Заполняем те пары, в которых преподаватели работают*/
        int index=0;
        pst=mdbc.myConnection.prepareStatement("SELECT discipline.name, discipline.id_Discipline as di, groups.short_name, groups.id_Groups as gi, lecturer.surname, lecturer.id_Lecturer as li FROM schedule_need_change, discipline, lecturer, groups WHERE schedule_need_change.Group=groups.id_Groups and schedule_need_change.Displine=discipline.id_Discipline and schedule_need_change.Lecturer=lecturer.id_Lecturer and lecturer.status='Работает' and week=? and day=?");
        pst.setString(1,week1);
        pst.setString(2, day1);
        rst=pst.executeQuery();
        while (rst.next()){
        PreparedStatement psi=mdbc.myConnection.prepareStatement("INSERT INTO `testchange1`(`Group`, `Displine`, `Lecturer`, `hours_today`,`hours_count_s`,`debt`) VALUES (?,?,?,?,?,?)");
        psi.setInt(1,rst.getInt("gi"));
        psi.setInt(2,rst.getInt("di"));
        psi.setInt(3,rst.getInt("li"));
        psi.setInt(4,1);
        psi.setInt(5,0);
        psi.setInt(6,0);
        psi.executeUpdate();}
        
        /**Получаем те пары в которых надо произвести замены*/
        String query="SELECT discipline.name, groups.id_Groups as g, lecturer.surname as s, para, week "
                + "FROM schedule_need_change, discipline, lecturer, groups, nag "
                + "WHERE schedule_need_change.Group=groups.id_Groups and schedule_need_change.Displine=discipline.id_Discipline and schedule_need_change.Lecturer=lecturer.id_Lecturer "
                + "and lecturer.status!='Работает' and week=? and day=? and nag.id_Discipline=discipline.id_Discipline and nag.id_Groups=groups.id_Groups";    
        psm=mdbc.myConnection.prepareStatement(query);
        psm.setString(1, week1);
        psm.setString(2, day1);
        rsm=psm.executeQuery();
        while (rsm.next()){
         check=0;   
         index++;
         /**Получаем преподавателя и дисциплину, на которые меняем*/
        pst=mdbc.myConnection.prepareStatement("SELECT groups.id_Groups as gi, lecturer.surname, lecturer.id_Lecturer as li, discipline.id_Discipline as di FROM schedule_need_change, lecturer, discipline, groups WHERE schedule_need_change.Displine=discipline.id_Discipline and schedule_need_change.Lecturer=lecturer.id_Lecturer and lecturer.status='Работает' and week=? and day=? and lecturer.surname IN (SELECT lecturer.surname FROM nag, groups, lecturer, discipline WHERE nag.id_Discipline=discipline.id_Discipline and nag.id_Groups=groups.id_Groups and discipline.Lecturer=lecturer.id_Lecturer and groups.id_Groups=? GROUP BY surname) and groups.id_Groups=? and surname IN (SELECT surname FROM schedule_need_change, lecturer WHERE schedule_need_change.Lecturer=lecturer.id_Lecturer and week=? and day=? and (SELECT COUNT(para) FROM `schedule_need_change` WHERE schedule_need_change.Lecturer=lecturer.id_Lecturer and week=? and day=?)<3 GROUP BY surname)  and surname IN (SELECT lecturer.surname FROM `storage`, schedule_need_change, lecturer WHERE schedule_need_change.Group=`storage`.Group and schedule_need_change.Lecturer = `storage`.Lecturer and schedule_need_change.Displine=`storage`.`Disciplane` and `storage`.`Lecturer`=lecturer.id_Lecturer and lecturer.status='Работает' and `storage`.hours_s>0 Group by schedule_need_change.Group, schedule_need_change.Displine, schedule_need_change.Lecturer) GROUP BY schedule_need_change.Group, schedule_need_change.Displine, schedule_need_change.Lecturer  ORDER BY COUNT(para), rand() ASC limit 1");
        pst.setString(1,week1);
        pst.setString(2, day1);
        pst.setInt(3, rsm.getInt("g"));
        pst.setInt(4, rsm.getInt("g"));
        pst.setString(5,week1);
        pst.setString(6, day1);
        pst.setString(7,week1);
        pst.setString(8, day1);
        rst=pst.executeQuery();
        while (rst.next()){
        System.out.println(rst.getInt("gi")+" - "+rst.getInt("di")+" - "+rst.getInt("li"));
        PreparedStatement psi=mdbc.myConnection.prepareStatement("INSERT INTO `testchange1`(`Group`, `Displine`, `Lecturer`, `hours_today`,`hours_count_s`,`debt`) VALUES (?,?,?,?,?,?)");
        psi.setInt(1,rst.getInt("gi"));
        psi.setInt(2,rst.getInt("di"));
        psi.setInt(3,rst.getInt("li"));
        psi.setInt(4,1);
        psi.setInt(5,0);
        psi.setInt(6,0);
        psi.executeUpdate();
        psi=mdbc.myConnection.prepareStatement("INSERT INTO `schedule_need_change`(`day`, `day_n`, `para`, `Group`, `Lecturer`, `Displine`, `week`) VALUES (?, ?, ?, ?, ?, ?, ?)");
        psi.setString(1, dayBox.getSelectedItem().toString());
        psi.setInt(2, 0);
        psi.setInt(3, 99-index);
        psi.setInt(4,rst.getInt("gi"));
        psi.setInt(5,rst.getInt("li"));
        psi.setInt(6,rst.getInt("di"));
        psi.setString(7,  weekBox.getSelectedItem().toString());
        psi.executeUpdate();
        }
        }
        
        /**Получаем все практики, за день и удаляем у них лишнии пары*/
       PreparedStatement psdd=mdbc.myConnection.prepareStatement("SELECT schedule_need_change.Group as gp, schedule_need_change.Displine as dp FROM schedule_need_change, "
               + "groups, nag WHERE schedule_need_change.Group=nag.id_Groups and schedule_need_change.Displine=nag.id_Discipline and nag.pr_practic=1 GROUP BY schedule_need_change.Group");
       ResultSet rsdd;
       rsdd=psdd.executeQuery();
       while (rsdd.next()){
       psdd=mdbc.myConnection.prepareStatement("DELETE FROM schedule_need_change WHERE schedule_need_change.Group=? and schedule_need_change.Displine!=?");
       psdd.setInt(1, rsdd.getInt("gp"));
       psdd.setInt(2, rsdd.getInt("dp"));
       psdd.executeUpdate();
       psdd=mdbc.myConnection.prepareStatement("DELETE FROM testchange1 WHERE testchange1.Group=? and  testchange1.Displine!=?");
       psdd.setInt(1, rsdd.getInt("gp"));
       psdd.setInt(2, rsdd.getInt("dp"));
       psdd.executeUpdate();
       }
       
       if (practik_check==1){
       psdd=mdbc.myConnection.prepareStatement("SELECT schedule_need_change.Group as gp, schedule_need_change.Lecturer as lp FROM schedule_need_change, nag, lecturer WHERE schedule_need_change.Group=nag.id_Groups and schedule_need_change.Displine=nag.id_Discipline and schedule_need_change.Lecturer=lecturer.id_Lecturer  and schedule_need_change.Lecturer IN (SELECT schedule_need_change.Lecturer FROM schedule_need_change, nag, discipline WHERE schedule_need_change.Group=nag.id_Groups and schedule_need_change.Displine=nag.id_Discipline and schedule_need_change.Displine=discipline.id_Discipline and schedule_need_change.Lecturer in (SELECT schedule_need_change.Lecturer FROM schedule_need_change, nag, discipline, lecturer WHERE schedule_need_change.Group=nag.id_Groups and schedule_need_change.Displine=nag.id_Discipline and schedule_need_change.Displine=discipline.id_Discipline and nag.st_practic=1 and schedule_need_change.Lecturer=lecturer.id_Lecturer GROUP BY schedule_need_change.Lecturer)) and 1 IN (SELECT COUNT(para)>3 FROM schedule_need_change Group BY schedule_need_change.Lecturer) GROUP BY schedule_need_change.Group, schedule_need_change.Displine, schedule_need_change.Lecturer  ORDER BY para DESC limit 1");
       rsdd=psdd.executeQuery();
       while (rsdd.next()){
       psdd=mdbc.myConnection.prepareStatement("DELETE FROM schedule_need_change WHERE schedule_need_change.Group=? and schedule_need_change.Lecturer=?");
       psdd.setInt(1, rsdd.getInt("gp"));
       psdd.setInt(2, rsdd.getInt("lp"));
       psdd.executeUpdate();
       psdd=mdbc.myConnection.prepareStatement("DELETE FROM testchange1 WHERE testchange1.Group=? and  testchange1.Lecturer=?");
       psdd.setInt(1, rsdd.getInt("gp"));
       psdd.setInt(2, rsdd.getInt("lp"));
       psdd.executeUpdate();

        pst=mdbc.myConnection.prepareStatement("SELECT groups.id_Groups as gi, lecturer.surname, lecturer.id_Lecturer as li, discipline.id_Discipline as di FROM schedule_need_change, lecturer, discipline, groups "
                + "WHERE schedule_need_change.Displine=discipline.id_Discipline and schedule_need_change.Lecturer=lecturer.id_Lecturer and lecturer.status='Работает' and week=? and day=? "
                + "and lecturer.surname IN (SELECT lecturer.surname FROM nag, groups, lecturer, discipline "
                + "WHERE nag.id_Discipline=discipline.id_Discipline and nag.id_Groups=groups.id_Groups and discipline.Lecturer=lecturer.id_Lecturer and groups.id_Groups=? GROUP BY surname) "
                + "and groups.id_Groups=? and surname IN (SELECT surname FROM schedule_need_change, lecturer WHERE schedule_need_change.Lecturer=lecturer.id_Lecturer and week=? and day=? "
                + "and (SELECT COUNT(para) FROM `schedule_need_change` WHERE schedule_need_change.Lecturer=lecturer.id_Lecturer and week=? and day=?)<3 GROUP BY surname)  "
                + "GROUP BY schedule_need_change.Group, schedule_need_change.Displine, schedule_need_change.Lecturer "
                + "and surname IN (SELECT lecturer.surname FROM `storage`, schedule_need_change, lecturer "
                + "WHERE schedule_need_change.Group=`storage`.Group and schedule_need_change.Lecturer = `storage`.Lecturer "
                + "and schedule_need_change.Displine=`storage`.`Disciplane` and `storage`.`Lecturer`=lecturer.id_Lecturer and lecturer.status='Работает' and `storage`.hours_s>0 "
                + "Group by schedule_need_change.Group, schedule_need_change.Displine, schedule_need_change.Lecturer) ORDER BY COUNT(para), rand() ASC limit 1");
        pst.setString(1,week1);
        pst.setString(2, day1);
        pst.setInt(3, rsdd.getInt("gp"));
        pst.setInt(4, rsdd.getInt("gp"));
        pst.setString(5,week1);
        pst.setString(6, day1);
        pst.setString(7,week1);
        pst.setString(8, day1);
        rst=pst.executeQuery();
        while (rst.next()){
        PreparedStatement psi=mdbc.myConnection.prepareStatement("INSERT INTO `testchange1`(`Group`, `Displine`, `Lecturer`, `hours_today`,`hours_count_s`,`debt`) VALUES (?,?,?,?,?,?)");
        psi.setInt(1,rst.getInt("gi"));
        psi.setInt(2,rst.getInt("di"));
        psi.setInt(3,rst.getInt("li"));
        psi.setInt(4,1);
        psi.setInt(5,0);
        psi.setInt(6,0);
        psi.executeUpdate();
        psi=mdbc.myConnection.prepareStatement("INSERT INTO `schedule_need_change`(`day`, `day_n`, `para`, `Group`, `Lecturer`, `Displine`, `week`) VALUES (?, ?, ?, ?, ?, ?, ?)");
        psi.setString(1, dayBox.getSelectedItem().toString());
        psi.setInt(2, 0);
        psi.setInt(3, 99-index);
        psi.setInt(4,rst.getInt("gi"));
        psi.setInt(5,rst.getInt("di"));
        psi.setInt(6,rst.getInt("li"));
        psi.setString(7,  weekBox.getSelectedItem().toString());
        psi.executeUpdate();
        System.out.println(index+"wtf");
        }
        
       }
       }  
        
        if ((index!=0)||(index==0)){
      
        /**На основе полученного расписания, расставляем количество пар */
       int k=0;
        PreparedStatement plo=mdbc.myConnection.prepareStatement("SELECT testchange1.Group as g, testchange1.Displine as d, testchange1.Lecturer as l FROM `testchange1` ");
        rst=plo.executeQuery();
        while (rst.next()){
        PreparedStatement pht=mdbc.myConnection.prepareStatement("SELECT COUNT(*) as ht FROM testchange1 WHERE testchange1.Group=? AND testchange1.Lecturer=? and testchange1.Displine=?");
        pht.setInt(1,rst.getInt("g"));
        pht.setInt(2,rst.getInt("l"));
        pht.setInt(3,rst.getInt("d"));
        rht=pht.executeQuery();
        while (rht.next()){
         k++;   

        pht=mdbc.myConnection.prepareStatement("UPDATE `testchange1` SET `hours_today`=? WHERE  testchange1.Group=? AND testchange1.Lecturer=? and testchange1.Displine=?");
        pht.setInt(1,rht.getInt("ht"));
        pht.setInt(2,rst.getInt("g"));
        pht.setInt(3,rst.getInt("l"));
        pht.setInt(4,rst.getInt("d"));
        pht.executeUpdate();
        }
        }


        
        
        /**Формируем файлы*/
        PreparedStatement preparedStatementes;
            File filewG = new File("C://Schedulesrc/GroupsScheduleChange.txt");
            PrintWriter w1g = null;
            File fileT = new File("C://Schedulesrc/TeachersScheduleChange.txt");
            PrintWriter w1t = null;
        
        String groupsline = "";
        String teacherline = "";    
        PreparedStatement psgl=mdbc.myConnection.prepareStatement("SELECT testchange1.Group as g, testchange1.Lecturer as l, hours_today as ht FROM `testchange1` GROUP BY testchange1.Group, testchange1.Displine, testchange1.Lecturer");
        rsgl=psgl.executeQuery();
        while (rsgl.next()){
        for (int i=0;i<rsgl.getInt("ht");i++){
        groupsline = groupsline + rsgl.getInt("g") + ",";
       teacherline = teacherline + rsgl.getInt("l") + ",";
        }
        }
        groupsline = groupsline.substring(0, groupsline.length() - 1);
        teacherline = teacherline.substring(0, teacherline.length() - 1);
            w1g = new PrintWriter(filewG, "UTF-8");
            w1g.println(groupsline);
            w1g.close();
            w1t = new PrintWriter(fileT, "UTF-8");
            w1t.println(teacherline);
            w1t.close(); 
            File filetopweek = new File("src/ScheduleC#/netcoreapp3.1change/ScheduleDownWeek.exe");
            Desktop.getDesktop().open(filetopweek);
            check=0;
        } else {
         check=0;   
        }
       } catch (Exception e){

       e.printStackTrace();
       }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void TeachersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeachersTableMousePressed
            int rows = TeachersTable.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 6; i++) {
            System.out.println(TeachersTable.getValueAt(rows, i));
            values1 = values1 + TeachersTable.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        IdTeacherField.setText(ring[0]);
        LongNameField1.setText(ring[1]);
        ShortNameField1.setText(ring[2]);
        SecondNameField.setText(ring[3]);
        WorkStatus.setSelectedItem(ring[4]);
        prioritykabField.setText(ring[5]);
        try{
        PreparedStatement psn = mdbc.myConnection.prepareStatement("SELECT need_in_kab_id as nk FROM lecturer WHERE lecturer.id_Lecturer=?");
        psn.setString(1, ring[0]);
        ResultSet rs;
        rs=psn.executeQuery();
        while (rs.next()){
        int nk=rs.getInt("nk");
        switch (nk) {
                                        case 0:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            
                                            break;
                                        case 1:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(true);
                                            break;
                                        case 2:
            PCFlag.setSelected(false);
            SportFlag.setSelected(true);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                        case 3:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(true);
            MasterFlag.setSelected(false);
                                            break;
                                        case 4:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(true);
            MasterFlag.setSelected(false);
                                            break;
                                        case 5:
            PCFlag.setSelected(true);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(true);
            MasterFlag.setSelected(false);
                                            
                                            break;
                                        case 6:
            PCFlag.setSelected(true);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(false);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                        case 7:
            PCFlag.setSelected(true);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                        case 8:
            PCFlag.setSelected(false);
            SportFlag.setSelected(false);
            DeskFlag.setSelected(true);
            ShowFlag.setSelected(false);
            MasterFlag.setSelected(false);
                                            break;
                                    } 
        }
        
        } catch (SQLException se){
        se.printStackTrace();
        }
    }//GEN-LAST:event_TeachersTableMousePressed

    private void GroupsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GroupsTableMousePressed
            int rows = GroupsTable.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 4; i++) {
            System.out.println(GroupsTable.getValueAt(rows, i));
            values1 = values1 + GroupsTable.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        IdGroupField.setText(ring[0]);
        LongNameField.setText(ring[2]);
        ShortNameField.setText(ring[1]);
        jTextField3.setText(ring[3]);
        GroupComboBox.setSelectedItem(ring[4]);
    }//GEN-LAST:event_GroupsTableMousePressed

    private void DispTableForeverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DispTableForeverMousePressed
           int rows = DispTableForever.getSelectedRow();
        String DC=" ";
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 5; i++) {
            System.out.println(DispTableForever.getValueAt(rows, i));
            values1 = values1 + DispTableForever.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        GroupNagBox.setSelectedItem(ring[1]);
              try{
       PreparedStatement pst=mdbc.myConnection.prepareStatement("SELECT id_Discipline as id, discipline.name as dn, surname as s, lecturer.name as n, lecturer.second_name as sn FROM discipline, lecturer WHERE discipline.Lecturer=lecturer.id_Lecturer and discipline.name LIKE ?");
       pst.setString(1, ring[0]+"%");
       ResultSet rs8;
       rs8=pst.executeQuery();
       while (rs8.next()) {
       int index = 0;
       DC=rs8.getString("dn");
       index = DC.lastIndexOf(" ");
       if (index!=-1){
       DC=DC.substring(0, index);
       }
        System.out.print(rs8.getString("id")+" - "+DC+" - "+rs8.getString("s")+" "+rs8.getString("n").charAt(0)+". "+rs8.getString("sn").charAt(0)+".");
        DCBOX.setSelectedItem(rs8.getString("id")+" - "+DC+" - "+rs8.getString("s")+" "+rs8.getString("n").charAt(0)+". "+rs8.getString("sn").charAt(0)+".");
               }}
       catch(Exception e){
        e.printStackTrace();
       }
        TopWeekHoursField.setText(String.valueOf(Integer.parseInt(ring[2])*2));
        DownWeekHoursField.setText(String.valueOf(Integer.parseInt(ring[3])*2));
        if (ring[4].equals("Да")) {
            praktickcheckbox.setSelected(true);
        } else {
            praktickcheckbox.setSelected(false);
        }
        if (ring[5].equals("Да")) {
            jCheckBox1.setSelected(true);
        } else {
            jCheckBox1.setSelected(false);
        }
    }//GEN-LAST:event_DispTableForeverMousePressed

    private void DispTableForever1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DispTableForever1MousePressed
      int rows = DispTableForever1.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 2; i++) {
            System.out.println(DispTableForever1.getValueAt(rows, i));
            values1 = values1 + DispTableForever1.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        IdDispField.setText(ring[0]);
        String[] teacher = ring[2].split(" ");

        String full = String.valueOf(teacher[0] + " " + teacher[1].charAt(0) + ". " + teacher[2].charAt(0) + ".");
        DispTeacher2.setSelectedItem(full);
 
        DispNameFull1.setText(ring[1]);
    }//GEN-LAST:event_DispTableForever1MousePressed

    private void KabinetTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabinetTableMousePressed
         int rows = KabinetTable.getSelectedRow();
        System.out.println(rows);
        String values1 = "";
        for (int i = 0; i <= 6; i++) {
            System.out.println(KabinetTable.getValueAt(rows, i));
            values1 = values1 + KabinetTable.getValueAt(rows, i).toString() + "-";
            System.out.println(values1);
        }
        String[] ring = values1.split("-");
        for (int i = 0; i < ring.length; i++) {
            System.out.println(ring[i]);
        }
        jTextField1.setText(ring[0]);
        StatusKabBox.setSelectedItem(ring[1]);
        if (ring[2].equals("Да")) {
            PCFlag1.setSelected(true);
        } else {
            PCFlag1.setSelected(false);
        }
        if (ring[3].equals("Да")) {
            DeskFlag1.setSelected(true);
        } else {
            DeskFlag1.setSelected(false);
        }
        if (ring[4].equals("Да")) {
            ShowFlag1.setSelected(true);
        } else {
            ShowFlag1.setSelected(false);
        }
        if (ring[5].equals("Да")) {
            SportFlag1.setSelected(true);
        } else {
            SportFlag1.setSelected(false);
        }
        if (ring[6].equals("Да")) {
            MasterFlag1.setSelected(true);
        } else {
            MasterFlag1.setSelected(false);
        }
    }//GEN-LAST:event_KabinetTableMousePressed

    public void PracticCheck() {
        int lpi1 = 0;
        int lpi2 = 0;
        int gpi = 0;
        int dpi = 0;
        gpi = GroupPracticBox.getSelectedItem().toString().indexOf(" ");
        dpi = DispCoBox.getSelectedItem().toString().indexOf(" ");
        lpi1 = jComboBox6.getSelectedItem().toString().indexOf(" ");
        lpi2 = jComboBox7.getSelectedItem().toString().indexOf(" ");
        int LP1 = Integer.parseInt(jComboBox6.getSelectedItem().toString().substring(0, lpi1));
        int LP2 = Integer.parseInt(jComboBox7.getSelectedItem().toString().substring(0, lpi2));
        int GP = Integer.parseInt(GroupPracticBox.getSelectedItem().toString().substring(0, gpi));
        int DP = Integer.parseInt(DispCoBox.getSelectedItem().toString().substring(0, dpi));
        int hs = 0;
        String type = "";
        if (jCheckBox3.isSelected()) {
        } else {
            LP2 = 0;
        }
        try {
            int st=0; int pr=0;
            PreparedStatement prs1 = mdbc.myConnection.prepareStatement("SELECT hours_s/3 as hours, st_practic as st, pr_practic as pr FROM `storage` WHERE (st_practic=1 or pr_practic=1) and `storage`.`Group`=? and `storage`.`Disciplane`=? and `storage`.`Lecturer`=?");
            prs1.setInt(1, GP);
            prs1.setInt(2, DP);
            prs1.setInt(3, LP1);
            ResultSet rs1 = prs1.executeQuery();
            while (rs1.next()) {
                hs = rs1.getInt("hours");
                st = rs1.getInt("st");
                pr = rs1.getInt("pr");
            if (st==1) {
            daysField.setText(String.valueOf(hs));
            prType.setText("Учебная");
            } else if (pr==1) {
            daysField.setText(String.valueOf(hs));
            prType.setText("Производственная");
            } else {
            daysField.setText(" ");
            prType.setText(" ");
            st=0; pr=0;
            }
            }
        
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
    }
    
    public void startScheduleChange() {
        String week1=weekBox.getSelectedItem().toString();
        String day1=dayBox.getSelectedItem().toString();
        ResultSet rsm, rst, rsgl, rht; PreparedStatement pst; PreparedStatement psm;
        try{
         
         PreparedStatement psd=mdbc.myConnection.prepareStatement("Delete from testchange1");
        psd.executeUpdate();
        psd=mdbc.myConnection.prepareStatement("Delete from schedule_need_change");
        psd.executeUpdate();
        psd=mdbc.myConnection.prepareStatement("INSERT INTO schedule_need_change SELECT * FROM schedule_true WHERE week=? and day=?");
        psd.setString(1,week1);
        psd.setString(2, day1);
        psd.executeUpdate();
NewPracticDialog.setSize(hor_mid, vert_mid);
NewPracticDialog.setVisible(true);
PracticCheck();
       } catch (Exception e){

       e.printStackTrace();
       }
}
   
    
    
    
    
    
  public void nextScheduleChange() throws IOException{
        try {

            File file1 = new File("C://Schedulesrc/ScheduleChange.csv");
            File file = new File("C://Schedulesrc/SchedulePerChange.csv");
            ResultSet rsk;
            PrintWriter wtS = null;
            int kab_true = 0;
            wtS = new PrintWriter(file1, "CP1251");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            int k = 0;
            int l = 0;
            String check2 = "";
            String check4 = "";
            int id_DIsp = 0;
            ResultSet rsh;
            ResultSet rss;
            String dayt = "";
            int parat = 0;
            int dayit = 0;
            PreparedStatement preparedStatement7 = mdbc.myConnection.prepareStatement("Delete from schedule_change_ht");
            preparedStatement7.executeUpdate();
            preparedStatement7 = mdbc.myConnection.prepareStatement("Delete from sch_kab");
            preparedStatement7.executeUpdate();
            preparedStatement7 = mdbc.myConnection.prepareStatement("Delete from schedule_change_true");
            preparedStatement7.executeUpdate();
            preparedStatement7 = mdbc.myConnection.prepareStatement("SELECT DISTINCT hours_today AS sex, testchange1.Group as gp, testchange1.Displine as dp, testchange1.Lecturer as lp  FROM testchange1 GROUP BY testchange1.Group, testchange1.Displine, testchange1.Lecturer");
            ResultSet rsc = preparedStatement7.executeQuery();
            while (rsc.next()) {
                int gp = rsc.getInt("gp");
                int dp = rsc.getInt("dp");
                int lp = rsc.getInt("lp");
                int sex = rsc.getInt("sex");
                preparedStatement7 = mdbc.myConnection.prepareStatement("INSERT INTO `schedule_change_ht`(`id_Group`, `id_Discipline`, `id_Lecturer`, `hours_today`) VALUES (?,?,?,?)");
                preparedStatement7.setInt(1, gp);
                preparedStatement7.setInt(2, dp);
                preparedStatement7.setInt(3, lp);
                preparedStatement7.setInt(4, sex);
                preparedStatement7.executeUpdate();
            }

            /**
             * Цикл работает до того, пока строка не пустая
             */
            while (line != null) {
                /**
                 * Считываем только нужные нам строки
                 */
                if (k != 6) {
                    String self = line;
                    String result = self.replaceAll("\\s", ""); // удаляем пробелы
                    check2 = self;
                    String s = check4;
                    int daysgone = self.indexOf("Day ");
                    if (daysgone != 0) {
                        String para1 = String.valueOf(self.charAt(5));
                        parat = Integer.parseInt(para1);
                    }
                    /**
                     * Заменяем лишнии дефисы
                     */
                    String resultspace = result.replaceAll("Group-Teacher:", ";");
                    String[] resultcheck = resultspace.split(";");
                    for (int i = 0; i < resultcheck.length; i++) {
                        System.out.println(resultcheck[i]);
                        /**
                         * Работаем с подстрокой, в которой есть дефис
                         */
                        if (resultcheck[i].contains("-")) {
                            String DN = "";
                            String GN = "";
                            String TN = "";
                            String TNi = "";
                            String GNi = "";
                            int DNi = 0;
                            /**
                             * Разделяем строку и забираем требуемые айди
                             */
                            String[] gandt = resultcheck[i].split("-");
                            int gid = Integer.parseInt(gandt[0]);
                            int tid = Integer.parseInt(gandt[1]);
                            /**
                             * Получаем название групп вместо их айди
                             */
                            PreparedStatement preparedStatement1 = mdbc.myConnection.prepareStatement("SELECT short_name from "
                                    + "`groups` where id_Groups  =?");
                            preparedStatement1.setInt(1, gid);
                            ResultSet rs = preparedStatement1.executeQuery();
                            while (rs.next()) {
                                GN = rs.getString("short_name");
                            }

                            /**
                             * Получаем фамилии вместо айди
                             */
                            PreparedStatement preparedStatement2 = mdbc.myConnection.prepareStatement("SELECT lecturer.surname as s, lecturer.name as n, lecturer.second_name as sn  FROM lecturer WHERE lecturer.id_Lecturer=?");
                            preparedStatement2.setInt(1, tid);
                            ResultSet rs2 = preparedStatement2.executeQuery();
                            while (rs2.next()) {
                                TN = rs2.getString("s")+" "+rs2.getString("n").charAt(0)+". "+rs2.getString("sn").charAt(0)+".";
                            }
                            preparedStatement2 = mdbc.myConnection.prepareStatement("SELECT testchange1.Displine as DNI, discipline.name as DN, lecturer.surname as s, testchange1.hours_today as htw FROM testchange1, discipline, lecturer, groups WHERE testchange1.Displine=discipline.id_Discipline and testchange1.Group=groups.id_Groups and testchange1.Lecturer=lecturer.id_Lecturer and testchange1.Group=? and testchange1.Lecturer=? AND testchange1.hours_today!=0 Group BY testchange1.Group LIMIT 1");
                            preparedStatement2.setInt(1, gid);
                            preparedStatement2.setInt(2, tid);
                            rs2 = preparedStatement2.executeQuery();
                            while (rs2.next()) {
                                DN = rs2.getString("DN");
                                DNi = rs2.getInt("DNI");
                                System.out.println(tid+" "+DN+" "+gid+rs2.getString("htw"));
                            }
                            int index=0;
                            index=DN.lastIndexOf(" ");
                            if (index!=-1){
                            DN=DN.substring(0, index);}
                            TNi = String.valueOf(tid);
                            GNi = String.valueOf(gid);
                            /**
                             * Выполняем преобразования строки и заменяем в ней
                             * айди на название и айди на фамилию
                             */
                            check4 = check2.replaceAll("p-T", "p&T");
                            String check3 = check4.replaceAll("-", "___");
                            String check1 = check3.replaceAll(GNi + "_", GN);
                            int kab_ran = -99;
                            /**Получение приорететного кабинета*/
                            PreparedStatement psk = mdbc.myConnection.prepareStatement("SELECT priority_kabinet as pk FROM lecturer WHERE lecturer.id_Lecturer=?");
                            psk.setInt(1, tid);
                            rsk = psk.executeQuery();
                            while (rsk.next()) {
                                /**Получение статуса кабинета*/
                                int nki = 0;
                                boolean have = false;
                                String kab_s = "";
                                int kab = rsk.getInt("pk");
                                PreparedStatement pss = mdbc.myConnection.prepareStatement("SELECT kabinet_status as ks FROM `kabinet` WHERE number_of_kabinet=?");
                                pss.setInt(1, kab);
                                rss = pss.executeQuery();
                                while (rss.next()) {
                                    kab_s = rss.getString("ks");
                                }
                                /**Проверка занят ли он*/
                                System.out.println(kab_s);
                                if (kab_s.equals("Работает")) {
                                    psk = mdbc.myConnection.prepareStatement("SELECT `Have` as hv FROM `sch_kab` WHERE `para`=? AND kabinet=?");
                                    psk.setInt(1, parat);
                                    psk.setInt(2, kab);
                                    rsh = psk.executeQuery();
                                   while (rsh.next()) {
                                      have = rsh.getBoolean("hv");
                                        }
                                } else {
                                    have = true;
                                }
                                /**Если кабинет занят, то получаем код запроса преподавателя и получаем свободный кабинет, который и будет поставлен в расписание*/
                                if (have) {
                                    psk = mdbc.myConnection.prepareStatement("SELECT `need_in_kab_id` as nki FROM `lecturer`  WHERE `id_Lecturer`=?");
                                    psk.setInt(1, tid);
                                    rsh = psk.executeQuery();
                                    while (rsh.next()) {
                                        nki = rsh.getInt("nki");
                                    }
                                    switch (nki) {
                                        case 0:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh = psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 1:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_master='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 2:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 3:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' AND kabinet.flag_projector='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 4:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' AND kabinet.flag_projector='1' and kabinet.flag_docs='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 5:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' AND kabinet.flag_projector='1' and kabinet.flag_docs='1' AND kabinet.flag_computers='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 6:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' AND  kabinet.flag_computers='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 7:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' AND  kabinet.flag_computers='1' and kabinet.flag_docs='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                        case 8:
                                            psk = mdbc.myConnection.prepareStatement("SELECT `number_of_kabinet` as nk FROM `kabinet` WHERE kabinet.flag_sportzal='0' and kabinet.flag_master='0' AND kabinet.flag_docs='1' and kabinet.kabinet_status='Работает' and number_of_kabinet>1 and number_of_kabinet NOT IN (SELECT sch_kab.kabinet FROM sch_kab WHERE sch_kab.para=?) ORDER BY RAND() LIMIT 1");
                                            psk.setInt(1, parat);
                                            rsh=psk.executeQuery();
                                            while (rsh.next()) {
                                                kab_ran = rsh.getInt("nk");
                                            }
                                            break;
                                    }
                                    psk = mdbc.myConnection.prepareStatement(" INSERT INTO `sch_kab`(`id_Group`, `id_Discipline`, `id_Lecturer`, `para`, `kabinet`, `Have`) VALUES (?,?,?,?,?,?)");
                                    psk.setInt(1, gid);
                                    psk.setInt(2, DNi);
                                    psk.setInt(3, tid);
                                    psk.setInt(4, parat);
                                    psk.setInt(5, kab_ran);
                                    psk.setBoolean(6, true);
                                    psk.executeUpdate();
                                } else {
                                    kab_ran = rsk.getInt("pk");
                                    psk = mdbc.myConnection.prepareStatement(" INSERT INTO `sch_kab`(`id_Group`, `id_Discipline`, `id_Lecturer`, `para`, `kabinet`, `Have`) VALUES (?,?,?,?,?,?)");
                                    psk.setInt(1, gid);
                                    psk.setInt(2, DNi);
                                    psk.setInt(3, tid);
                                    psk.setInt(4, parat);
                                    psk.setInt(5, kab_ran);
                                    psk.setBoolean(6, true);
                                    psk.executeUpdate();
                                }
                            }
                            int parah = parat + 1;
                            check2 = check1.replaceAll("_" + TNi, DN + "   " + TN + "     " + " Кабинет: " + kab_ran);
                            check4 = check2;
                            PreparedStatement preparedStatement3 = mdbc.myConnection.prepareStatement(" INSERT INTO `schedule_change_true`(`Group`, `Disp`, `Lecturer`, `para`, `kabinet`) VALUES (?,?,?,?,?)");
                            preparedStatement3.setString(1, GN);
                            preparedStatement3.setString(2, DN);
                            preparedStatement3.setString(3, TN);
                            preparedStatement3.setInt(4, parah);
                            preparedStatement3.setInt(5, kab_ran);
                            preparedStatement3.executeUpdate();

                            System.out.println(check2);
                            preparedStatement3 = mdbc.myConnection.prepareStatement("UPDATE `testchange1` SET `hours_today`=(`hours_today`-'1') WHERE `Group`=? and `Displine`=? and`Lecturer`=?");
                            preparedStatement3.setInt(1, gid);
                            preparedStatement3.setInt(2, DNi);
                            preparedStatement3.setInt(3, tid);
                            preparedStatement3.executeUpdate();

                        }
                    }
                    wtS.println(check2);
                    k++;
                }
                line = reader.readLine();
            }
            preparedStatement7 = mdbc.myConnection.prepareStatement("SELECT *  FROM schedule_change_ht");
            rsc = preparedStatement7.executeQuery();
            while (rsc.next()) {
                int gp = rsc.getInt("id_Group");
                int dp = rsc.getInt("id_Discipline");
                int lp = rsc.getInt("id_Lecturer");
                int sex = rsc.getInt("hours_today");
                preparedStatement7 = mdbc.myConnection.prepareStatement("UPDATE `testchange1` SET `hours_today`=? WHERE `Group`=? and `Displine`=? and`Lecturer`=?");
                preparedStatement7.setInt(2, gp);
                preparedStatement7.setInt(3, dp);
                preparedStatement7.setInt(4, lp);
                preparedStatement7.setInt(1, sex);
                preparedStatement7.executeUpdate();
            }
            wtS.close();
            System.out.println("Успешно");

              ResultSet rsg=null, rsp=null;
        try {
        Workbook book = new HSSFWorkbook();
         Sheet sheet = book.createSheet(weekBox.getSelectedItem().toString()+" "+dayBox.getSelectedItem().toString());

        
          
          
          Font headerlabelfont = book.createFont();
        headerlabelfont.setFontHeightInPoints((short)11);
        headerlabelfont.setFontName("Times New Roman");
        headerlabelfont.setColor(IndexedColors.BLACK.getIndex());
        headerlabelfont.setBold(true);
        
         Font headerlabelfont1 = book.createFont();
        headerlabelfont1.setFontHeightInPoints((short)12);
        headerlabelfont1.setFontName("Times New Roman");
        headerlabelfont1.setColor(IndexedColors.BLACK.getIndex());
        headerlabelfont1.setBold(true);
        
        Font headerlabelfont2 = book.createFont();
        headerlabelfont2.setFontHeightInPoints((short)12);
        headerlabelfont2.setFontName("Times New Roman");
        headerlabelfont2.setColor(IndexedColors.BLACK.getIndex());

        
       CellStyle titleCenterGroup = book.createCellStyle();
        titleCenterGroup.setFont(headerlabelfont); 
         titleCenterGroup.setWrapText(true);
         titleCenterGroup.setAlignment(HorizontalAlignment.CENTER);
         titleCenterGroup.setVerticalAlignment(VerticalAlignment.CENTER);
         titleCenterGroup.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
          titleCenterGroup.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
         CellStyle titleCenter = book.createCellStyle();
         titleCenter.setWrapText(true);
         titleCenter.setAlignment(HorizontalAlignment.CENTER);
         titleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
         titleCenter.setFont(headerlabelfont1); 
        
         CellStyle Center = book.createCellStyle();
         titleCenter.setWrapText(true);
         titleCenter.setAlignment(HorizontalAlignment.CENTER);
         titleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
         titleCenter.setFont(headerlabelfont2); 
         
         Row row = sheet.createRow(0);
         Cell name = row.createCell(1);
         name.setCellStyle(titleCenter);
         name.setCellValue("Преподаватель");  
         name = row.createCell(2);
         name.setCellStyle(titleCenter);
         name.setCellValue("Дисциплина, МДК");
         name = row.createCell(3);
         name.setCellStyle(titleCenter);
         name.setCellValue("Пара");
         name = row.createCell(4);
         name.setCellStyle(titleCenter);
         name.setCellValue("Время\nприхода");
         name = row.createCell(5);
         name.setCellStyle(titleCenter);
         name.setCellValue("Аудитория");
         if (check==0){
         PreparedStatement psg = mdbc.myConnection.prepareStatement("SELECT `schedule_change_true`.Group as g FROM `schedule_change_true` GROUP BY schedule_change_true.Group");
         rsg=psg.executeQuery();}
         else {    
         PreparedStatement psg = mdbc.myConnection.prepareStatement("SELECT  groups.short_name as g FROM `schedule_true`, groups WHERE week=? and day=? and schedule_true.Group=groups.id_Groups GROUP BY schedule_true.Group");
         psg.setString(1, weekBox.getSelectedItem().toString());
         psg.setString(2, dayBox.getSelectedItem().toString());
         rsg=psg.executeQuery();
         }
         int index=1;
         while (rsg.next()){
         row=sheet.createRow(index);
         sheet.addMergedRegion(new CellRangeAddress(index,index,0,5));
         name = row.createCell(0);
         name.setCellStyle(titleCenterGroup);
         name.setCellValue(rsg.getString("g"));
         if (check==0){
         PreparedStatement psp = mdbc.myConnection.prepareStatement("SELECT `Disp` as d, `Lecturer` as l, `para` as p, `kabinet` as k FROM `schedule_change_true` WHERE schedule_change_true.Group=?"
                 + " ORDER BY schedule_change_true.Group, schedule_change_true.para");
         psp.setString(1, rsg.getString("g"));
         rsp=psp.executeQuery();}
         else {
         PreparedStatement psp = mdbc.myConnection.prepareStatement("SELECT  groups.short_name as g, lecturer.surname as l, discipline.name as d, para+1 as p "
                 + "FROM `schedule_true`, groups, lecturer, discipline "
                 + "WHERE week=? and day=? and schedule_true.Group=groups.id_Groups and schedule_true.Displine=discipline.id_Discipline and schedule_true.Lecturer=lecturer.id_Lecturer "
                 + "AND groups.short_name=? ORDER BY schedule_true.Group, schedule_true.para");
         psp.setString(1, weekBox.getSelectedItem().toString());
         psp.setString(2, dayBox.getSelectedItem().toString());
         psp.setString(3, rsg.getString("g"));
         rsp=psp.executeQuery();
         }
         int i=1;
         while (rsp.next()){
         row = sheet.createRow(index+i);
         name = row.createCell(1);
         name.setCellStyle(Center);
         name.setCellValue(rsp.getString("l"));  
         name = row.createCell(2);
         name.setCellStyle(Center);
         name.setCellValue(rsp.getString("d"));
         name = row.createCell(3);
         name.setCellStyle(Center);
         name.setCellValue(rsp.getString("p"));
         if (check==0){
         name = row.createCell(5);
         name.setCellStyle(Center);
         name.setCellValue(rsp.getString("k"));}
         i++;
         }    
         index=index+i;        
         }

         for (int i=0; i<999;i++){
          sheet.autoSizeColumn(i);
         }
         
      JFileChooser filec = new JFileChooser();
      filec.setDialogTitle("Выбор директории для сохранения файла");
      filec.setMultiSelectionEnabled(false);
      filec.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      filec.setFileHidingEnabled(false);
      if (filec.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
         java.io.File f = filec.getSelectedFile();
            try (FileOutputStream out = new FileOutputStream(new File(f.getPath()+"/"+weekBox.getSelectedItem().toString()+" "+dayBox.getSelectedItem().toString()+".xls"))) {
                book.write(out);
                book.close();
            }
            File filetopweek = new File(f.getPath()+"/"+weekBox.getSelectedItem().toString()+" "+dayBox.getSelectedItem().toString()+".xls");
            Desktop.getDesktop().open(filetopweek);
      }
             } catch (IOException ex) {
                 ex.printStackTrace();
             }  catch (SQLException se) {
                 se.printStackTrace();
             }
             System.out.println("Сделано");
        }  catch (SQLException se){
            MessageDialog.setVisible(true);
            MessLabel1.setText("Возникла ошибка, повторите попытку, если ситуация не изменится, повторите создание основного расписания");
            se.printStackTrace();
        }                               

  }  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainForm_v2().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace(er);
                    Logger.getLogger(MainForm_v2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public int indext=0;
    public int practik_check=0;
    public int check=0;
    public int day_n = 1;
    public int month = 1;
    public File error = new File("C://Schedulesrc/JavaErrorBackLog.txt");
    public static PrintWriter er = null;
    public String today = "";
    public int tday;
    public int vert = 0;
    public int hor = 0;
    public int hor_small = 0;
    public int vert_small = 0;
    public int hor_mid = 0;
    public int vert_mid = 0;
    public int ehit = 0;
    public int row = 0;
    public String weekname = "";
    public Desktop desktop = null;
    public String values = "";
    public static int week = 1;
    private int i = 0;
    private MyDBConnection mdbc;
    private java.sql.Statement stmt;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButtonForGroup;
    private javax.swing.JButton AddButtonForGroup1;
    private javax.swing.JButton AddButtonForGroup2;
    private javax.swing.JButton AddButtonForGroup4;
    private javax.swing.JButton AddButtonForTeachers;
    private javax.swing.JButton AddButtonForTeachers1;
    private javax.swing.JComboBox<String> DCBOX;
    private javax.swing.JButton DeleteButtonForGroup;
    private javax.swing.JButton DeleteButtonForGroup1;
    private javax.swing.JButton DeleteButtonForGroup2;
    private javax.swing.JButton DeleteButtonForTeachers;
    private javax.swing.JButton DeleteButtonForTeachers1;
    private javax.swing.JCheckBox DeskFlag;
    private javax.swing.JCheckBox DeskFlag1;
    private javax.swing.JButton DispButton;
    private javax.swing.JComboBox<String> DispCoBox;
    private javax.swing.JDialog DispDailog;
    private javax.swing.JTextField DispNameFull1;
    private javax.swing.JTable DispTableForever;
    private javax.swing.JTable DispTableForever1;
    private javax.swing.JComboBox<String> DispTeacher2;
    private javax.swing.JTextField DownWeekHoursField;
    private javax.swing.JButton EasyButton;
    private javax.swing.JButton EditButtonForDisp;
    private javax.swing.JButton EditButtonForGroup;
    private javax.swing.JButton EditButtonForGroup1;
    private javax.swing.JButton EditButtonForTeachers;
    private javax.swing.JButton EditButtonForTeachers1;
    private javax.swing.JComboBox<String> GroupComboBox;
    private javax.swing.JComboBox<String> GroupNagBox;
    private javax.swing.JComboBox<String> GroupPracticBox;
    private javax.swing.JButton GroupsButton;
    private javax.swing.JDialog GroupsDialog;
    private javax.swing.JTable GroupsTable;
    private javax.swing.JButton HardButton;
    private javax.swing.JTextField IdDispField;
    private javax.swing.JTextField IdGroupField;
    private javax.swing.JTextField IdTeacherField;
    private javax.swing.JButton KabinetButton;
    private javax.swing.JDialog KabinetDialog;
    private javax.swing.JTable KabinetTable;
    private javax.swing.JTextField LongNameField;
    private javax.swing.JTextField LongNameField1;
    private javax.swing.JCheckBox MasterFlag;
    private javax.swing.JCheckBox MasterFlag1;
    private javax.swing.JLabel MessLabel1;
    private javax.swing.JDialog MessageDialog;
    private javax.swing.JDialog NagDialog;
    private javax.swing.JDialog NewPracticDialog;
    private javax.swing.JCheckBox PCFlag;
    private javax.swing.JCheckBox PCFlag1;
    private javax.swing.JTextField RemainsHourField;
    private javax.swing.JTextField SecondNameField;
    private javax.swing.JComboBox<String> SecondTeacherBox;
    private javax.swing.JTextField ShortNameField;
    private javax.swing.JTextField ShortNameField1;
    private javax.swing.JCheckBox ShowFlag;
    private javax.swing.JCheckBox ShowFlag1;
    private javax.swing.JCheckBox SportFlag;
    private javax.swing.JCheckBox SportFlag1;
    private javax.swing.JComboBox<String> StatusKabBox;
    private javax.swing.JDialog StatusTeacherDialog;
    private javax.swing.JComboBox<String> TeacherListBox;
    private javax.swing.JButton TeachersButton;
    private javax.swing.JDialog TeachersDialog;
    private javax.swing.JTable TeachersTable;
    private javax.swing.JTextField TopWeekHoursField;
    private javax.swing.JComboBox<String> WorkStatus;
    private javax.swing.JComboBox<String> WorkStatus1;
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JTextField daysField;
    private javax.swing.JDialog goodDialog;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField prType;
    private javax.swing.JCheckBox praktickcheckbox;
    private javax.swing.JTextField prioritykabField;
    private javax.swing.JComboBox<String> weekBox;
    // End of variables declaration//GEN-END:variables
}
