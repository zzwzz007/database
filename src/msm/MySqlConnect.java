package msm;

import java.sql.*;
import java.util.ArrayList;

public class MySqlConnect {
    //驱动名与数据库URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airlineinfo?useUnicode=true&"
            + "characterEncoding=GBK" + "&serverTimezone=GMT";

    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "root";
    private static final String PASS = "123456";

    public Connection connect = null;
    private Statement state = null;

    public MySqlConnect() {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            connect = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            state = connect.createStatement();
            state.executeUpdate("set global max_write_lock_count = 1;");//设置读写优先权
        } catch (Exception se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        }// 处理 Class.forName 错误

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

