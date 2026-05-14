import benchmark.PerformanceEvaluator;
import java.util.Scanner;
import service.BusinessLogic;
import service.TransactionService;
import util.DBInitializer;

public class MainApp {

    public static void main(String[] args) {

        DBInitializer.initializeDatabase();

        BusinessLogic logic =
                new BusinessLogic();

        TransactionService ts =
                new TransactionService();

        PerformanceEvaluator pe =
                new PerformanceEvaluator();

        Scanner sc =
                new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LIBRARY MENU =====");

            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Process Loan");
            System.out.println("4. Return Book");
            System.out.println("5. View Books");
            System.out.println("6. View Members");
            System.out.println("7. View Active Loans");
            System.out.println("8. Run Performance Benchmark");
            System.out.println("9. Exit");

            System.out.print("\nEnter choice: ");

            int choice =
                    sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Enter Member ID: "
                    );

                    int memberId =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Enter Member Name: "
                    );

                    String memberName =
                            sc.nextLine();

                    logic.addMember(
                            memberId,
                            memberName
                    );

                    break;

                case 2:

                    System.out.print(
                            "Enter Book ID: "
                    );

                    int bookId =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Enter Book Title: "
                    );

                    String title =
                            sc.nextLine();

                    System.out.print(
                            "Enter Author: "
                    );

                    String author =
                            sc.nextLine();

                    logic.addBook(
                            bookId,
                            title,
                            author
                    );

                    break;

                case 3:

                    System.out.print(
                            "Enter Loan ID: "
                    );

                    int loanId =
                            sc.nextInt();

                    System.out.print(
                            "Enter Member ID: "
                    );

                    int mId =
                            sc.nextInt();

                    System.out.print(
                            "Enter Book ID: "
                    );

                    int bId =
                            sc.nextInt();

                    ts.processLoan(
                            loanId,
                            mId,
                            bId
                    );

                    break;

                case 4:

                    System.out.print(
                            "Enter Member ID: "
                    );

                    int rmId =
                            sc.nextInt();

                    System.out.print(
                            "Enter Book ID: "
                    );

                    int rbId =
                            sc.nextInt();

                    ts.returnBook(
                            rmId,
                            rbId
                    );

                    break;

                case 5:

                    logic.viewBooks();

                    break;

                case 6:

                    logic.viewMembers();

                    break;

                case 7:

                    logic.viewActiveLoans();

                    break;

                case 8:

                    pe.benchmarkBatchInsert(1000);

                    break;

                case 9:

                    System.out.println(
                            "Exiting System..."
                    );

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }
}
