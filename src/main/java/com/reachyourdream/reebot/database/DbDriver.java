package com.reachyourdream.reebot.database;

import com.reachyourdream.reebot.Config;

import java.util.HashMap;

public class DbDriver {

    private static final String DEFAULT_CONNECTION = "discord";
    private static HashMap<String, PostgreAdapter> connections = new HashMap<>();

    public static PostgreAdapter get(String key){
        if (connections.containsKey(key)){
            return connections.get(key);
        }
        System.out.printf("The PostgreSQL connection '%s' is not set", key);
        return null;
    }

    public static PostgreAdapter get(){
        return connections.get(DEFAULT_CONNECTION);
    }

    public static void init(){
        connections.clear();
        connections.put("discord", new PostgreAdapter(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD));
    }
}
