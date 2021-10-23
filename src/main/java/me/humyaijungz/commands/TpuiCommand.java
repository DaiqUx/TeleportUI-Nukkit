package me.humyaijungz.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import me.humyaijungz.Hum;

public class TpuiCommand extends Command {
  
  private final Hum plugin;
  
  public TpuiCommand(Hum plugin){
    super("Tpui", "Teleport a player to another player's position");
    this.setPermission("use.tpui");
    this.plugin = plugin;
  }
  
  public String getPrefix(){
    return plugin.getPrefix();
  }
  
  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args){
    if(!this.testPermission(sender)){
      return false;
    }
    if(!sender.hasPermission("use.tpui")){
      sender.sendMessage(this.getPrefix()+"§c You not have Permission");
      return false;
    }
    if(sender instanceof Player){
      plugin.getFormUI().TpForm((Player) sender);
      return true;
    }
    return true;
  }
}