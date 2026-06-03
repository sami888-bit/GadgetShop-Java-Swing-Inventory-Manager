public class MP3 extends Gadget {
    private int availableMemory; // in MB or GB

    // Constructor taking 5 parameters
    public MP3(String model, double price, int weight, String size, int availableMemory) {
        super(model, price, weight, size); // Passing 4 parameters to Gadget
        this.availableMemory = availableMemory;
    }

    // Accessor Method
    public int getAvailableMemory() {
        return availableMemory;
    }

    // Method to download music
    public void downloadMusic(int memoryRequired) {
        if (availableMemory >= memoryRequired) {
            availableMemory -= memoryRequired;
            System.out.println("Download successful! Used " + memoryRequired + " units of memory.");
        } else {
            System.out.println("Error: Insufficient memory available to download music.");
        }
    }

    // Method to delete music
    public void deleteMusic(int memoryFreed) {
        availableMemory += memoryFreed;
        System.out.println("Music deleted. Freed " + memoryFreed + " units of memory.");
    }

    // Overriding display method
    @Override
    public void display() {
        super.display(); // Calls Gadget's display
        System.out.println("Available Memory: " + availableMemory + " units");
    }
}