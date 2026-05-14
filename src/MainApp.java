import service.BusinessLogic;
import util.DBInitializer;

public class MainApp {

    public static void main(String[] args) {

        // Initialize database & tables
        DBInitializer.initializeDatabase();

        // Create object of business logic
        BusinessLogic logic =
                new BusinessLogic();

        // Add sample member
        logic.addMember(
                1,
                "Jeet"
        );

        // Add sample book
        logic.addBook(
                101,
                "Java Programming",
                "James Gosling"
        );

        System.out.println(
                "Library System Started!"
        );
    }
}