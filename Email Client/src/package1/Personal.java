package package1;

class Personal extends Recipient implements BDWishable {
    private final String dateOfBirth;
    private String nickName;
    public Personal(String name, String nickNme, String email, String dateOfBirth) {
        super(name, email);
        this.nickName = nickNme;
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String getDetails() {
        return "Personal: " + this.getName() + "," + this.getNickName() +
                "," + this.getEmail() + "," + this.getDateOfBirth();
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getNickName() {
        return nickName;
    }
    @Override
    public String getWish() {
        return "hugs and love on your birthday. Jathushan";
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

