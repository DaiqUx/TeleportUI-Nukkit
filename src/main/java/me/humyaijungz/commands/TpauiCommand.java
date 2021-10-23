package me.humyaijungz.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import me.humyaijungz.Hum;

public class TpauiCommand extends Command {
  
  private final Hum plugin;
  
  public TpauiCommand(Hum plugin){
    super("Tpaui", "Teleport a player to another player's position");
    this.setPermission("use.tpaui");
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
    if(!sender.hasPermission("use.tpaui")){
      sender.sendMessage(this.getPrefix()+"§c You not have Permission");
      return false;
    }
    if(sender instanceof Player){
      plugin.getFormUI().TpaForm((Player) sender);
      return true;
    }
    return true;
  }
}