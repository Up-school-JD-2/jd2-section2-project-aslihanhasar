import java.io.*;
import java.util.Set;

public class FileOperations {
    private final PhoneManagementApp managementApp = new PhoneManagementApp();

    public <T> void contactDataBackup(String serialNumber, String fileName) {
        try {
            MobilePhone mobilePhone = managementApp.searchPhoneBySerialNumber(serialNumber);
            Set<Contact> contacts = managementApp.getAllContactsFromPhone(mobilePhone);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(contacts);
            System.out.println("Contact Data backed up successfully " + fileName);
        } catch (IOException e) {
            System.out.println("Data Backup Error " + e.getMessage());
        }
    }

    public <T> void applicationDataBackup(String serialNumber, String fileName) {
        try {
            MobilePhone mobilePhone = managementApp.searchPhoneBySerialNumber(serialNumber);
            Set<Application> applications = managementApp.getAllAppsFromPhone(mobilePhone);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(applications);
            System.out.println("Application Data backed up successfully " + fileName);
        } catch (IOException e) {
            System.out.println("Data Backup Error " + e.getMessage());
        }
    }

    public <T> Set<T> contactRestoreDate(String fileName) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            System.out.println("Contacts data restored successfully. ");
            return (Set<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        return null;
    }

    public <T> Set<T> applicationRestoreDate(String fileName) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            System.out.println("Applications data restored successfully. ");
            return (Set<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        return null;
    }
}
