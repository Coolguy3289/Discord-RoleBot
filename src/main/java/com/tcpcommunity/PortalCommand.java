package com.tcpcommunity;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;

/**
 * Created by Coolguy3289 on 6/5/2017.
 */
public class PortalCommand {


    public static void run(Message message) {
        MessageChannel channel = message.getChannel();
        User author = message.getAuthor();
        channel.sendMessage("Check your PM," + author.getAsMention()).queue();
        PrivateChannel pm = message.getAuthor().openPrivateChannel().complete();
        EmbedBuilder portal1 = new EmbedBuilder();

        portal1.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thecanoepirates.org/");
        portal1.setDescription("_MAIN HUB SERVER_");
        portal1.addField("Info:", "```Markdown\n#The Canoe Pirates\n```", false);
        portal1.addField("Owners:", "JoroxTheAncient", false);
        portal1.setColor(new Color(255, 127, 13));
        portal1.setImage("http://i.imgur.com/yXMD3qJ.png");

        pm.sendMessage(portal1.build()).queue();

        EmbedBuilder portal2 = new EmbedBuilder();

        portal2.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thegamingcorner.net/");
        portal2.setDescription("_BACKUP HUB SERVER_");
        portal2.addField("Info:", "```Markdown\n#The Gaming Corner\n```", false);
        portal2.addField("Test:", "Test value", true);
        portal2.addField("Test2:", "Test2Value", true);
        portal2.setColor(new Color(255, 127, 13));
        portal2.setImage("http://i.imgur.com/vfhrcZz.png");

        pm.sendMessage(portal2.build()).queue();

        EmbedBuilder portal3 = new EmbedBuilder();

        portal3.setTitle("TCPNetwork Communities", "http://discord.zgfgaming.com");
        portal3.addField("Info:", "```Markdown\n#ZGF Gaming\n```", false);
        portal3.addField("Test:", "Test value", true);
        portal3.addField("Test2:", "Test2Value", true);
        portal3.setColor(new Color(255, 127, 13));
        portal3.setImage("http://www.zimsgizmos.biz/zgfgaming/minecraft/advertimg/ZimBanner512w.png");

        pm.sendMessage(portal3.build()).queue();

        EmbedBuilder portal4 = new EmbedBuilder();

        portal4.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thecanoepirates.org/?port=9989");
        portal4.addField("Info:", "```Markdown\n#Strafe Nation\n```", false);
        portal4.addField("Test:", "Test value", true);
        portal4.addField("Test2:", "Test2Value", true);
        portal4.setColor(new Color(255, 127, 13));
        portal4.setImage("http://i.imgur.com/4TF5SNl.jpg");

        pm.sendMessage(portal4.build()).queue();

        EmbedBuilder portal5 = new EmbedBuilder();

        portal5.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thecanoepirates.org/?port=9989");
        portal5.addField("Info:", "```Markdown\n#Tenebres Network\n```", false);
        portal5.addField("Test:", "Test value", true);
        portal5.addField("Test2:", "Test2Value", true);
        portal5.setColor(new Color(255, 127, 13));
        portal5.setImage("http://i.imgur.com/xwG7TP8.jpg?1");

        pm.sendMessage(portal5.build()).queue();

        EmbedBuilder portal6 = new EmbedBuilder();

        portal6.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thecanoepirates.org/?port=9988");
        portal6.addField("Info:", "```Markdown\n#People's Republic of NW\n```", false);
        portal6.addField("Test:", "Test value", true);
        portal6.addField("Test2:", "Test2Value", true);
        portal6.setColor(new Color(255, 127, 13));
        portal6.setImage("http://i.imgur.com/vfeIIJq.png");

        pm.sendMessage(portal6.build()).queue();

        EmbedBuilder portal7 = new EmbedBuilder();

        portal7.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/lsesrp.com");
        portal7.addField("Info:", "```Markdown\n#Darkside Roleplay\n```", false);
        portal7.addField("Test:", "Test value", true);
        portal7.addField("Test2:", "Test2Value", true);
        portal7.setColor(new Color(255, 127, 13));
        portal7.setImage("http://i.imgur.com/DUMqMAM.png");

        pm.sendMessage(portal7.build()).queue();

        EmbedBuilder portal8 = new EmbedBuilder();

        portal8.setTitle("TCPNetwork Communities", "https://discord.gg/rzvhXPq");
        portal8.addField("Info:", "```Markdown\n#Ethereal Gaming Network\n```", false);
        portal8.addField("Test:", "Test value", true);
        portal8.addField("Test2:", "Test2Value", true);
        portal8.setColor(new Color(255, 127, 13));
        // portal8.setImage("http://i.imgur.com/xwG7TP8.jpg?1");

        pm.sendMessage(portal8.build()).queue();

        EmbedBuilder portal9 = new EmbedBuilder();

        portal9.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thecanoepirates.org/?port=9993");
        portal9.addField("Info:", "```Markdown\n#Idiot LAN Party\n```", false);
        portal9.addField("Test:", "Test value", true);
        portal9.addField("Test2:", "Test2Value", true);
        portal9.setColor(new Color(255, 127, 13));
        // portal9.setImage("http://i.imgur.com/xwG7TP8.jpg?1");

        pm.sendMessage(portal9.build()).queue();

        EmbedBuilder portal10 = new EmbedBuilder();

        portal10.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/thecanoepirates.org/?port=9993");
        portal10.addField("Info:", "```Markdown\n#KeeperPi\n```", false);
        portal10.addField("Test:", "Test value", true);
        portal10.addField("Test2:", "Test2Value", true);
        portal10.setColor(new Color(255, 127, 13));
        portal10.setImage("https://i.imgur.com/xO2FXeD.png");

        pm.sendMessage(portal10.build()).queue();

        EmbedBuilder portal11 = new EmbedBuilder();

        portal11.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/158.69.106.42/?port=10639");
        portal11.addField("Info:", "```Markdown\n#NESA\n```", false);
        portal11.addField("Test:", "Test value", true);
        portal11.addField("Test2:", "Test2Value", true);
        portal11.setColor(new Color(255, 127, 13));
        portal11.setImage("http://imagizer.imageshack.com/img924/5406/5zA88X.png");

        pm.sendMessage(portal11.build()).queue();

        EmbedBuilder portal12 = new EmbedBuilder();

        portal12.setTitle("TCPNetwork Communities", "http://www.teamspeak.com/invite/158.69.106.42/?port=10001");
        portal12.addField("Info:", "```Markdown\n#Demilitarized Gaming\n```", false);
        portal12.addField("Test:", "Test value", true);
        portal12.addField("Test2:", "Test2Value", true);
        portal12.setColor(new Color(255, 127, 13));
        portal12.setImage("http://mbmfiles.com/Jan2017/170114-fvThWdVMih95.gif");

        pm.sendMessage(portal12.build()).queue();
    }


}
