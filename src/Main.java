import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

class DatabaseResult {
    private int resultCode;

    public DatabaseResult(int code) {
        resultCode = code;
    }

    public int getResultCode() {
        return resultCode;
    }
}

class CarDatabaseManager {
    private List<CarDatabase> carDatabases;

    public CarDatabaseManager() {
        carDatabases = new ArrayList<>();
    }

    // Добавление базы данных автомобилей
    public void addCarDatabase(CarDatabase carDatabase) {
        carDatabases.add(carDatabase);
    }

    // Доступ к базе данных по индексу
    public CarDatabase getCarDatabase(int index) {
        return carDatabases.get(index);
    }

    // Доступ к общему количеству баз данных
    public int getTotalCarDatabases() {
        return carDatabases.size();
    }
}

class CarDatabase {
    private static int totalCarDatabases = 0;

    private int numCars = 0;
    private ArrayList<Car> cars = new ArrayList<>();

    public CarDatabase() {
        totalCarDatabases++;
    }

    public DatabaseResult input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создание записи об автомобиле.\n");

        try {
            System.out.print("Введите имя: ");
            String name = scanner.next();
            System.out.print("Введите фамилию: ");
            String surname = scanner.next();
            System.out.print("Введите телефонный номер: ");
            String phoneNumber = scanner.next();
            CustomerData customer = new CustomerData(name, surname, phoneNumber);

            System.out.print("Введите марку автомобиля: ");
            String carBrand = scanner.next();
            System.out.print("Введите год выпуска автомобиля: ");
            String yearOfRelease = scanner.next();
            System.out.print("Введите госномер автомобиля: ");
            String stateNumber = scanner.next();
            CarData car = new CarData(carBrand, yearOfRelease, stateNumber);

            System.out.print("Выберите тип двигателя (1. бензиновый; 2. дизельный; 3. электрический): ");
            Engine eng = Engine.UNKNOWN_ENGINE;
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    eng = Engine.PETROL;
                    break;
                case 2:
                    eng = Engine.DIESEL;
                    break;
                case 3:
                    eng = Engine.ELECTRIC;
                    break;
            }

            System.out.print("Выберите тип привода (1. передний; 2. задний; 3. полный): ");
            WheelDrive drive = WheelDrive.UNKNOWN_WD;
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    drive = WheelDrive.FWD;
                    break;
                case 2:
                    drive = WheelDrive.RWD;
                    break;
                case 3:
                    drive = WheelDrive.AWD;
                    break;
            }

            System.out.print("Введите цвет кузова: ");
            String color = scanner.next();

            System.out.print("Выберите материал кузова (1. алюминий; 2. сталь; 3. углеволокно): ");
            Material mat = Material.UNKNOWN_MATERIAL;
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    mat = Material.ALUMINUM;
                    break;
                case 2:
                    mat = Material.STEEL;
                    break;
                case 3:
                    mat = Material.CARBON_FIBER;
                    break;
            }

            CarBody body = new CarBody(color, mat);

            if (numCars < Integer.MAX_VALUE) {
                Car carObj = new Car(customer, car, eng, drive, body);
                cars.add(carObj);
                numCars++;
                System.out.println("Запись создана.\n");
                return new DatabaseResult(0); // Успешное создание записи
            } else {
                System.out.println("Достигнуто максимальное количество записей!\n");
                return new DatabaseResult(1); // Превышено максимальное количество записей
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при создании записи: " + e.getMessage());
            return new DatabaseResult(3); // Код ошибки для общей ошибки
        }
    }

    public void output() {
        if (numCars > 0) {
            System.out.println("Вывод всех записей.\n");
            for (int counter = 0; counter < numCars; counter++) {
                System.out.println("Заказ №" + (counter + 1));
                cars.get(counter).displayCarInfo();
                System.out.println("-----------------------------------\n");
            }
        } else {
            System.out.println("Записи не найдены!\n");
        }
    }

    public void sortByYearOfRelease() {
        Collections.sort(cars, (car1, car2) ->
                car1.getCarData().getYearOfRelease().compareTo(car2.getCarData().getYearOfRelease()));
    }

    public Car searchCarByBrand(String brand) {
        for (Car car : cars) {
            if (car.getCarData().getCarBrand().equalsIgnoreCase(brand)) {
                return car;
            }
        }
        return null; // Автомобиль не найден
    }

    public static int getTotalCarDatabases() {
        return totalCarDatabases;
    }

    public static void main(String[] args) {
        CarDatabase carDb = new CarDatabase();
        CarDatabase[] carDatabases = new CarDatabase[5]; // Пример массива объектов

        int quit = 0;
        int option;
        Scanner scanner = new Scanner(System.in);

        while (quit == 0) {
            System.out.println("Выберите действие:");
            System.out.println(" 1. Создать запись об автомобиле");
            System.out.println(" 2. Редактировать конкретную запись");
            System.out.println(" 3. Вывести все существующие записи");
            System.out.println(" 4. Вывести конкретную запись");
            System.out.println(" 5. Удалить конкретную запись");
            System.out.println(" 6. Отсортировать по году выпуска");
            System.out.println(" 7. Поиск по марке");
            System.out.println(" 0. Выйти");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        DatabaseResult result = carDb.input();
                        if (result.getResultCode() == 1) {
                            System.out.println("Ошибка: Превышено максимальное количество записей!\n");
                        } else if (result.getResultCode() == 2) {
                            System.out.println("Ошибка: Введены некорректные данные!\n");
                        } else if (result.getResultCode() == 3) {
                            System.out.println("Ошибка: Произошла ошибка при создании записи!\n");
                        }
                        break;
                    case 2:
                        System.out.println("Редактирование находится в разработке!\n");
                        break;
                    case 3:
                        carDb.output();
                        break;
                    case 4:
                        System.out.println("Вывод конкретной записи находится в разработке!\n");
                        break;
                    case 5:
                        System.out.println("Удаление находится в разработке!\n");
                        break;
                    case 6:
                        carDb.sortByYearOfRelease();
                        System.out.println("Список автомобилей отсортирован по году выпуска.");
                        break;
                    case 7:
                        System.out.print("Введите марку автомобиля для поиска: ");
                        String brandToSearch = scanner.next();
                        Car foundCar = carDb.searchCarByBrand(brandToSearch);
                        if (foundCar != null) {
                            System.out.println("Найден автомобиль:");
                            foundCar.displayCarInfo();
                        } else {
                            System.out.println("Автомобиль с маркой " + brandToSearch + " не найден.");
                        }
                        break;
                    case 0:
                        System.out.println("Выход.\n");
                        quit = 1;
                        break;
                    default:
                        System.out.println("Некорректный ввод. Попробуйте еще раз\n");
                        break;
                }
            } else {
                scanner.next();
                System.out.println("Некорректный ввод. Попробуйте еще раз\n");
            }
        }
    }
}
