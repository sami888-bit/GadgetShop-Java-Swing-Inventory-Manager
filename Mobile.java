public class Mobile extends Gadget {
    private int creditRemaining; // in minutes

    // Constructor taking 5 parameters
    public Mobile(String model, double price, int weight, String size, int creditRemaining) {
        super(model, price, weight, size); // Passing 4 parameters to Gadget
        this.creditRemaining = creditRemaining;
    }

    // Accessor Method
    public int getCreditRemaining() {
        return creditRemaining;
    }

    // Method to add calling credit
    public void addCredit(int amount) {
        if (amount > 0) {
            creditRemaining += amount;
            System.out.println("Successfully added " + amount + " minutes of credit.");
        } else {
            System.out.println("Error: Please enter a positive amount of credit.");
        }
    }

    // Method to make a phone call
    public void makeCall(String phoneNumber, int duration) {
        if (creditRemaining >= duration) {
            System.out.println("Making call to: " + phoneNumber + " | Duration: " + duration + " minutes.");
            creditRemaining -= duration;
        } else {
            System.out.println("Error: Insufficient credit to make this call.");
        }
    }

    // Overriding display method
    @Override
    public void display() {
        super.display(); // Calls Gadget's display
        System.out.println("Calling Credit Remaining: " + creditRemaining + " minutes");
    }
}