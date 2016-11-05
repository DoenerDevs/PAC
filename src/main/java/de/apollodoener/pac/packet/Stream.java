package de.apollodoener.pac.packet;

import de.apollodoener.pac.PAC;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_10_R1.PacketPlayInFlying;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by raffael on 05.11.16.
 *
 * @author Tigifan
 * @version 1.0
 */
public class Stream {

    public void create(Player player){
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

            @Override
			public void channelRead(ChannelHandlerContext channelHandlerContext, Object object) throws Exception{
                PAC.getInstance().getLogger().info("READ --> "+ object.toString());
                if(object instanceof PacketPlayInFlying)
                super.channelRead(channelHandlerContext, object);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext,Object object, ChannelPromise channelPromise) throws Exception{
                 PAC.getInstance().getLogger().info("WRITE --> "+ object.toString());
                 super.write(channelHandlerContext, object, channelPromise);
            }
        };
        ChannelPipeline channelPipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        channelPipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }
}
