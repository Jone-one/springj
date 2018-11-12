package test;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class redistest {
    public static void main(String[] args) {
       List<String > list = new ArrayList<>();
       list.add("彭钰钦");
       list.add("刘怡");
       list.remove("彭钰钦");
        System.out.println(list.toString());
    }
}
