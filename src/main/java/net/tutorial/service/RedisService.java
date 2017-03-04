package net.tutorial.service;

import net.tutorial.service.credential.RedisCredential;
import redis.clients.jedis.Jedis;

/**
 * Created by pongpantola.
 */
public class RedisService {

    private Jedis jedis;

    public RedisService(){
        RedisCredential redCred = new RedisCredential();
        jedis = new Jedis(redCred.hostname, Integer.parseInt(redCred.port));
        jedis.auth(redCred.password);
    }

    public void set(String key, String val) {
        jedis.set(key, val);
    }

    public String get(String key) {
        return jedis.get(key);
    }
}
