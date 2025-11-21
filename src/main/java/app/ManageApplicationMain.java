package app;

import data_access.DatabaseConnection;

public class ManageApplicationMain {

    public static void main(String[] args) throws Exception {

        var conn = DatabaseConnection.connect();

        // Optional: add dummy applications if DB is empty
        var gateway = new data_access.DatabaseApplicationGateway(conn);
        if (gateway.fetchAllApplications().isEmpty()) {
            System.out.println("DB empty: inserting test applications...");
            gateway.addApplication(new entity.Application(
                    "pet001", "Alice", "Smith", "alice@email.com",
                    "1234567890", "30", "Engineer", "Loves pets",
                    "Apartment", "Weekends", "Had pets before"
            ));
            gateway.addApplication(new entity.Application(
                    "pet002", "Bob", "Johnson", "bob@email.com",
                    "0987654321", "25", "Teacher", "Wants dog",
                    "House", "Weekdays", "None"
            ));
        }

        ManageApplicationAppBuilder appBuilder = new ManageApplicationAppBuilder(conn);
        appBuilder.buildAndShow();
    }
}
