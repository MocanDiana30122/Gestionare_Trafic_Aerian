public class Aircraft {
    private String id;
    private String type;
    private int altitude;

    public Aircraft(String id, String type) {
        this.id = id;
        this.type = type;
        this.altitude = 0; // initial altitude
    }

    public String getId() {
        return id;
    }

    public void receiveCommand(AtcCommand command) {
        if (command.getAction().equals("takeoff")) {
            // Simulate takeoff and rapid altitude increase
            new Thread(() -> {
                for (int i = 0; i <= command.getAltitude(); i += 100) { // increase by 100 ft each time
                    altitude = i;
                    try {
                        Thread.sleep(50); // adjust to speed up or slow down
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else if (command.getAction().equals("land")) {
            // Simulate landing logic
            altitude = 0; // reset altitude to 0 on landing
        }
    }

    @Override
    public String toString() {
        return "Aircraft ID: " + id + ", Type: " + type + ", Current Altitude: " + altitude;
    }
}
