package com.example.journey_server.Service;

import com.example.journey_server.entity.Peer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    private static Map<String, Peer> users = new HashMap<>();

    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    public void addUser(Peer user) {
        if (users.containsKey(user.getEmail())) {
            return;
        }
        users.put(user.getEmail(), user);
    }

    public double[] geo2xyz(double lon, double lat) {
        double thera = (Math.PI * lat) / 180;
        double fie = (Math.PI * lon) / 180;
        double x = EARTH_RADIUS * Math.cos(thera) * Math.cos(fie);
        double y = EARTH_RADIUS * Math.cos(thera) * Math.sin(fie);
        double z = EARTH_RADIUS * Math.sin(thera);
        double[] point = new double[3];
        point[0] = x;
        point[1] = y;
        point[2] = z;
        return point;
    }

    /**
     * @param lon1
     * @param lat1
     * @param lon2 start point
     * @param lat2 start point
     * @param lon3
     * @param lat3
     * @return
     */
    public double getAngel(double lon1, double lat1, double lon2, double lat2, double lon3, double lat3) {

        double[] p1 = geo2xyz(lon1, lat1);
        double[] p2 = geo2xyz(lon2, lat2);
        double[] p3 = geo2xyz(lon3, lat3);
        double P1P2 = Math.sqrt((p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[2] - p1[2]) * (p2[2] - p1[2]));
        double P2P3 = Math.sqrt((p3[0] - p2[0]) * (p3[0] - p2[0]) + (p3[1] - p2[1]) * (p3[1] - p2[1]) + (p3[2] - p2[2]) * (p3[2] - p2[2]));
        double P = (p1[0] - p2[0]) * (p3[0] - p2[0]) + (p1[1] - p2[1]) * (p3[1] - p2[1]) + (p1[2] - p2[2]) * (p3[2] - p2[2]);
        return (Math.acos(P / (P1P2 * P2P3)) / Math.PI) * 180;

    }

    public List<Peer> getMatch(Peer user) {
        if (user.getLimit() == null || user.getLimit() == 0) {
            user.setLimit(5);
        }
        addUser(user);
        List<Peer> result = new ArrayList<>();
        for (Map.Entry<String, Peer> entry : users.entrySet()) {
            Peer userM = entry.getValue();
            double distance = getDistance(user.getLongitude(), user.getLatitude(), userM.getLongitude(), userM.getLatitude());
            if (distance < 500 && getAngel(user.getdLongtitude(), user.getdLatitude(), user.getLongitude(), user.getLongitude(),
                    userM.getdLongtitude(), userM.getdLatitude()) < 45) {
                result.add(userM);
            }
            if (result.size() >= user.getLimit()) {
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MatchService matchService = new MatchService();
        System.out.print(matchService.getAngel(53.342186, -6.254613, 53.341226, -6.250776, 53.342164, -6.251525));
    }
}
