package com.tcpcommunity.command.api;

import com.nachtraben.orangeslice.CommandResult;
import com.nachtraben.orangeslice.CommandSender;
import com.tcpcommunity.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class ConsoleCommandSender implements CommandSender, Runnable {
//
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleCommandSender.class);
//
//    private static ConsoleCommandSender instance;
//    private Scanner input;
//
//    public ConsoleCommandSender() {
//        LOGGER.info("Console command sender initialized.");
//        Asserts.check(instance == null, "There is already an instance of the ConsoleCommandSender");
//        instance = this;
//        input = new Scanner(System.in);
//        Utils.getExecutor().execute(this);
//    }
//
    @Override
    public void sendMessage(String s) {
        LOGGER.info("MESSAGE: " + s);
    }
//
    @Override
    public String getName() {
        return "CONSOLE";
    }
//
    @Override
    public Future<CommandResult> runCommand(String command, String[] args) {
        return Main.getInstance().getCommandBase().execute(this, command, args);

    }
//
    @Override
    public void run() {
//        String message;
//        while(Tohsaka.getInstance().isRunning()) {
//            while((message = input.nextLine()) != null) {
//                String[] tokens = message.split("\\s+");
//                String command = tokens[0];
//                String[] args = tokens.length > 1 ? Arrays.copyOfRange(tokens, 1, tokens.length) : new String[]{};
//                runCommand(command, args);
//            }
//        }
    }
//
}
