class Car {
    private CustomerData custData;
    private CarData carData;
    private Engine engine;
    private WheelDrive wd;
    private CarBody carBody;

    public Car(CustomerData customer, CarData car, Engine eng, WheelDrive drive, CarBody body) {
        custData = customer;
        carData = car;
        engine = eng;
        wd = drive;
        carBody = body;
    }

    // ... (остальные методы остаются без изменений)

    public String getEngineType() {
        switch (engine) {
            case PETROL:
                return "Бензиновый";
            case DIESEL:
                return "Дизельный";
            case ELECTRIC:
                return "Электрический";
            default:
                return "Неизвестен";
        }
    }

    public String getWheelDriveType() {
        switch (wd) {
            case FWD:
                return "Передний";
            case RWD:
                return "Задний";
            case AWD:
                return "Полный";
            default:
                return "Неизвестен";
        }
    }

    public String getMaterialType() {
        switch (carBody.getMaterial()) {
            case ALUMINUM:
                return "Алюминий";
            case STEEL:
                return "Сталь";
            case CARBON_FIBER:
                return "Углеволокно";
            default:
                return "Неизвестен";
        }
    }

    public void displayCarInfo() {
        System.out.println("Имя: " + custData.getName());
        System.out.println("Фамилия: " + custData.getSurname());
        System.out.println("Телефонный номер: " + custData.getPhoneNumber());
        System.out.println("Марка автомобиля: " + carData.getCarBrand());
        System.out.println("Год выпуска автомобиля: " + carData.getYearOfRelease());
        System.out.println("Госномер автомобиля: " + carData.getStateNumber());
        System.out.println("Тип двигателя: " + getEngineType());
        System.out.println("Тип привода: " + getWheelDriveType());
        System.out.println("Цвет кузова: " + carBody.getColor());
        System.out.println("Материал кузова: " + getMaterialType());
    }
}