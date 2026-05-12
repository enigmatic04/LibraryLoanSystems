import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App {

    public static void main(String[] args) {

        try {

            String url = "jdbc:derby:LibraryDB;create=true";

            Connection con = DriverManager.getConnection(url);

            Statement st = con.createStatement();

            st.executeUpdate(
                "CREATE TABLE Books (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(50))"
            );

            st.executeUpdate(
                "INSERT INTO Books VALUES (1, 'Java Programming')"
            );

            System.out.println("Derby Database Connected!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}