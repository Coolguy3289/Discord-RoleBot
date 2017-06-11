package com.tcpcommunity;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;


public class Main extends ListenerAdapter {

    private static JDA api;
    private RoleSetter roleSetter;
    private static long guildID = 0L; //Put your guild ID here, MAKE SURE THE L IS AT THE END.
    private static String discordToken = ""; // Put your GuildID in the quotes.

    public static void main(String[] arguments) throws Exception {
        new Main();
    }

    private Main() throws Exception {
        //Add your Discord App Token below in the quotes.
        api = new JDABuilder(AccountType.BOT).setToken(discordToken).buildAsync();
        roleSetter = new RoleSetter();
        roleSetter.addRoleToList(guildID, "ROLES TO BE ADDED. PUT ROLE IN QUOTES");


        api.addEventListener(this);
        api.getPresence().setGame(Game.of("With Roles"));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        Message message = event.getMessage();
        String content = message.getRawContent();

       if(content.startsWith("r+") && content.length() > 2) {
           String role = content.substring(2);
           roleSetter.run(message, role);
       }

       if(content.startsWith("r-") && content.length() > 2) {
           String role = content.substring(2);
           roleSetter.run(message, role, true);
       }
        if(content.equals("rhelp")) {
            MessageChannel channel = event.getChannel();
            Guild guild = message.getGuild();
            EmbedBuilder rolehelp = new EmbedBuilder();

            rolehelp.setTitle("List of Roles and How to add/remove them!");
            rolehelp.setDescription("To add a role, type r+ and whichever role you want listed below. To remove roles, type r- and the role you want to remove from the list below.");
            rolehelp.addField("List of Roles", "``", false);
            rolehelp.addField("Example Usage", "r+rpg <-- This will add the `RPG` role to you.\nr-rpg <-- This will remove the `RPG` role from you.", true);
            rolehelp.setColor(new Color(34, 139, 34));
            channel.sendMessage(rolehelp.build()).queue();

            if(message != null && guild.getMember(message.getJDA().getSelfUser()).hasPermission(message.getTextChannel(), Permission.MESSAGE_MANAGE)) {
                System.out.println("Deleting message: " + message.getStrippedContent());
                message.delete().queue();
            } else {
                System.out.println("Message already deleted, Ignoring.");
            }

        }

    }

}
