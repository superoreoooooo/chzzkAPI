package kr.superoreo.chzzkapi.command;

import kr.superoreo.chzzkapi.util.ChzzkMgr;
import kr.superoreo.chzzkapi.util.ChzzkUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel;

import java.io.IOException;

public class Command implements CommandExecutor {
    private ChzzkMgr chzzkMgr;

    public Command() {
        this.chzzkMgr = ChzzkMgr.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "connect" -> {
                    if (args.length > 1) {
                        chzzkMgr = ChzzkMgr.getInstance();
                        try {
                            if (chzzkMgr.addChatOverWatch(args[1])) {
                                try {
                                    sender.sendMessage("added!" + ChatColor.GREEN + " [" + ChzzkUtil.getChannelByID(args[1]).getChannelName() + "]");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                sender.sendMessage("failed to add!" + ChatColor.RED + " [" + args[1] + "]");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case "disconnect" -> {
                    if (args.length > 1) {
                        chzzkMgr = ChzzkMgr.getInstance();
                        if (chzzkMgr.removeChatOverWatch(args[1])) {
                            try {
                                sender.sendMessage("removed!" + ChatColor.GREEN + " [" + ChzzkUtil.getChannelByID(args[1]).getChannelName() + "]");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            sender.sendMessage("failed to remove!" + ChatColor.RED + " [" + args[1] + "]");
                        }
                    }
                }
                case "status" -> {
                    chzzkMgr = ChzzkMgr.getInstance();
                    sender.sendMessage(chzzkMgr.getChatOverwatchMap().size() + " chat overwatches");
                    for (ChzzkChannel channel : chzzkMgr.getChatOverwatchMap().keySet()) {
                        sender.sendMessage(channel.getChannelName());
                    }
                }
                case "register" -> {
                    if (args.length > 2) {
                        chzzkMgr = ChzzkMgr.getInstance();
                        try {
                            if (chzzkMgr.getChatOverwatchMap().containsKey(ChzzkUtil.getChannelByID(args[1]))) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    chzzkMgr.addChzzkPlayer(ChzzkUtil.getChannelByID(args[1]), Bukkit.getPlayer(args[2]));
                                    sender.sendMessage("register complete!", ChatColor.GREEN + " [" + ChzzkUtil.getChannelByID(args[1]) + args[2] + "]");
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case "unregister" -> {
                    if (args.length > 1) {
                        chzzkMgr = ChzzkMgr.getInstance();
                        if (Bukkit.getPlayer(args[1]) != null) {
                            if (chzzkMgr.removeChzzkPlayer(Bukkit.getPlayer(args[1]))) {
                                sender.sendMessage("unregistered!" + ChatColor.GREEN + " [" + args[1] + "]");
                            } else {
                                sender.sendMessage("failed to unregister!" + ChatColor.RED + " [" + args[1] + "]");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
