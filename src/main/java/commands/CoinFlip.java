package commands;

import com.reachyourdream.reebot.ReebotSpringApplication;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CoinFlip extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        User author = event.getMessage().getAuthor();

        if(author.isBot())
            return;

        if(args[0].equals(ReebotSpringApplication.prefix + "coinflip")){
            Random rand = new Random();
            int randomNumber = rand.nextInt(2);
            if(randomNumber==1){
                event.getChannel().sendMessage("Head").queue();;
            } else{ ;
                event.getChannel().sendMessage("Tail").queue();
            }
        }
    }
}
