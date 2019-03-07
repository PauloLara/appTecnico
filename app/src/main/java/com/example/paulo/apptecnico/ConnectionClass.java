
package com.example.paulo.apptecnico;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionClass {

    String server = "192.168.15.17";
    String classe = "net.sourceforge.jtds.jdbc.Driver";
    String porta = "1433";
    String db = "appscout";
    String user = "sa";
    String password = "050310sqlserver";

    @SuppressLint("NewApi")
    Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnURL;
        Connection conn = null;
        try {
            Class.forName(classe);
            ConnURL = "jdbc:jtds:sqlserver://" + server + ":" + porta +";databaseName=" + db + ";user=" + user + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);

        }catch (SQLException se){
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e){
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e){
            Log.e("error here 3 : ", e.getMessage());
        }
        return conn;
    }
}



/*
import java.sql.*;

public class ConnectionClass {

    public static final String url = "jdbc:sqlserver://appscout.database.windows.net:1433;database=AppScout;user=paulopassword=050310Azure;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public static final String name = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;

    public static void main(String[] args) {

        try {

            String SQL ="select * from Usuario;";
            Class.forName(name);
            conn = DriverManager.getConnection(url);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void close() {
        try {
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/
