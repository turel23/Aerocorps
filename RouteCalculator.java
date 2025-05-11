public class RouteCalculator {
    private static final double EARTH_RADIUS_NM = 3440.065; // Earth's radius in nautical miles

    public static double calculateDistanceNM(Airport origin, Airport destination) {
        double lat1 = Math.toRadians(origin.getLatitude());
        double lon1 = Math.toRadians(origin.getLongitude());
        double lat2 = Math.toRadians(destination.getLatitude());
        double lon2 = Math.toRadians(destination.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.round(EARTH_RADIUS_NM * c*10.0)/10.0; // Distance in nautical miles
    }
}
