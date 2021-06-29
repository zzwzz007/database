package msm;

import java.sql.*;
import java.util.ArrayList;

public class MySqlConnect {
    //�����������ݿ�URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airlineinfo?useUnicode=true&"
            + "characterEncoding=GBK" + "&serverTimezone=GMT";

    // ���ݿ���û��������룬��Ҫ�����Լ�������
    private static final String USER = "root";
    private static final String PASS = "123456";

    public Connection connect = null;
    private Statement state = null;

    public MySqlConnect() {
        try {
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);

            // ������
            connect = DriverManager.getConnection(DB_URL, USER, PASS);

            // ִ�в�ѯ
            state = connect.createStatement();
            state.executeUpdate("set global max_write_lock_count = 1;");//���ö�д����Ȩ
        } catch (Exception se) {
            // ���� JDBC ����
            se.printStackTrace();
        }// ���� Class.forName ����

    }

    protected void finalize() {
        try {
            if (state != null) state.close();
        } catch (SQLException ignored) {
        }
        try {
            if (connect != null) connect.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet executeQuery(String command) {
        try {
            return state.executeQuery(command);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(100);

        System.out.print(list.contains(100));
    }
}

