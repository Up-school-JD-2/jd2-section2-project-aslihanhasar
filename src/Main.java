import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PhoneManagementApp appManagement = new PhoneManagementApp();
    private static final FileOperations fileOperations = new FileOperations();

    public static void main(String[] args) {
        boolean exit = false;
        do {
            printMenu();
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1 -> {
                    phoneMenu();
                    phoneOperations();
                }
                case 2 -> {
                    contactMenu();
                    contactOperations();
                }
                case 3 -> {
                    appMenu();
                    applicationOperations();
                }
                case 4 -> {
                    storageMenu();
                    storageOperations();
                }
                case 5 -> {
                    dataMenu();
                    dataOperations();
                }
                case 0 -> exit = true;

                default -> System.out.print("Enter a valid menuChoice.");

            }
        } while (!exit);

    }

    private static void phoneOperations() {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                MobilePhone phone = appManagement.createMobilePhone();
                appManagement.addMobilePhone(phone);
            }
            case 2 -> {
                System.out.print("Enter the serial number to remove phone from the app.");
                String serialNumber = scanner.next();
                appManagement.removeMobilePhone(serialNumber);
            }
            case 3 -> appManagement.listMobilePhones();
            default -> System.out.print("Enter a valid choice.");
        }
    }

    private static void contactOperations() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the phone serial number to operate contact: ");
        String serialNumber = scanner.next();
        switch (choice) {
            case 1 -> {
                Contact contact = appManagement.createContact();
                appManagement.addContactToPhone(serialNumber, contact);
            }
            case 2 -> {
                System.out.print("Enter the contact full name:");
                String fullName = scanner.next();
                appManagement.removeContactFromPhone(serialNumber, fullName);
            }
            case 3 -> {
                System.out.print("Enter the name of the contact to update:");
                String oldContactFullName = scanner.next();
                System.out.println("Enter the new contact information.");
                Contact newContact = appManagement.createContact();
                appManagement.updateContact(serialNumber, oldContactFullName, newContact);
            }
            case 4 -> contactSearchOperations();
            case 5 -> {
                List<Contact> listedContacts = appManagement.listContacts();
                System.out.println(listedContacts);
            }
            default -> System.out.print("Enter a valid choice");
        }
    }

    private static void applicationOperations() {
        int choice = scanner.nextInt();
        System.out.print("Enter the phone serial number to operate application: ");
        String serialNumber = scanner.next();
        switch (choice) {
            case 1 -> {
                Application application = appManagement.createApplication();
                appManagement.addApplicationToPhone(serialNumber, application);
            }
            case 2 -> {
                System.out.print("Enter the application name: ");
                String appTitle = scanner.next();
                appManagement.removeApplicationFromPhone(serialNumber, appTitle);
            }
            case 3 -> {
                System.out.print("Enter the application name: ");
                String appTitle = scanner.next();
                System.out.print("Enter the application new version: ");
                String newVersion = scanner.next();
                appManagement.updateApplication(serialNumber, appTitle, newVersion, (app, version) -> app.setVersion(newVersion));
            }
            case 4 -> {
                System.out.print("Enter the application name to search application: ");
                String searchedAppTitle = scanner.next();
                List<Application> searchedApps = appManagement.searchApplicationBy(
                        application -> application.getAppTitle().equalsIgnoreCase(searchedAppTitle));
                System.out.print("Searched application: " + searchedApps);
            }
            case 5 -> appManagement.groupAppsByCategory();
            case 6 -> {
                List<Application> applications = appManagement.listApplications();
                System.out.print(applications);
            }
            default -> System.out.print("Enter a valid choice");
        }
    }

    public static void storageOperations() {
        int choice = scanner.nextInt();
        System.out.print("Enter the phone serial number to check storage: ");
        String serialNumber = scanner.next();
        switch (choice) {
            case 1 -> {
                double fullStorage = appManagement.getFullStorageSpace(serialNumber);
                System.out.print("Full storage space: " + fullStorage);
            }
            case 2 -> {
                double remainingSpace = appManagement.getRemainingStorageSpace(serialNumber);
                System.out.print("Free storage space: " + remainingSpace);
            }
            default -> System.out.print("Enter a valid choice");
        }
    }

    public static void dataOperations() {
        int choice = scanner.nextInt();
        System.out.print("Enter the phone serial number to check storage: ");
        String serialNumber = scanner.next();
        switch (choice) {
            case 1 -> fileOperations.contactDataBackup(serialNumber, "contact.txt");
            case 2 -> fileOperations.applicationDataBackup(serialNumber, "application.txt");
            case 3 -> {
                Set<String> restoredContacts = fileOperations.contactRestoreDate("contact.txt");
                if (restoredContacts != null) {
                    for (String s : restoredContacts) {
                        System.out.println(s);
                    }
                }
            }
            case 4 -> {
                Set<String> restoredApplications = fileOperations.applicationRestoreDate("application.txt");
                if (restoredApplications != null) {
                    for (String s : restoredApplications) {
                        System.out.println(s);
                    }
                }
            }

        }
    }

    private static void contactSearchOperations() {
        System.out.print("""
                Search Options:
                1 - Search By Full Name
                2 - Search By Phone Number
                3 - Search by E mail
                Enter your choice :\040""");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.print("Enter the full name to search contact: ");
                String searchedContactFullName = scanner.next();
                List<Contact> searchedContacts = appManagement.searchContactBy(
                        contact -> contact.getFullName().equalsIgnoreCase(searchedContactFullName));
                System.out.print("Searched contact: " + searchedContacts);
            }
            case 2 -> {
                System.out.print("Enter the phone number to search contact: ");
                String searchedContactPhoneNumber = scanner.next();
                List<Contact> searchedContacts = appManagement.searchContactBy(
                        contact -> contact.getPhoneNumber().equalsIgnoreCase(searchedContactPhoneNumber));
                System.out.print("Searched contact: " + searchedContacts);
            }
            case 3 -> {
                System.out.println("Enter the e mail to search contact: ");
                String searchedContactEmail = scanner.next();
                List<Contact> searchedContacts = appManagement.searchContactBy(
                        contact -> contact.getEmail().equalsIgnoreCase(searchedContactEmail));
                System.out.print("Searched contact: " + searchedContacts);
            }
            default -> System.out.print("Enter a valid menuChoice.");
        }
    }

    private static void printMenu() {
        String str = """
                \n*********** Management Operations ***********
                 1 - Phone Management
                 2 - Contact Management
                 3 - Application Management
                 4 - Storage Management
                 5 - Data Management
                 0 - Exit
                 Enter your choice :\s""";
        System.out.print(str);
    }

    private static void phoneMenu() {
        String str = """
                \n*********** Phone Operations ***********
                 1 - Add a mobile phone to the app.\s
                 2 - Remove a mobile phone from the app.
                 3 - List mobile phones.
                 Enter your choice :\s""";
        System.out.print(str);
    }

    private static void contactMenu() {
        String str = """
                \n*********** Contact Operations ***********
                 1 - Add a contact to the phone.\s
                 2 - Delete a contact.
                 3 - Update a contact.
                 4 - Search a contact.
                 5 - List contacts.
                 Enter your choice :\s""";
        System.out.print(str);
    }

    private static void appMenu() {
        String str = """
                \n*********** Application Operations ***********
                 1 - Add an application to the phone.
                 2 - Delete an application.
                 3 - Update an application.
                 4 - Search an application by title.
                 5 - Group applications by category.
                 6 - List applications
                 Enter your choice :\s""";
        System.out.print(str);
    }

    private static void storageMenu() {
        String str = """
                \n*********** Storage Operations ***********
                 1 - Display full storage space.
                 2 - Display free storage space.
                 Enter your choice :\s""";
        System.out.print(str);
    }

    private static void dataMenu() {
        String str = """
                \n*********** Data Operations ***********
                 1 - Backup Contact Data.
                 2 - Backup Application Data.
                 3 - Restore Contact Data.
                 4 - Restore Application Data.
                 Enter your choice :\s""";
        System.out.print(str);
    }
}
