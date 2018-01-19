package com.tcpcommunity.listeners;

import com.nachtraben.orangeslice.CommandResult;
import com.nachtraben.orangeslice.event.CommandEventListener;
import com.nachtraben.orangeslice.event.CommandExceptionEvent;
import com.nachtraben.orangeslice.event.CommandPostProcessEvent;
import com.nachtraben.orangeslice.event.CommandPreProcessEvent;
import com.tcpcommunity.command.api.GuildCommandSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandHandler implements CommandEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandler.class);

    @Override
    public void onCommandPreProcess(CommandPreProcessEvent e) {
        if (e.getSender() instanceof GuildCommandSender) {
            GuildCommandSender sendee = (GuildCommandSender) e.getSender();
            LOGGER.debug(String.format("CommandPreProcess >> Sender: %s#{%s}, Args: %s, Flags: %s, Command: %s", sendee.getMember().getEffectiveName(), sendee.getGuild().getName(), e.getArgs(), e.getFlags(), e.getCommand().getName()));
        } else {
            LOGGER.debug(String.format("CommandPreProcess >> Sender: %s, Args: %s, Flags: %s, Command: %s", e.getSender().getName(), e.getArgs(), e.getFlags(), e.getCommand().getName()));
        }
    }

    @Override
    public void onCommandPostProcess(CommandPostProcessEvent e) {
        if (e.getResult().equals(CommandResult.UNKNOWN_COMMAND))
            return;
        if (e.getSender() instanceof GuildCommandSender) {
            GuildCommandSender sendee = (GuildCommandSender) e.getSender();
            LOGGER.debug(String.format("CommandPostProcess >> Sender: %s#{%s}, Args: %s, Flags:%s, Command: %s, Result: %s", sendee.getMember().getEffectiveName(), sendee.getGuild().getName(), e.getArgs(), e.getFlags(), e.getCommand().getName(), e.getResult()));
        } else {
            LOGGER.debug(String.format("CommandPostProcess >> Sender: %s, Args: %s, Flags:%s, Command: %s, Result: %s", e.getSender().getName(), e.getArgs(), e.getFlags(), e.getCommand().getName(), e.getResult()));
        }
        if (e.getResult().equals(CommandResult.INVALID_FLAGS))
            e.getSender().sendMessage("Sorry, but you provided invalid flags for that command. {`" + e.getException().getMessage() + "`}");
    }

    @Override
    public void onCommandException(CommandExceptionEvent e) {
        e.getSender().sendMessage("Unfortunately an error has occurred with your request. The bot author has been notified.");
        LOGGER.error("An error occurred during command execution.", e.getException());
    }
}
