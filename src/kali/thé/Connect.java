package kali.thé;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    /* 
     * @author lperr
     */
    public class Connect {
         /* 
         * Connect to a sample database 
         */
        public static void connect() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            Connection conn = null;
            try {
                // db parameters
                Class.forName("org.sqlite.JDBC").newInstance();
                String url = "jdbc:sqlite:semainespe.db";
                // mv semainespe.db
                // create a connection to the database
                conn = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        /** 
         * @param args the command line arguments 
         */
        public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            connect();
        }
    }