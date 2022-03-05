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


    public List<Peer> getMatch(Peer user) {
//        if (limit == null || limit == 0) {
//            limit = 5;
//        }
        addUser(user);
        List<Peer> result = new ArrayList<>();
        for (Map.Entry<String, Peer> entry : users.entrySet()) {
            Peer userM = entry.getValue();
//            double distance = getDistance(user.getLongitude(), user.getLatitude(), userM.getLongitude(), userM.getLatitude());
//            if (distance < 500) {
//                result.add(userM);
//            }
//            if (result.size() >= limit) {
//                return result;
//            }
        }
        return result;
    }
}
