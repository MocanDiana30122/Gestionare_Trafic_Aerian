public class AtcCommand {
    private String action;
    private int altitude;

    public AtcCommand(String action, int altitude) {
        this.action = action;
        this.altitude = altitude;
    }

    public String getAction() {
        return action;
    }

    public int getAltitude() {
        return altitude;
    }
}
