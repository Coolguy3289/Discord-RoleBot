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

    public static void main(String[] arguments) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        api = new JDABuilder(AccountType.BOT).setToken("<INSERT DISCORD TOKEN HERE>").buildAsync();
        roleSetter = new RoleSetter();
        roleSetter.addRoleToList(297197859353264128L, "<ROLES GO HERE SEPERATED BY COMMA OUTSIDE OF QUOTES>");


        api.addEventListener(this);
        api.getPresence().setGame(Game.of("With Myself"));
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
