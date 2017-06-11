package com.tcpcommunity;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;

import java.util.*;

/**
 * Created by Coolguy3289 on 6/5/2017.
 */
public class RoleSetter {

    private Map<Long, List<String>> ALLOWED_ROLES;

    public RoleSetter() {
        ALLOWED_ROLES = new HashMap<>();
    }


    public void addRoleToList(Long guild, String... role) {
        List<String> roles = ALLOWED_ROLES.computeIfAbsent(guild, k -> new ArrayList<>());
        roles.addAll(Arrays.asList(role));
    }

    public void run(Message message, String role) {
        run(message, role, false);
    }

    public void run(Message message, String role, Boolean removeIfExists) {
        Guild guild = message.getGuild();
        Member member = guild.getMember(message.getAuthor());
        if(role != null) {
            // Check if we have specified roles for the guild, and that the guild at least contains a single role matching that string.
            if (ALLOWED_ROLES.containsKey(guild.getIdLong()) && !guild.getRolesByName(role, true).isEmpty()) {
                // Iterate through all the roles we said we could give for this guild, see if any of them matches the role the user requested.
                for (String s : ALLOWED_ROLES.get(guild.getIdLong())) {
                    if (s.toLowerCase().matches(role.toLowerCase())) {
                        // Extract the role object to be assigned to the user.
                        Role r = guild.getRolesByName(role, true).get(0);
                        // If the member has the role
                        if (member.getRoles().contains(r)) {
                            if(removeIfExists) {
                                try {
                                    guild.getController().removeRolesFromMember(member, r).queue((Void) -> {
                                        message.getTextChannel().sendMessage(String.format("I removed `%s` from you, %s.", role, member.getAsMention())).queue();
                                    });
                                } catch(Exception e){
                                    message.getTextChannel().sendMessage(String.format("I was unable to remove `%s` from you %s , Please contact an administrator for help!", role, member.getAsMention())).queue();
                                    e.printStackTrace();
                                }
                            } else {
                                message.getTextChannel().sendMessage(String.format("You already have the `%s` role %s", role, member.getAsMention())).queue();
                            }
                            break;
                        }
                        // If the member does not have the role and we aren't spose to be removing shit.
                        else if(!removeIfExists) {
                            // Add the role, send a message if it fails magically.
                            try {
                                guild.getController().addRolesToMember(member, r).queue((Void) -> {
                                    message.getTextChannel().sendMessage(String.format("I added `%s` to you, %s.", role, member.getAsMention())).queue();
                                });
                            } catch (Exception e) {
                                message.getTextChannel().sendMessage(String.format("I was unable to add `%s` to you %s , Please contact an administrator for help!", role, member.getAsMention())).queue();
                                e.printStackTrace();
                            }

                            // Break because you found the role and finished.
                            break;
                        }
                        // If they don't have the rank and we are spose to remove something
                        else {
                            message.getTextChannel().sendMessage(String.format("You don't have that role %s , Try adding it first! ", message.getAuthor().getAsMention())).queue();
                        }
                    }
                }
            }
        }
        if(message != null && guild.getMember(message.getJDA().getSelfUser()).hasPermission(message.getTextChannel(), Permission.MESSAGE_MANAGE)) {
            System.out.println("Deleting message: " + message.getStrippedContent());
            message.delete().queue();
        } else {
            System.out.println("Message already deleted, Ignoring.");
        }
    }
}
