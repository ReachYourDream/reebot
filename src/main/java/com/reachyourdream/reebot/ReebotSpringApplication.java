package com.reachyourdream.reebot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.logging.log4j.util.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReebotSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReebotSpringApplication.class, args);
        InitBot();
    }

    private static void InitBot(){
        String token = System.getenv("BOT_TOKEN");
        try{
            JDABuilder.createLight(token)
                    .addEventListeners(new CoinFlipListener())
                    .setActivity(Activity.listening("to your commands :)"))
                    .setStatus(OnlineStatus.ONLINE)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
