package service;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BusinessLogic {

    // ADD MEMBER

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

            System.out.println("Member added successfully.");

        } catch (Exception e) {

            System.out.println("Member already exists.");
        }
    }

    // ADD BOOK

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

            System.out.println("Book added successfully.");

        } catch (Exception e) {

            System.out.println("Book already exists.");
        }
    }

    // VIEW BOOKS

    public void viewBooks() {

        String query =
                "SELECT * FROM Books";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println("\n===== BOOK LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("book_id") +
                                " | " +
                                rs.getString("title") +
                                " | " +
                                rs.getString("author") +
                                " | Available: " +
                                rs.getBoolean("available")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW MEMBERS

    public void viewMembers() {

        String query =
                "SELECT * FROM Members";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println("\n===== MEMBER LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("member_id") +
                                " | " +
                                rs.getString("name") +
                                " | Active Loans: " +
                                rs.getInt("active_loans")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW ACTIVE LOANS

    public void viewActiveLoans() {

        String query =
                "SELECT * FROM Loans WHERE return_date IS NULL";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println("\n===== ACTIVE LOANS =====");

            while (rs.next()) {

                System.out.println(
                        "Loan ID: " + rs.getInt("loan_id") +
                                " | Member ID: " + rs.getInt("member_id") +
                                " | Book ID: " + rs.getInt("book_id") +
                                " | Loan Date: " + rs.getDate("loan_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
