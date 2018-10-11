package test;

import redis.clients.jedis.Jedis;

public class redistest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("运行:"+jedis.ping());
        jedis.set("name","liuyi");
        System.out.println("储存的名字："+jedis.get("name"));
    }
}
