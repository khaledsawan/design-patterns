import java.util.ArrayList;
import java.util.List;

// Singleton: Ensures a single instance of the class
class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Factory Method: Creates objects based on a common interface
interface Product {
    void create();
}

class ConcreteProductA implements Product {
    @Override
    public void create() {
        System.out.println("Created Product A");
    }
}

class ConcreteProductB implements Product {
    @Override
    public void create() {
        System.out.println("Created Product B");
    }
}

interface Factory {
    Product createProduct();
}

class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

// Adapter: Allows objects with incompatible interfaces to work together
class LegacySystem {
    void doLegacyStuff() {
        System.out.println("Legacy system doing stuff.");
    }
}

class AdapterLegacySystem implements Product {
    private LegacySystem legacySystem;

    AdapterLegacySystem(LegacySystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void create() {
        legacySystem.doLegacyStuff();
    }
}

// Composite: Represents part-whole hierarchies
abstract class Component {
    abstract void operation();
}

class Leaf extends Component {
    @Override
    void operation() {
        System.out.println("Leaf does its operation.");
    }
}

class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    void add(Component component) {
        children.add(component);
    }

    @Override
    void operation() {
        System.out.println("Composite does its operation, and its children do theirs:");
        for (Component child : children) {
            child.operation();
        }
    }
}

// Observer: Defines a one-to-many dependency between objects
interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private String name;

    ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

public class CombinedExample {
    public static void main(String[] args) {
        // Singleton Example
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();

        // Factory Method Example
        Factory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        productA.create();

        Factory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        productB.create();

        // Adapter Example
        LegacySystem legacySystem = new LegacySystem();
        Product adaptedSystem = new AdapterLegacySystem(legacySystem);
        adaptedSystem.create();

        // Composite Example
        Component leaf = new Leaf();
        Component composite = new Composite();
        composite.add(leaf);
        composite.operation();

        // Observer Example
        Subject subject = new Subject();
        Observer observer1 = new ConcreteObserver("Observer 1");
        Observer observer2 = new ConcreteObserver("Observer 2");

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.notifyObservers("Hello Observers!");
    }
}
