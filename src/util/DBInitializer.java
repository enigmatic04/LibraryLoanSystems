package util;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    public static void initializeDatabase() {

        try (
                Connection con =
                        ConnectionManager.getConnection();

                Statement st =
                        con.createStatement()
        ) {

            try {

                st.executeUpdate(
                        "CREATE TABLE Members (" +
                                "member_id INT PRIMARY KEY, " +
                                "name VARCHAR(100), " +
                                "active_loans INT DEFAULT 0)"
                );

                System.out.println("Members table created.");

            } catch (Exception e) {
                System.out.println("Members table already exists.");
            }

            try {

                st.executeUpdate(
                        "CREATE TABLE Books (" +
                                "book_id INT PRIMARY KEY, " +
                                "title VARCHAR(100), " +
                                "author VARCHAR(100), " +
                                "available BOOLEAN)"
                );

                System.out.println("Books table created.");

            } catch (Exception e) {
                System.out.println("Books table already exists.");
            }

            try {

                st.executeUpdate(
                        "CREATE TABLE Loans (" +
                                "loan_id INT PRIMARY KEY, " +
                                "member_id INT REFERENCES Members(member_id), " +
                                "book_id INT REFERENCES Books(book_id), " +
                                "loan_date DATE, " +
                                "return_date DATE)"
                );

                System.out.println("Loans table created.");

            } catch (Exception e) {
                System.out.println("Loans table already exists.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}