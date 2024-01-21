class CarData {
    private String carBrand;
    private String yearOfRelease;
    private String stateNumber;

    public CarData(String brand, String year, String state) {
        carBrand = brand;
        yearOfRelease = year;
        stateNumber = state;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public String getStateNumber() {
        return stateNumber;
    }
}