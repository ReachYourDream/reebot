package com.reachyourdream.reebot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CoinFlipListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        User author = message.getAuthor();
        String messageContent = message.getContentDisplay();
        if(author.isBot())
            return;

        if(messageContent.equals("!coinflip")){
            MessageChannel messageChannel = event.getChannel();
            Random rand = new Random();
            int randomNumber = rand.nextInt(2);
            if(randomNumber==1){
                messageChannel.sendMessage("Head").queue();
            } else{
                messageChannel.sendMessage("Tail").queue();
            }
        }
    }
}
