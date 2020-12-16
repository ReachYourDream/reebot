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
        MessageChannel messageChannel = event.getChannel();

        if(author.isBot())
            return;

        if(messageContent.equals("!coinflip")){
            Random rand = new Random();
            int randomNumber = rand.nextInt(2);
            if(randomNumber==1){
                messageChannel.sendMessage("Head").queue();
            } else{
                messageChannel.sendMessage("Tail").queue();
            }
        }
        if(messageContent.length()>5){
            if(messageContent.substring(0,5).equals("!pick")){
                if(messageContent.charAt(6)=='[' && messageContent.charAt(messageContent.length()-1)== ']'){
                    String optionsString = messageContent.substring(6);
                    String optionsStringNoBrackets = optionsString.replace("[", "")
                            .replace("]","");
                    String options[] = optionsStringNoBrackets.split(",");
                    if(options.length<2){
                        String  errorMessage = "Pilihan minimal 2";
                        messageChannel.sendMessage(errorMessage).queue();
                    } else{
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(options.length);
                        messageChannel.sendMessage(options[randomNumber]).queue();
                    }
                } else{
                    String errorMessage = "Template salah.\n Template yang benar:\n " +
                            "```!pick [opsi1, opsi2, opsi3, opsi...]```";
                    messageChannel.sendMessage(errorMessage).queue();
                }
            }
        }

    }
}
