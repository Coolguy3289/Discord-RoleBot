package com.tcpcommunity.configuration;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.List;

public class ConfiguredRole {

    private String name;
    private List<String> triggers;
    private String discordRoleName;
    private String description;

    public ConfiguredRole(String name, String discordRoleName) {
        this.name = name;
        this.discordRoleName = discordRoleName;
    }

    public String getName() {
        return name;
    }

    public ConfiguredRole setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTriggers() {
        return triggers;
    }

    public ConfiguredRole setTriggers(List<String> triggers) {
        this.triggers = triggers;
        return this;
    }

    public String getDiscordRoleName() {
        return discordRoleName;
    }

    public ConfiguredRole setDiscordRoleName(String discordRoleName) {
        this.discordRoleName = discordRoleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ConfiguredRole setDescription(String description) {
        this.description = description;
        return this;
    }

    private MessageEmbed getEmbed() {
        // TODO: Implement
        EmbedBuilder builder = new EmbedBuilder();
        return builder.build();
    }

}
