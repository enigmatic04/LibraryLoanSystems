package service;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BusinessLogic {

    public void addMember(int id, String name) {

        String query =
                "INSERT INTO Members VALUES (?, ?, 0)";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setInt(1, id);
            ps.setString(2, name);

            ps.executeUpdate();

            System.out.println("Member added.");

        } catch (Exception e) {

            System.out.println(
                    "Member already exists."
            );
        }
    }

    public void addBook(
            int id,
            String title,
            String author
    ) {

        String query =
                "INSERT INTO Books VALUES (?, ?, ?, ?)";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setBoolean(4, true);

            ps.executeUpdate();

            System.out.println("Book added.");

        } catch (Exception e) {

            System.out.println(
                    "Book already exists."
            );
        }
    }
}