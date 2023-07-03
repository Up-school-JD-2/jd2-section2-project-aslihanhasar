import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PhoneManagementApp {
    private Scanner scanner;
    private Map<String, MobilePhone> mobilePhones;
    private Map<MobilePhone, Set<Contact>> contacts;
    private Map<MobilePhone, Set<Application>> applications;
    private double totalStorage;

    public PhoneManagementApp() {
        scanner = new Scanner(System.in);
        mobilePhones = new HashMap<>();
        contacts = new HashMap<>();
        applications = new HashMap<>();
    }

    public void addMobilePhone(MobilePhone mobilePhone) {
        mobilePhones.put(mobilePhone.getSerialNumber(), mobilePhone);
        contacts.put(mobilePhone, new HashSet<>());
        applications.put(mobilePhone, new HashSet<>());
    }

    public void removeMobilePhone(String serialNumber) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        mobilePhones.remove(serialNumber, mobilePhone);
        contacts.remove(mobilePhone);
        applications.remove(mobilePhone);
    }

    public void listMobilePhones() {
        for (Map.Entry<String, MobilePhone> entry : mobilePhones.entrySet()) {
            String serialNumber = entry.getKey();
            MobilePhone mobilePhone = entry.getValue();
            System.out.println("\nMobile Phone Serial Number: " + serialNumber +
                    "\n" + mobilePhone.toString());
        }
    }

    public MobilePhone searchPhoneBySerialNumber(String serialNumber) {
        return mobilePhones.get(serialNumber);
    }

    public void addContactToPhone(String serialNumber, Contact contact) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        Set<Contact> contactSet = contacts.get(mobilePhone);
        contactSet.add(contact);
        contacts.put(mobilePhone, contactSet);
    }

    public void removeContactFromPhone(String serialNumber, String fullName) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        Set<Contact> contactSet = contacts.get(mobilePhone);
        boolean contactEqual = isContactsPair(contactSet, fullName);
        if (contactEqual) {
            Contact contactByFullName = getContactByFullName(fullName);
            contactSet.remove(contactByFullName);
            contacts.put(mobilePhone, contactSet);
        } else {
            System.out.println("No such a contact.");
        }
    }

    public void updateContact(String serialNumber, String oldContactFullName, Contact newContact) {
        removeContactFromPhone(serialNumber, oldContactFullName);
        addContactToPhone(serialNumber, newContact);
    }

    public List<Contact> searchContactBy(Predicate<Contact> filterPredicate) {
        return contacts.values().stream().flatMap(Set::stream).filter(filterPredicate).toList();
    }

    public List<Contact> listContacts() {
        return contacts.values().stream().flatMap(Set::stream).toList();
    }

    public void addApplicationToPhone(String serialNumber, Application application) {
        double appSize = application.getSize();
        totalStorage += appSize;
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        boolean storageFull = isStorageFull(mobilePhone);
        if (!storageFull) {
            Set<Application> applicationsSet = applications.get(mobilePhone);
            applicationsSet.add(application);
            applications.put(mobilePhone, applicationsSet);
        } else {
            System.out.println("Storage is full.");
        }

    }

    public void removeApplicationFromPhone(String serialNumber, String appTitle) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        Set<Application> applicationsSet = applications.get(mobilePhone);
        boolean appEqual = isApplicationsPair(applicationsSet, appTitle);
        if (appEqual) {
            Application application = getApplicationByTitle(appTitle);
            applicationsSet.remove(application);
            applications.put(mobilePhone, applicationsSet);
        } else {
            System.out.println("No such an application found.");
        }
    }

    public void updateApplication(String serialNumber, String appTitle, String version, BiConsumer<Application, String> updateFunction) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        Set<Application> applicationsSet = applications.get(mobilePhone);
        boolean appEqual = isApplicationsPair(applicationsSet, appTitle);
        if (appEqual) {
            Application appByTitle = getApplicationByTitle(appTitle);
            updateFunction.accept(appByTitle, version);
            System.out.println("Application version updated");
        } else {
            System.out.println("No such app found");
        }
    }

    public List<Application> searchApplicationBy(Predicate<Application> filterPredicate) {
        return applications.values().stream().flatMap(Set::stream).filter(filterPredicate).toList();
    }

    public List<Application> listApplications() {
        return applications.values().stream().flatMap(Set::stream).toList();
    }

    public void groupAppsByCategory() {
        Map<AppCategory, List<Application>> collect = applications.values().stream().flatMap(Set::stream).collect(Collectors.groupingBy(Application::getCategory));
        System.out.println(collect);
    }

    public double getFullStorageSpace(String serialNumber) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        Set<Application> applicationSet = applications.get(mobilePhone);
        return applicationSet.stream().mapToDouble(Application::getSize).sum();
    }

    public double getRemainingStorageSpace(String serialNumber) {
        MobilePhone mobilePhone = mobilePhones.get(serialNumber);
        double fullStorage = getFullStorageSpace(serialNumber);
        double totalStorage = mobilePhone.getStorage();
        System.out.println("Total Storage Space: " + totalStorage);
        return totalStorage - fullStorage;
    }

    public Set<Contact> getAllContactsFromPhone(MobilePhone mobilePhone) {
        return contacts.get(mobilePhone);
    }

    public Set<Application> getAllAppsFromPhone(MobilePhone mobilePhone) {
        return applications.get(mobilePhone);
    }

    public MobilePhone createMobilePhone() {
        System.out.print("Enter the mobile phone brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter the mobile phone model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the mobile phone serial number: ");
        String serialNumber = scanner.nextLine();
        System.out.print("Enter the mobile phone storage space: ");
        double storage = scanner.nextDouble();
        scanner.nextLine();
        OperatingSystem.listOperatingSystems();
        System.out.print("Enter the mobile phone operating system: ");
        String inputOS = scanner.nextLine();
        OperatingSystem operatingSystem = OperatingSystem.getOperatingSystem(inputOS);
        return new MobilePhone(brand, model, serialNumber, storage, operatingSystem);
    }

    public Contact createContact() {
        System.out.print("Enter the contact full name: ");
        String fullName = scanner.next();
        System.out.print("Enter the contact phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter the contact email: ");
        String email = scanner.next();
        return new Contact(fullName, phoneNumber, email);
    }

    public Application createApplication() {
        System.out.print("Enter the application name: ");
        String appTitle = scanner.next();
        System.out.print("Enter the application version: ");
        String version = scanner.next();
        AppCategory.listCategories();
        System.out.print("Enter the application category: ");
        String inputCategory = scanner.next();
        AppCategory category = AppCategory.getAppCategory(inputCategory);
        System.out.print("Enter the application size: ");
        double size = scanner.nextDouble();
        return new Application(appTitle, version, category, size);
    }

    private Application getApplicationByTitle(String appTitle) {
        return applications.values().stream()
                .flatMap(Set::stream)
                .filter(application -> application.getAppTitle().equalsIgnoreCase(appTitle))
                .findAny()
                .orElse(null);
    }

    private Contact getContactByFullName(String fullName) {
        return contacts.values().stream()
                .flatMap(Set::stream)
                .filter(contact -> contact.getFullName().equalsIgnoreCase(fullName))
                .findAny()
                .orElse(null);
    }

    private Contact getContactByPhoneNumber(String phoneNumber) {
        return contacts.values().stream()
                .flatMap(Set::stream)
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .findAny()
                .orElse(null);
    }

    private boolean isStorageFull(MobilePhone mobilePhone) {
        double phoneStorage = mobilePhone.getStorage();
        return !(totalStorage <= phoneStorage);
    }

    private boolean isContactsPair(Set<Contact> contactSet, String fullName) {
        for (Contact c : contactSet) {
            boolean contactEqual = c.getFullName().equalsIgnoreCase(fullName);
            if (!contactEqual) {
                break;
            }
        }
        return true;
    }

    private boolean isApplicationsPair(Set<Application> appSet, String appTitle) {
        for (Application a : appSet) {
            boolean appEqual = a.getAppTitle().equalsIgnoreCase(appTitle);
            if (!appEqual) {
                break;
            }
        }
        return true;
    }

}

