package vilnius.tech.dal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company extends BaseOid implements Serializable {

    public Company(Session session) {
        super(session);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    private String name;
    private ContactInformation contactInformation;

    private final ArrayList<JuridicalUser> juridicalUsers = new ArrayList<>();
    private final ArrayList<PhysicalUser> physicalUsers = new ArrayList<>();
    private final ArrayList<FinancialCategory> categories = new ArrayList<>();
    private final ArrayList<ContactInformation> contactsInformation = new ArrayList<>();
    private final ArrayList<Address> addresses = new ArrayList<>();
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<Country> countries = new ArrayList<>();
    private final ArrayList<ExpenseType> expenseTypes = new ArrayList<>();
    private final ArrayList<IncomeType> incomeTypes = new ArrayList<>();

    public List<FinancialCategory> getCategories() {
        return categories;
    }

    public List<PhysicalUser> getPhysicalUsers() {
        return physicalUsers;
    }

    public List<JuridicalUser> getJuridicalUsers() {
        return juridicalUsers;
    }

    public List<ContactInformation> getContactsInformation() {
        return contactsInformation;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<ExpenseType> getExpenseTypes() {
        return expenseTypes;
    }

    public List<IncomeType> getIncomeTypes() {
        return incomeTypes;
    }

    @Override
    public String toString() {
        return String.format("Company information: %n%n") +
                String.format("Name: %s%n", name) +
                String.format("Contact Information: %n%s%n", contactInformation) +
                String.format("Juridical Users: %s%n", juridicalUsers.size()) +
                String.format("Physical Users: %s%n", physicalUsers.size()) +
                String.format("Categories: %s%n", categories.size()) +
                String.format("Contacts Information: %s%n", contactsInformation.size()) +
                String.format("Addresses: %s%n", addresses.size()) +
                String.format("Cities: %s%n", cities.size()) +
                String.format("Countries: %s%n", countries.size()) +
                String.format("Expense Types: %s%n", expenseTypes.size()) +
                String.format("Income Types: %s%n", incomeTypes.size());
    }

    @Override
    public String toShortString() {
        return String.format("Company Name: %s%n", name);
    }
}
