package InfoSys.model;

import java.sql.*;

public class InfoSysDataBase {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //解决java.sql.SQLException: The server time zone value ‘XXXXXX’ is unrecognized or represents more than one time zone.###
    //加?serverTimezone=GMT%2B8  解决时区问题
    static final String DB_URL = "jdbc:mysql://localhost:3306/?serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PASS = "mysqltest";
    private static final String sqlStu = "CREATE TABLE student " +
            "(id INTEGER not NULL, " +
            " name VARCHAR(50) not NULL , " +
            " age INTEGER not NULL , " +
            " grade FLOAT not NULL , " +
            " PRIMARY KEY ( id ))";
    private static  final String sqlSta = "CREATE TABLE staff " +
            "(id INTEGER not NULL, " +
            " name VARCHAR(50) not NULL , " +
            " age INTEGER not NULL , " +
            " salary FLOAT not NULL , " +
            " jobs VARCHAR(50) not NULL , " +
            " PRIMARY KEY ( id ))";
    Connection conn = null;
    Statement stmt = null;
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        new InfoSysDataBase();
    }
    //初始化数据库
    public InfoSysDataBase() throws ClassNotFoundException, SQLException {

        //创建InfoSys数据库
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sqla = "CREATE DATABASE InfoSys";
            stmt.executeUpdate(sqla);
            System.out.println("Database created successfully...");
        }catch (Exception e){
            Class.forName(JDBC_DRIVER);
            String sqlb = "jdbc:mysql://localhost:3306/InfoSys?serverTimezone=GMT%2B8";
            conn = DriverManager.getConnection(sqlb, USER, PASS);
            stmt = conn.createStatement();
        }
        //创建student表
        try{
            System.out.println(stmt.executeUpdate(sqlStu));
        }catch (Exception e){
            System.out.println("student表已经存在了");
        }
        //创建staff表
        try{
            System.out.println(stmt.executeUpdate(sqlSta));
        }catch (Exception e){
            System.out.println("staff表已经存在了");
        }
//        String sqlf = "INSERT INTO student " +
//                "VALUES (1004, name, 22, 100)";
//        System.out.println(stmt.executeUpdate(sqlf));
//        stmt.close();
//        conn.close();
    }
}