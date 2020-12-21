package commands;

import com.reachyourdream.reebot.ReebotSpringApplication;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MuteRoles extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        User author = event.getMessage().getAuthor();

        if(author.isBot())
            return;

        if(args[0].equals(ReebotSpringApplication.prefix + "mute")){
            if(args.length>1 && args.length<3){
                String userId = args[1].replace("<@!", "").replace(">", "");
                System.out.println();
                event.getGuild()
                        .addRoleToMember(userId, event.getGuild().getRolesByName("Muted", true).get(0))
                        .complete();
                event.getMessage().getChannel().sendMessage("Berhasil mute " + args[1]).queue();
            } else{
                String errorMessage = "Format salah, format yang benar: " + ReebotSpringApplication.prefix
                        + "mute [username]";
                event.getMessage().getChannel().sendMessage(errorMessage).queue();
            }
        } else if(args[0].equals(ReebotSpringApplication.prefix + "unmute")){
            if(args.length>1 && args.length<3){
                String userId = args[1].replace("<@!", "").replace(">", "");
                event.getGuild()
                        .removeRoleFromMember(userId, event.getGuild().getRolesByName("Muted", true).get(0))
                        .complete();
                event.getMessage().getChannel().sendMessage("Berhasil unmute " + args[1]).queue();
            } else{
                String errorMessage = "Format salah, format yang benar: " + ReebotSpringApplication.prefix
                        + "unmute [username]";
                event.getMessage().getChannel().sendMessage(errorMessage).queue();
            }
        }
    }
}
