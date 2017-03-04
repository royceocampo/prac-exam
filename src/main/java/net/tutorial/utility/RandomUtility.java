package net.tutorial.utility;

import java.util.Random;

/**
 * Created by pongpantola.
 */
public class RandomUtility{

    private static Random rand;

    static{
        rand = new Random();
    }

    public static long getRandomLong(){
        return rand.nextLong();

    }

    public static String getRandomTimestamp(){
        String str = ""+DateTimeUtility.getTimestamp().getTime();
        long nanos = getRandomLong() % 1000000000l;

        return str + nanos;
    }


}
