package palindrometester;
import java.sql.*;

public class DBConnector {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnector(){
    }

    public void setConnection(String password)throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9232853","sql9232853", password);
            st = con.createStatement();
        }
        catch(Exception ex){
            throw new IllegalArgumentException(ex);
        }
    }


    public boolean checkDB(String word)throws Exception{
        try{
            String query = "SELECT * FROM `palindrome` WHERE palindrome = \'" + word + "\'";
            rs = st.executeQuery(query);
            if(rs.next()==false){
                return false;
            }
            else{
                return true;
            }
        }
        catch(Exception ex){
            throw new IllegalArgumentException(ex);
        }
    }

    public void addPalindrome(String word, boolean alphaOnly, boolean alphanumericOnly, boolean caseSensitive, boolean allowSpace, boolean ignoreSpace, boolean ignorePunctuation)throws Exception{
        try{
            String query = "INSERT INTO `palindrome`(`palindrome`, `alphaOnly`, `alphanumericOnly`, `caseSensitive`, `allowSpace`, `ignoreSpace`, `ignorePunctuation`) VALUES (\'" + word + "\',"+ alphaOnly +","+ alphanumericOnly +","+ caseSensitive +","+ allowSpace +","+ ignoreSpace +","+ ignorePunctuation +")";
            st.executeUpdate(query);
        }
        catch(Exception ex){
            throw new IllegalArgumentException(ex);
        }
    }
}
