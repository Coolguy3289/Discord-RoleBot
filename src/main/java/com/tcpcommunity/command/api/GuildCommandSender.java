package com.tcpcommunity.command.api;

import com.tcpcommunity.Main;
import net.dv8tion.jda.core.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class GuildCommandSender extends DiscordCommandSender implements Serializable {

    private transient static final Logger LOGGER = LoggerFactory.getLogger(GuildCommandSender.class);

    private long guildId;
    private long textChannelId;

    public GuildCommandSender(Main dbot, Message message) {
        super(dbot, message);
        if(!message.isFromType(ChannelType.TEXT))
            throw new IllegalArgumentException("Message must be from type " + ChannelType.TEXT + ".");

        this.guildId = message.getGuild().getIdLong();
        this.textChannelId = message.getChannel().getIdLong();
    }

    public Guild getGuild() {
        return getJDA().getGuildById(guildId);
    }

    public Member getMember() {
        Guild guild = getGuild();
        if(guild != null)
            return guild.getMemberById(getUserID());
        return null;
    }

    public TextChannel getTextChannel() {
        return getJDA().getTextChannelById(textChannelId);
    }

    public VoiceChannel getVoiceChannel() {
        Member m = getMember();
        if(m != null)
            return m.getVoiceState().getChannel();
        return null;
    }

    @Override
    public void sendMessage(String message) {
        getTextChannel().sendMessage(message).queue();
    }

    public long getGuildId() {
        return guildId;
    }

    public long getTextChannelId() {
        return textChannelId;
    }

}
