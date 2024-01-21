class CustomerData {
    private String name;
    private String surname;
    private String phoneNumber;

    public CustomerData(String n, String s, String phone) {
        name = n;
        surname = s;
        phoneNumber = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}