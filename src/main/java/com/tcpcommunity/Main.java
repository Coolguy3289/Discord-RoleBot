package com.tcpcommunity;

import com.nachtraben.orangeslice.CommandBase;
import com.tcpcommunity.command.PublicCommands;
import com.tcpcommunity.listeners.CommandHandler;
import com.tcpcommunity.listeners.DiscordCommandListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.hooks.InterfacedEventManager;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static Main instance;
    private static JDA api;
    private CommandBase commandBase;
    private static String token;


    public static void main(String[] arguments) throws Exception {
        if(arguments.length < 1) {
            System.err.println("You must provide a token when running this application!");
        }
        token = arguments[0];
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            LOGGER.error("Uncaught exception encountered in, " + t.getName(), e);
        });
        new Main();
    }

    public Main() throws Exception {
        instance = this;

        // Init CommandBase and register commands
        commandBase = new CommandBase();
        commandBase.registerEventListener(new CommandHandler());
        commandBase.registerCommands(new PublicCommands());

        // Connect to discord and register discord events.

        api = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setEventManager(new InterfacedEventManager())
                .buildBlocking();
        api.addEventListener(new DiscordCommandListener());
        //api.addEventListener(new EventListeners());
        api.getPresence().setGame(Game.of("Type rhelp for cmds!"));
        api.addEventListener(this);
    }

    public JDA getApi() {
        return api;
    }

    public CommandBase getCommandBase() {
        return commandBase;
    }

    public static Main getInstance() {
        return instance;
    }
}
