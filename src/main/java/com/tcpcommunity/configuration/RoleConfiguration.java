package com.tcpcommunity.configuration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.nachtraben.lemonslice.ConfigurationUtils;
import com.nachtraben.lemonslice.CustomJsonIO;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleConfiguration implements CustomJsonIO {

    private static final RoleConfiguration instance;
    private static final File DATA_DIR = new File(System.getProperty("user.dir"));


    static {
        instance = new RoleConfiguration();
    }

    private String titleText;
    private String descriptionText;
    private String exampleText;
    private String embedColor;
    private List<ConfiguredRole> roles;

    private RoleConfiguration() {
        roles = new ArrayList<>();
        titleText = "List of Roles and How to add/remove them!";
        descriptionText = "To add a role, type z+ and whichever role you want listed below. To remove roles, type r- and the role you want to remove from the list below.";
        exampleText = "Example Usage\n" +
                "r+ rpg <-- This will add the RPG role to you.\n" +
                "r- rpg <-- This will remove the RPG role from you.";
        embedColor = "#277ecd";
        roles.add(new ConfiguredRole("Sandbox", "sandbox").setDescription("GMOD, Minecraft, etc.").setTriggers(Arrays.asList("all", "sb")));
    }

    public static void save() {
        ConfigurationUtils.saveData("roles.json", DATA_DIR, instance);
    }

    public static void load() {
        ConfigurationUtils.load("roles.json", DATA_DIR, instance);
    }

    public static RoleConfiguration getInstance() {
        return instance;
    }

    @Override
    public JsonElement write() {
        return ConfigurationUtils.GSON_P.toJsonTree(this);
    }
    @Override
    public void read(JsonElement jsonElement) {
        if(jsonElement instanceof JsonObject) {
            JsonObject jo = jsonElement.getAsJsonObject();
            titleText = jo.get("titleText").getAsString();
            descriptionText = jo.get("descriptionText").getAsString();
            exampleText = jo.get("exampleText").getAsString();
            embedColor = jo.get("embedColor").getAsString();
            roles = ConfigurationUtils.GSON_P.fromJson(jo.get("roles"), TypeToken.getParameterized(ArrayList.class, ConfiguredRole.class).getType());
        }
    }

    public String getTitleText() {
        return titleText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getExampleText() {
        return exampleText;
    }

    public Color getEmbedColor() {
        return Color.decode(embedColor);
    }

    public List<ConfiguredRole> getRoles() {
        return roles;
    }

    public ConfiguredRole getRoleByName(String name) {
        return roles.stream().filter(cr -> cr.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public MessageEmbed getEmbed() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(titleText);
        eb.setDescription(descriptionText);

        StringBuilder roles = new StringBuilder();
        getRoles().forEach(cr -> {
            if (!cr.getName().equalsIgnoreCase("CommunityMember"))
                roles.append("`").append(cr.getName()).append("`\n");
        });
        roles.replace(roles.length() - 1, roles.length(), "");

        eb.addField("List of Roles", roles.toString(), false);

//        eb.addField("Roles", "`" + rolename + "'", false);
        eb.addField("Example Usage", exampleText, false);
        eb.setColor(getEmbedColor());
        return eb.build();
    }

}
