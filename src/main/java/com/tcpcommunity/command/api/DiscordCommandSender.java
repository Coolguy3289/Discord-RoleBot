package com.tcpcommunity.command.api;

import com.nachtraben.orangeslice.CommandResult;
import com.nachtraben.orangeslice.CommandSender;
import com.tcpcommunity.Main;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.Future;

public class DiscordCommandSender implements CommandSender, Serializable {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(DiscordCommandSender.class);

    private transient Main dbot;

    private transient JDA jda;

    private long userID;
    private long messageID;
    private long messageChannelID;

    public DiscordCommandSender(Main dbot, Message message) {
        this.dbot = dbot;
        this.jda = message.getJDA();
        this.userID = message.getAuthor().getIdLong();
        this.messageID = message.getIdLong();
        this.messageChannelID = message.getChannel().getIdLong();
    }

    public Main getBot() {
        return dbot;
    }

    public JDA getJDA() {
        return jda;
    }

    public long getUserID() {
        return userID;
    }

    public User getUser() {
        return jda.getUserById(userID);
    }

    public long getMessageID() {
        return messageID;
    }

    public Message getMessage() {
        MessageChannel channel = getMessageChannel();
        if (channel != null) {
            return channel.getMessageById(messageID).complete();
        }
        return null;
    }

    public long getMessageChannelID() {
        return messageChannelID;
    }

    public MessageChannel getMessageChannel() {
        MessageChannel channel = dbot.getApi().getTextChannelById(messageChannelID);
        if (channel == null) channel = dbot.getApi().getPrivateChannelById(messageChannelID);
        return channel;
    }

    public void sendPrivateMessage(String message) {
        getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
    }

    public void sendPrivateMessage(MessageEmbed embed) {
        getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(embed).queue());
    }

    public void sendPrivateMessage(Message message) {
        getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
    }

    @Override
    public void sendMessage(String message) {
        MessageChannel channel = getMessageChannel();
        if (channel != null)
            channel.sendMessage(message).queue();
    }

    public void sendMessage(MessageEmbed embed) {
        MessageChannel channel = getMessageChannel();
        if (channel != null)
            channel.sendMessage(embed).queue();
    }

    public void sendMessage(Message message) {
        MessageChannel channel = getMessageChannel();
        if (channel != null)
            channel.sendMessage(message).queue();
    }

    public boolean hasPermission() {
        return true;
    }

    @Override
    public String getName() {
        User user = getUser();
        if(user != null) {
            return user.getName();
        } else {
            return "UNKNOWN_USER";
        }
    }

    @Override
    public Future<CommandResult> runCommand(String command, String[] args) {
        return dbot.getCommandBase().execute(this, command, args);
    }

}
