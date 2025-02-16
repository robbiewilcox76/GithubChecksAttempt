public class Main {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }

    public void test() {
        System.out.println("testing");d
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.greet("Jenkins"));
    }
}