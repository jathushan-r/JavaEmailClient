package package1;

class Office_friend extends Official implements BDWishable {
    private final String dateOfBirth;
    public Office_friend(String name, String email, String designation, String dateOfBirth) {
        super(name, email, designation);
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String getDetails() {
        return "Official_friend: " + this.getName() + "," + this.getEmail() + "," +
                this.getDesignation() + "," + this.getDateOfBirth();
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    @Override
    public String getWish() {
        return "Wish you a Happy Birthday. Jathushan";
    }
    @Override
    public boolean isBirthday(String date) {
        String[] dob_arr = getDateOfBirth().split("/");
        String[] date_arr = date.split("/");
        if (date_arr[0].compareTo(dob_arr[0]) > 0) {
            return dob_arr[1].equals(date_arr[1]) && dob_arr[2].equals(date_arr[2]);
        }
        return false;
    }
}
