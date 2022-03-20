package com.example.journey_server.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class redisUtil {

    private static String ip = null;

    private static String port = null;

    private static Jedis jedis = null;

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

    public void aaa() throws IOException {
        Properties pro = new Properties();
        FileInputStream in = new FileInputStream("src/main/resources/redis.properties");
        pro.load(in);
        System.out.println(pro.get("ip"));
        in.close();
    }

    public static void main(String[] args) throws IOException {
        redisUtil redisUtil = new redisUtil();
        redisUtil.aaa();
    }
}
