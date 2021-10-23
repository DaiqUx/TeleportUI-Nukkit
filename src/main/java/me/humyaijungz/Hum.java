package me.humyaijungz;

import cn.nukkit.command.Command;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.Plugin;

import me.humyaijungz.forms.TeleportForm;
import me.humyaijungz.commands.TpauiCommand;
import me.humyaijungz.commands.TpuiCommand;

public class Hum extends PluginBase {
  
  private TeleportForm form;
  private String prefix = "§f[§6TeleportUI§f]§r ";
  
  public void onEnable(){
    this.form = new TeleportForm(this);
    getServer().getPluginManager().registerEvents(new TeleportForm(this), this);
    getServer().getCommandMap().register("Hum", new TpuiCommand(this));
    getServer().getCommandMap().register("Hum", new TpauiCommand(this));
    getLogger().info("§aTeleportUI Enable§c!");
  }
  
  public String getPrefix(){
    return this.prefix;
  }
  
  public TeleportForm getFormUI(){
    return form;
  }
}