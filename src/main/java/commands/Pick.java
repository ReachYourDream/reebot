package commands;

import com.reachyourdream.reebot.ReebotSpringApplication;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Pick extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        User author = event.getMessage().getAuthor();

        if(author.isBot())
            return;

        if(args[0].equals(ReebotSpringApplication.prefix + "pick")){
            if(args.length < 2){ //handle commands formatting
                String errorMessage = "Template salah.\n Template yang benar:\n " +
                        "```" + ReebotSpringApplication.prefix + "pick opsi1 opsi2 opsi3 opsi...```";
                event.getMessage().getChannel().sendMessage(errorMessage).queue();
            } else {
                if(args.length < 3){
                    String  errorMessage = "Pilihan minimal 2";
                    event.getMessage().getChannel().sendMessage(errorMessage).queue();
                } else {
                    Random rand = new Random();
                    int randomNumber = rand.nextInt(args.length - 1);
                    event.getMessage().getChannel().sendMessage(args[randomNumber + 1]).queue();
                }
            }
        }
    }
}
