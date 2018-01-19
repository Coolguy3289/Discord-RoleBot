package com.tcpcommunity.command.api;

import com.tcpcommunity.Main;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;

public class PrivateCommandSender extends DiscordCommandSender {

    private PrivateChannel privateChannel;

    public PrivateCommandSender(Main dbot, Message message) {
        super(dbot, message);
        if(!message.isFromType(ChannelType.PRIVATE))
            throw new IllegalArgumentException("Message must be from type " + ChannelType.PRIVATE + ".");
        privateChannel = message.getPrivateChannel();
    }

    public PrivateChannel getPrivateChannel() {
        if(privateChannel == null) privateChannel = getUser().openPrivateChannel().complete();
        return privateChannel;
    }

    @Override
    public void sendMessage(String message) {
        getPrivateChannel().sendMessage(message).queue();
    }

}
