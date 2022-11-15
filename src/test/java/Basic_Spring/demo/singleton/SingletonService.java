package Basic_Spring.demo.singleton;

public class SingletonService {
    //1. make only one object in static area.
    private static final SingletonService instance = new SingletonService();

    //2. allow looking up it by using this public static method if someone needs object instance.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. declare constructor as private, so prevent someone from making object by using new keyword
    private SingletonService(){}

    public void logic(){
        System.out.println("call singleton object logic method");
    }
}

