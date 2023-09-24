package package1;

abstract class Recipient {
    private static int count = 0;
    private final String name;
    private final String email;
    public Recipient(String name, String email) {
        this.name = name;
        this.email = email;
        count++;
    }
    public abstract String getDetails();
    public static int getCount() {
        return count;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}