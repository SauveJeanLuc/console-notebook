import java.sql.*;

public class ProjectDatabase {

    //JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/consolenotebook";

    //Database Credentials
    static final String USER = "root";
    static final String PASS = "donatien";

    private static int userID;


    public static void insert(String userName,String password) {

        Connection conn = null;
        Statement stmt = null;

        try{
            //Regiter the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //EXECUTE QUERY
            stmt = conn.createStatement();
            String sql;
            sql= "INSERT INTO users (userName, Password) VALUES ('" + userName +"','" + password + "')";
            stmt.executeUpdate(sql);
            //Clean up environment
            stmt.close();
            conn.close();

        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();;
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
            try {
                if(conn!=null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static boolean checkCredentials(String userName,String password){

        Connection conn = null;
        Statement stmt = null;

        try{
            //Regiter the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //EXECUTE QUERY
            stmt = conn.createStatement();
            String sql;
            sql= "SELECT userID, userName, password FROM users WHERE userName='"+userName+"' AND password='"+password+"';";
            ResultSet rs = stmt.executeQuery(sql);

            //Extract results
            if(rs==null){
                System.out.println("The resultSet is null");
            }
            while ( rs.next() ) {

                if( ( userName.equals(rs.getString("userName")) == true)
                        && password.equals(Integer.toString( rs.getInt("password") ) )
                ){
                    userID = rs.getInt("userID");
                    return true;
                }

            }
            //Clean up environment
            stmt.close();
            conn.close();

        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();;
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
            try {
                if(conn!=null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;

    }

    public static boolean userNameExists(String userName){


        Connection conn = null;
        Statement stmt = null;

        try{
            //Regiter the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //EXECUTE QUERY
            stmt = conn.createStatement();

            String sql;
            sql= "SELECT userName FROM users WHERE userName='"+userName+"'";
            ResultSet rs = stmt.executeQuery(sql);

            //Extract results
            if(rs==null){
                System.out.println("The resultSet is null");
            }
            while (rs.next()) {

                if(  ( userName.equals(rs.getString("userName") ) == true)  ){
                    return true;
                }

            }

            //Clean up environment
            stmt.close();
            conn.close();

        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();;
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
            try {
                if(conn!=null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;



    }

    public static void insertNotes(String Notes) {

        Connection conn = null;
        Statement stmt = null;

        try{
            //Regiter the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //EXECUTE QUERY
            stmt = conn.createStatement();
            String sql;
            sql= "INSERT INTO notes (notes, userID) VALUES ('" + Notes +"','" + userID + "')";
            stmt.executeUpdate(sql);
            //Clean up environment
            stmt.close();
            conn.close();

        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();;
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
            try {
                if(conn!=null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }

    }
}
