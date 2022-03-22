package com.example.journey_server.utils;

import com.example.journey_server.entity.Peer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class RedisUtil {

    private static String ip = null;

    private static String port = null;

    private static Jedis jedis = null;

    @Autowired
    private SerializeUtil serializeUtil;

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

    public Map<String, Peer> getUsers() {
        Map<String, Peer> users = new HashMap<>();
        byte[] bytes = jedis.get("users".getBytes());
        if (null == bytes) {
            return users;
        } else {
            users = (Map<String, Peer>) serializeUtil.unserizlize(bytes);
            return users;
        }
    }

    public void removeUser(String email) {
        byte[] bytes = jedis.get("users".getBytes());
        if (null == bytes) {
            return;
        }
        Map<String, Peer> users = (Map<String, Peer>) serializeUtil.unserizlize(bytes);
        if (users.containsKey(email)) {
            users.remove(email);
            jedis.set("users".getBytes(), serializeUtil.serialize(users));
        }
    }

    public void putMatchedUser(String email, List<Peer> peers) {
        byte[] bytes = jedis.get("matchedUser".getBytes());
        Map<String, List<Peer>> matched;
        if (null == bytes) {
            matched = new HashMap<>();
        } else {
            matched = (Map<String, List<Peer>>) serializeUtil.unserizlize(bytes);
        }
        matched.put(email, peers);
        jedis.set("matchedUser".getBytes(), serializeUtil.serialize(matched));
    }

    public Map<String, List<Peer>> getMatchedUser(){
        byte[] bytes = jedis.get("matchedUser".getBytes());
        if(null == bytes){
            return new HashMap<>();
        }else {
            return (Map<String, List<Peer>>) serializeUtil.unserizlize(bytes);
        }
    }
}
