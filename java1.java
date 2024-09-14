public class HotDrink {
    private String name; // Название напитка
    private double volume; // Объем напитка
    
    public HotDrink(String name, double volume) {
        this.name = name;
        this.volume = volume;
    }
}

import java.util.HashMap;
import java.util.Map;

public interface TradeMachine {
    Map<Integer, Product> products = new HashMap<>();

    void addProduct(int id, Product product);
    Product getProduct(int id);
    boolean hasProduct(int id);
}

import java.util.HashMap;
import java.util.Map;

public class HotBeverageMachine implements TradeMachine {
    @Override
    public void addProduct(int id, Product product) {
        if (!products.containsKey(id)) {
            products.put(id, product);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " already exists");
        }
    }

    @Override
    public Product getProduct(int id) {
        return products.get(id);
    }

    @Override
    public boolean hasProduct(int id) {
        return products.containsKey(id);
    }
}
 
public class HotBevMachine extends HotBeverageMachine {
    private int temperature;

    public HotBevMachine(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}

public HotDrink getProduct(int name, int volume, int temperature) {
    Product product = super.getProduct(name);
    if (product instanceof HotDrink hotDrink && hotDrink.getVolume() == volume && hotDrink.getTemperature() == temperature) {
        return hotDrink;
    } else {
        throw new IllegalArgumentException("Invalid product or temperature");
    }
}

public static void main(String[] args) {
    HotDrink tea = new HotDrink("Tea", 0.25);
    HotDrink coffee = new HotDrink("Coffee", 0.35);
    HotDrink milk = new HotDrink("Milk", 0.15);
    
    HotBeverageMachine machine = new HotBeverageMachine();
    machine.addProduct(1, tea);
    machine.addProduct(2, coffee);
    machine.addProduct(3, milk);
    
    System.out.println("Tea volume: " + tea.getVolume());
    System.out.println("Coffee volume: " + coffee.getVolume());
    System.out.println("Milk volume: " + milk.getVolume());
    
    try {
        HotDrink result = machine.getProduct(1, 0.25, 80);
        System.out.println("Got Tea with volume: " + result.getVolume() + ", at temperature: " + result.getTemperature());
    } catch (IllegalArgumentException e) {
        System.err.println("Error getting product: " + e.getMessage());
    }
}

