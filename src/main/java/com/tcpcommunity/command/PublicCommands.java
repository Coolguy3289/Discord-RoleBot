package com.tcpcommunity.command;

import com.nachtraben.orangeslice.CommandSender;
import com.nachtraben.orangeslice.command.Cmd;
import com.tcpcommunity.command.api.DiscordCommandSender;
import com.tcpcommunity.command.api.GuildCommandSender;
import com.tcpcommunity.configuration.ConfiguredRole;
import com.tcpcommunity.configuration.RoleConfiguration;
import net.dv8tion.jda.core.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class PublicCommands {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicCommands.class);

    @Cmd(name = "+", format = "{roles}", description = "Use this command to add an approved role to yourself!")
    public void addCommand(CommandSender sender, Map<String, String> args) {
        LOGGER.debug("addCommand Firing!");
        if (sender instanceof DiscordCommandSender) {
            GuildCommandSender sendee = (GuildCommandSender) sender;

            // Reload file incase it changed
            RoleConfiguration.load();

            // Isolate role variable by pulling it out of the hashmap
            List<String> roles = Arrays.asList(args.get("roles").toLowerCase().split("\\s+"));

            // Stream every ConfiguredRole
            // Check if the user specified roles contains the configured roles name
            // Check if the configured roles triggers is contained in the user specified role
            // Grab all that match and return a new list
            Set<ConfiguredRole> selectedRoles = RoleConfiguration.getInstance().getRoles().stream().filter(cr -> roles.contains(cr.getName().toLowerCase()) || cr.getTriggers().stream().anyMatch(trigger -> roles.contains(trigger.toLowerCase()))).collect(Collectors.toSet());
            if(selectedRoles.isEmpty()) {
                sendee.sendMessage("That role doesn't exist.");
                return;
            }

            Set<Role> addRoles = convertToGuildRoles(sendee, selectedRoles, false);

            if(!addRoles.isEmpty()) {
                sendee.getGuild().getController().addRolesToMember(sendee.getMember(), addRoles).queue();
                String roleString = addRoles.stream().map(role -> "`" + role.getName() + "` ").collect(Collectors.joining());
                sendee.sendMessage("Added you to the following roles: " + roleString);
            }
        }
    }

    @Cmd(name = "-", format = "{roles}", description = "Use this command to remove an approved role from yourself!")
    public void delCommand(CommandSender sender, Map<String, String> args) {
        LOGGER.debug("deCommand Firing!");
        if (sender instanceof DiscordCommandSender) {
            GuildCommandSender sendee = (GuildCommandSender) sender;

            // Reload file incase it changed
            RoleConfiguration.load();

            // Isolate role variable by pulling it out of the hashmap
            List<String> roles = Arrays.asList(args.get("roles").toLowerCase().split("\\s+"));

            // Stream every ConfiguredRole
            // Check if the user specified roles contains the configured roles name
            // Check if the configured roles triggers is contained in the user specified role
            // Grab all that match and return a new list
            Set<ConfiguredRole> selectedRoles = RoleConfiguration.getInstance().getRoles().stream().filter(cr -> roles.contains(cr.getName().toLowerCase()) || cr.getTriggers().stream().anyMatch(trigger -> roles.contains(trigger.toLowerCase()))).collect(Collectors.toSet());
            if(selectedRoles.isEmpty()) {
                sendee.sendMessage("That role doesn't exist.");
                return;
            }

            Set<Role> removalRoles = convertToGuildRoles(sendee, selectedRoles, true);

            if(!removalRoles.isEmpty()) {
                sendee.getGuild().getController().removeRolesFromMember(sendee.getMember(), removalRoles).queue();
                String roleString = removalRoles.stream().map(role -> "`" + role.getName() + "` ").collect(Collectors.joining());
                sendee.sendMessage("Removed you from the following roles: " + roleString);
            }
        }
    }

    private Set<Role> convertToGuildRoles(GuildCommandSender sendee, Set<ConfiguredRole> roles, boolean remove) {
        Set<Role> processed = new HashSet<>();
        //LOGGER.debug(roles.toString());
        for(ConfiguredRole role : roles) {
            List<Role> guildRoles = sendee.getGuild().getRolesByName(role.getDiscordRoleName(), true);
            if(guildRoles.isEmpty()) {
                sendee.sendMessage("Ask your guild owner to actually create " + role.getDiscordRoleName() + " before configuring me to use it -.-");
                continue;
            }
            Role guildRole = guildRoles.get(0);
            Member m = sendee.getMember();
            //LOGGER.debug("Has " + role.getName() + "? " + m.getRoles().contains(guildRole));

            // If we have the role and we want to remove it
            // If we don't have role and we wanna add it

            if(m.getRoles().contains(guildRole) && remove) {
                processed.add(guildRole);
            } else if(!m.getRoles().contains(guildRole) && !remove) {
                processed.add(guildRole);
            }
        }
        return processed;
    }


    @Cmd(name = "help", format = "", description = "This command will display how to add roles to yourself, as well as display the allowed roles.")
    public void helpCommand(CommandSender sender, Map<String, String> args) {
        if (sender instanceof DiscordCommandSender) {
            DiscordCommandSender sendee = (DiscordCommandSender) sender;
            RoleConfiguration.load();
            sendee.sendMessage(RoleConfiguration.getInstance().getEmbed());
        }
    }


}