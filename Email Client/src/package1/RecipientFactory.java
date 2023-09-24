package package1;

class RecipientFactory {
    public Recipient makeRecipient(String[] details) {
        try {
            switch (details[0].toLowerCase()) {
                case "official":
                    return new Official(details[1], details[2], details[3]);
                case "office_friend":
                    return new Office_friend(details[1], details[2], details[3], details[4]);
                case "personal":
                    return new Personal(details[1], details[2], details[3], details[4]);
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Error: Some fields are missing!");
        }
        return null;
    }
}