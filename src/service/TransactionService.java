package service;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class TransactionService {

    // PROCESS LOAN

    public void processLoan(
            int loanId,
            int memberId,
            int bookId
    ) {

        Connection con = null;

        try {

            con =
                    ConnectionManager.getConnection();

            con.setAutoCommit(false);

            // CHECK AVAILABILITY

            String checkBook =
                    "SELECT available FROM Books " +
                            "WHERE book_id = ?";

            PreparedStatement checkStmt =
                    con.prepareStatement(checkBook);

            checkStmt.setInt(1, bookId);

            ResultSet rs =
                    checkStmt.executeQuery();

            boolean available = false;

            if (rs.next()) {
                available =
                        rs.getBoolean("available");
            }

            if (!available) {

                System.out.println(
                        "Book not available."
                );

                con.rollback();

                return;
            }

            Savepoint sp1 =
                    con.setSavepoint();

            // INSERT LOAN

            String insertLoan =
                    "INSERT INTO Loans VALUES " +
                            "(?, ?, ?, CURRENT_DATE, NULL)";

            PreparedStatement loanStmt =
                    con.prepareStatement(insertLoan);

            loanStmt.setInt(1, loanId);
            loanStmt.setInt(2, memberId);
            loanStmt.setInt(3, bookId);

            loanStmt.executeUpdate();

            // UPDATE BOOK STATUS

            String updateBook =
                    "UPDATE Books SET available = false " +
                            "WHERE book_id = ?";

            PreparedStatement bookStmt =
                    con.prepareStatement(updateBook);

            bookStmt.setInt(1, bookId);

            bookStmt.executeUpdate();

            // UPDATE MEMBER LOANS

            String updateMember =
                    "UPDATE Members " +
                            "SET active_loans = active_loans + 1 " +
                            "WHERE member_id = ?";

            PreparedStatement memberStmt =
                    con.prepareStatement(updateMember);

            memberStmt.setInt(1, memberId);

            memberStmt.executeUpdate();

            con.commit();

            System.out.println(
                    "Loan processed successfully."
            );

        } catch (Exception e) {

            try {

                if (con != null) {

                    System.out.println(
                            "Transaction failed. Rolling back..."
                    );

                    con.rollback();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);

                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // RETURN BOOK

    public void returnBook(
            int memberId,
            int bookId
    ) {

        Connection con = null;

        try {

            con =
                    ConnectionManager.getConnection();

            con.setAutoCommit(false);

            String deleteLoan =
                    "DELETE FROM Loans " +
                            "WHERE member_id = ? " +
                            "AND book_id = ? " +
                            "AND return_date IS NULL";

            PreparedStatement deleteStmt =
                    con.prepareStatement(deleteLoan);

            deleteStmt.setInt(1, memberId);
            deleteStmt.setInt(2, bookId);

            int rows =
                    deleteStmt.executeUpdate();

            if (rows == 0) {

                System.out.println(
                        "No active loan found."
                );

                con.rollback();

                return;
            }

            String updateBook =
                    "UPDATE Books SET available = true " +
                            "WHERE book_id = ?";

            PreparedStatement bookStmt =
                    con.prepareStatement(updateBook);

            bookStmt.setInt(1, bookId);

            bookStmt.executeUpdate();

            String updateMember =
                    "UPDATE Members " +
                            "SET active_loans = active_loans - 1 " +
                            "WHERE member_id = ?";

            PreparedStatement memberStmt =
                    con.prepareStatement(updateMember);

            memberStmt.setInt(1, memberId);

            memberStmt.executeUpdate();

            con.commit();

            System.out.println(
                    "Book returned successfully."
            );

        } catch (Exception e) {

            try {

                if (con != null) {

                    System.out.println(
                            "Return failed. Rolling back..."
                    );

                    con.rollback();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);

                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
