package com.example.journey_server.utils;

import com.example.journey_server.entity.Peer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class redisUtil {

    private static String ip = null;

    private static String port = null;

    private static Jedis jedis = null;

    private static serializeUtil serializeUtil = new serializeUtil();

    static {
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/redis.properties");
            pro.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ip = pro.getProperty("ip");
        port = pro.getProperty("port");
        jedis = new Jedis(ip, Integer.parseInt(port));

    }


    public void addUser(Peer user) {
        byte[] bytes = jedis.get("users".getBytes());

        Map<String, Peer> users = null;
        if (null == bytes) {
            users = new HashMap<>();
        } else {
            Map<String, Peer> userss = (Map<String, Peer>) serializeUtil.unserizlize(bytes);
            users = userss;
            if (users.containsKey(user.getEmail())) {
                return;
            }
        }

        users.put(user.getEmail(), user);
        jedis.set("users".getBytes(), serializeUtil.serialize(users));
    }

    public static void main(String[] args) throws IOException {
        redisUtil redisUtil = new redisUtil();
        jedis.flushDB();
        Peer peer1 = new Peer("11@qq.com", "male", 23, 4.4, 53.33, -6.32, 53.3, -6.51, 0L, 0L, 5, false);
        redisUtil.addUser(peer1);
        Map<String, Peer> users = (Map<String, Peer>) serializeUtil.unserizlize(jedis.get("users".getBytes()));
        System.out.println(users.get("11@qq.com"));

    }
}
