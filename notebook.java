import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NotebookShop {

    public static void main(String[] args) {
        // Создаем список ноутбуков
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Acer", 8, 512, "Windows 10", "Silver"));
        notebooks.add(new Notebook("Lenovo", 16, 1024, "macOS", "Space Gray"));
        notebooks.add(new Notebook("Dell", 12, 256, "Windows 11", "Black"));
        notebooks.add(new Notebook("HP", 8, 512, "Chrome OS", "White"));

        // Запрашиваем критерии фильтрации у пользователя
        Map<String, Integer> filterCriteria = getFilterCriteriaFromUser();

        // Фильтруем ноутбуки
        List<Notebook> filteredNotebooks = filterNotebooks(notebooks, filterCriteria);

        // Выводим отфильтрованные ноутбуки
        System.out.println("Отфильтрованные ноутбуки:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }

    // Метод для получения критериев фильтрации от пользователя
    private static Map<String, Integer> getFilterCriteriaFromUser() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> filterCriteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Считываем оставшуюся часть строки

        System.out.print("Введите минимальное значение: ");
        int minValue = scanner.nextInt();

        switch (choice) {
            case 1:
                filterCriteria.put("RAM", minValue);
                break;
            case 2:
                filterCriteria.put("Storage", minValue);
                break;
            case 3:
                filterCriteria.put("OS", minValue); // Предполагаем, что OS будет представлена числовым кодом
                break;
            case 4:
                filterCriteria.put("Color", minValue); // Предполагаем, что Color будет представлен числовым кодом
                break;
            default:
                System.out.println("Неверный выбор.");
        }

        return filterCriteria;
    }

    // Метод для фильтрации ноутбуков
    private static List<Notebook> filterNotebooks(List<Notebook> notebooks, Map<String, Integer> filterCriteria) {
        List<Notebook> filteredNotebooks = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            boolean matchesCriteria = true;
            for (Map.Entry<String, Integer> entry : filterCriteria.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                switch (key) {
                    case "RAM":
                        if (notebook.getRam() < value) {
                            matchesCriteria = false;
                        }
                        break;
                    case "Storage":
                        if (notebook.getStorage() < value) {
                            matchesCriteria = false;
                        }
                        break;
                    case "OS":
                        // Сравнение OS по числовому коду
                        if (notebook.getOsCode() != value) {
                            matchesCriteria = false;
                        }
                        break;
                    case "Color":
                        // Сравнение Color по числовому коду
                        if (notebook.getColorCode() != value) {
                            matchesCriteria = false;
                        }
                        break;
                }
                if (!matchesCriteria) {
                    break;
                }
            }
            if (matchesCriteria) {
                filteredNotebooks.add(notebook);
            }
        }
        return filteredNotebooks;
    }
}

class Notebook {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;

    // Конструктор, геттеры и сеттеры

    public Notebook(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // ... геттеры и сеттеры для всех полей

    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    // Методы для получения числовых кодов OS и Color (если необходимо)
    public int getOsCode() {
        // Реализация получения числового кода OS
        return 0;
    }

    public int getColorCode() {
        // Реализация получения числового кода Color
        return 0;
    }
}
