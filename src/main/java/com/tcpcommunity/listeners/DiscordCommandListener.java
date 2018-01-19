package com.tcpcommunity.listeners;

import com.tcpcommunity.Main;
import com.tcpcommunity.command.api.DiscordCommandSender;
import com.tcpcommunity.command.api.GuildCommandSender;
import com.tcpcommunity.command.api.PrivateCommandSender;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class DiscordCommandListener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordCommandListener.class);

    private static final boolean DM_LOGGING = true;
    private static final boolean GUILD_LOGGING = false;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getRawContent();
        List<User> mentions = message.getMentionedUsers();
        JDA jda = message.getJDA();

        if (!message.getAuthor().isBot() && !message.getAuthor().isFake() && content.length() > 0) {
            //LOGGER.debug("Recieved a non-empty non-bot message.");
            if (GUILD_LOGGING && message.isFromType(ChannelType.TEXT)) {
                LOGGER.debug(String.format("[Message][%s>>%s#%s]: %s", message.getGuild().getName(), message.getAuthor().getName(), message.getAuthor().getDiscriminator(), message.getContent()));
            } else if (DM_LOGGING && message.isFromType(ChannelType.PRIVATE)) {
                LOGGER.debug(String.format("[Message][DM>>%s#%s]: %s", message.getAuthor().getName(), message.getAuthor().getDiscriminator(), message.getContent()));
            }

            //LOGGER.debug("Did console logging.");

            DiscordCommandSender sender = null;
            String prefix = null;

            try {

                // Handles mention prefixes
                if (!mentions.isEmpty() && mentions.get(0).equals(jda.getSelfUser()) && content.startsWith(mentions.get(0).getAsMention()))
                    prefix = mentions.get(0).getAsMention() + " ";

                // Generic prefix
                if(prefix == null && content.toLowerCase().startsWith("r"))
                    prefix = String.valueOf(content.toCharArray()[0]);

                // Fallback mention check
                if(prefix == null && content.startsWith("<@317163871737741322>"))
                    prefix = "<@317163871737741322>";

                //LOGGER.debug("Passed prefix parsing.");

                if (message.isFromType(ChannelType.TEXT)) {
                    sender = new GuildCommandSender(Main.getInstance(), message);
                } else if (message.isFromType(ChannelType.PRIVATE)) {
                    sender = new PrivateCommandSender(Main.getInstance(), message);
                } else {
                    //LOGGER.warn("Received message from unsupported currentChannel type { " + message.getChannelType() + " }.");
                }

                //LOGGER.debug("Passed sender parsing.");

                if(sender == null) {
                    //LOGGER.debug("How the fuck did this even happen, there isn't a sender?");
                } else {
                    if(prefix == null) {
                        //LOGGER.debug("No prefix? Could just be a message. " + message.getMentionedUsers());
                    }
                }

                if (prefix != null && sender != null) {
                    //LOGGER.debug("I'm about to run the fucking command.");
                    String[] tokens = content.replaceFirst(prefix, "").split("\\s+");
                    String command = tokens[0];
                    String[] args = tokens.length > 1 ? Arrays.copyOfRange(tokens, 1, tokens.length) : new String[]{};
                    try {
                        //LOGGER.debug("Command passed to CommandBase  " + command + " >> " + args);
                        sender.runCommand(command, args).get();
                    } catch (Exception e) {
                        sender.sendMessage("I was unable to process your command, please try again later.");
                        LOGGER.error("An exception occurred while attempting to run a command.", e);
                    }
                }

            } catch (Exception e) {
                if (sender != null)
                    sender.sendMessage("I was unable to process your command, please try again later.");
                LOGGER.error("An exception occurred while trying to query the database.", e);
            }
        }
    }
}
