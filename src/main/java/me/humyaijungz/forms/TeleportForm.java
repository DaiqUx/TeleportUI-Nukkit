package me.humyaijungz.forms;

import me.humyaijungz.Hum;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.ModalFormResponsePacket;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementToggle;
import cn.nukkit.form.element.ElementStepSlider;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.response.FormResponseData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.nukkit.level.Location;

public class TeleportForm implements Listener {
  
  private Hum plugin;
  
  public TeleportForm(Hum plugin){
    this.plugin = plugin;
  }
  
  public Hum getPlugin(){
    return plugin;
  }
  
  public String getPrefix(){
    return plugin.getPrefix();
  }
  
  static int TPUIFORM = 0xAAA0001;
  static int TPAUIFORM = 0xAAA0002;
  static int CONFIRMUIFORM = 0xAAA0003;
  
  public void TpForm(Player p){
    FormWindowCustom window = new FormWindowCustom("Tp");
    for(Player pp : getPlugin().getServer().getOnlinePlayers().values()){
      List<String> pl = Arrays.asList(pp.getName());
      window.addElement(new ElementDropdown("Select", pl));
        p.showFormWindow(window, TPUIFORM);
    }
  }
  
  public void TpaForm(Player p){
    FormWindowCustom window = new FormWindowCustom("Tpa");
    for(Player pp : getPlugin().getServer().getOnlinePlayers().values()){
      List<String> pl = Arrays.asList(pp.getName());
      window.addElement(new ElementDropdown("Select", pl));
        p.showFormWindow(window, TPAUIFORM);
    }
  }
  
  public void ConfirmTpa(Player p, String pname){
    FormWindowModal window = new FormWindowModal(pname, "Do you want "+pname+" Teleport to come to you?", "Accept", "Deny");
    p.showFormWindow(window, CONFIRMUIFORM); 
  }
  
  @EventHandler
  public void onFormResponse(PlayerFormRespondedEvent e){
    Player p = e.getPlayer();
    if(e.getWindow() instanceof FormWindowCustom){
      FormWindowCustom window = (FormWindowCustom) e.getWindow();
      if(e.getFormID() == TPUIFORM){
        if(e.wasClosed()){
          return;
        }
        String response = window.getResponse().getDropdownResponse(0).getElementContent();
        Player player = getPlugin().getServer().getPlayer(response);
        if(player instanceof Player){
          p.teleport((Location)player);
          p.sendMessage(this.getPrefix()+"§a Teleport to§e "+player.getName()+"§a successfully");
          return;
        }else{
          p.sendMessage(this.getPrefix()+"§c Player is not online");
          return;
        }
      }
      if(e.getFormID() == TPAUIFORM){
        if(e.wasClosed()){
          return;
        }
        String response = window.getResponse().getDropdownResponse(0).getElementContent();
        Player player = getPlugin().getServer().getPlayer(response);
        if(player instanceof Player){
          ConfirmTpa(player, p.getName());
          p.sendMessage(this.getPrefix()+"§e You sent a teleport request successfully");
          return;
        }else{
          p.sendMessage(this.getPrefix()+"§c Player is not online");
          return;
        }
      } 
    }
    if(e.getWindow() instanceof FormWindowModal){
      FormWindowModal window = (FormWindowModal) e.getWindow();
      if(e.getFormID() == CONFIRMUIFORM){
        if(e.wasClosed()){
          return;
        }
        if(window.getResponse().getClickedButtonId() == 0){
          String pname = window.getTitle();
          Player pp = getPlugin().getServer().getPlayer(pname);
          p.teleport((Location)pp);
          p.sendMessage(this.getPrefix()+"§e "+pp.getName()+"§a Teleport to you successfully");
          pp.sendMessage(this.getPrefix()+"§a You have teleported successfully");
        }
        if(window.getResponse().getClickedButtonId() == 1){
          String pname = window.getTitle();
          Player pp = getPlugin().getServer().getPlayer(pname);
          pp.sendMessage(this.getPrefix()+"§e You have been denied teleport");
          p.sendMessage(this.getPrefix()+"§e You denied teleport");
          return;
        }
      }
    }
  }
}