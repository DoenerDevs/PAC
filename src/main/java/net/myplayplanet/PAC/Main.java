package net.myplayplanet.PAC;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.PacketPlayInFlying;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		this.create(event.getPlayer());
	}
	
	
	public void create(Player player){
		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler(){
			
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext,Object object) throws Exception{
				getLogger().info("READ --> "+ object.toString());
				if(object instanceof PacketPlayInFlying)
				super.channelRead(channelHandlerContext, object);
				
			}
			
			 @Override
			 public void write(ChannelHandlerContext channelHandlerContext,Object object, ChannelPromise channelPromise) throws Exception{
				 getLogger().info("WRITE --> "+ object.toString());
				 super.write(channelHandlerContext, object, channelPromise);
				 
			 }
			
		};
		
		ChannelPipeline channelPipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
		channelPipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
	}
	
	
}
