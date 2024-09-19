package kr.superoreo.chzzkapi.command;

import kr.superoreo.chzzkapi.util.ChzzkMgr;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    private ChzzkMgr chzzkMgr;

    public Command() {
        this.chzzkMgr = ChzzkMgr.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        //TODO find how to load channel id from user nickname
        if (args.length > 0) {
            switch (args[0]) {
                case "connect" -> {
                    if (args.length > 1) {
                        chzzkMgr = ChzzkMgr.getInstance();
                        if (chzzkMgr.addChatOverWatch(args[1])) {
                            sender.sendMessage("added!" + ChatColor.GREEN + " [" + args[1] + "]");
                        } else {
                            sender.sendMessage("failed to add!" + ChatColor.RED + " [" + args[1] + "]");
                        }
                    }
                }
                case "disconnect" -> {
                    if (args.length > 1) {
                        chzzkMgr = ChzzkMgr.getInstance();
                        if (chzzkMgr.removeChatOverWatch(args[1])) {
                            sender.sendMessage("removed!" + ChatColor.GREEN + " [" + args[1] + "]");
                        } else {
                            sender.sendMessage("failed to remove!" + ChatColor.RED + " [" + args[1] + "]");
                        }
                    }
                }
            }
        }
        return false;
    }
}
