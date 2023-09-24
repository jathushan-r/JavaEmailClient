package package1;

class Official extends Recipient {
    private final String designation;
    public Official(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }
    @Override
    public String getDetails() {
        return "Official: " + this.getName() + "," + this.getEmail() + "," + this.getDesignation();
    }
    public String getDesignation() {
        return designation;
    }
}