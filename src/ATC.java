import java.util.ArrayList;

public class ATC {
    private ArrayList<Aircraft> aircraftList;

    public ATC() {
        this.aircraftList = new ArrayList<>();
    }

    public void addAircraft(String id, String type) {
        Aircraft aircraft = new Aircraft(id, type);
        aircraftList.add(aircraft);
    }

    public void sendCommand(String id, AtcCommand command) {
        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId().equals(id)) {
                aircraft.receiveCommand(command);
                break;
            }
        }
    }

    public ArrayList<String> getAircrafts() {
        ArrayList<String> result = new ArrayList<>();
        for (Aircraft aircraft : aircraftList) {
            result.add(aircraft.toString());
        }
        return result;
    }
}
