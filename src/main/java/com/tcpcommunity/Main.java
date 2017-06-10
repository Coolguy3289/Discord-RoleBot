package com.tcpcommunity;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


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

       if(content.startsWith("+") && content.length() > 1) {
           String role = content.substring(1);
           roleSetter.run(message, role);
       }

       if(content.startsWith("-") && content.length() > 1) {
           String role = content.substring(1);
           roleSetter.run(message, role, true);
       }

    }
}
