public class Weather {
    private static boolean stormy;

    public static void generateWeather() {
        stormy = Math.random() < 0.3; // 30% chance of stormy weather
    }

    public static boolean isStormy() {
        return stormy;
    }
}
