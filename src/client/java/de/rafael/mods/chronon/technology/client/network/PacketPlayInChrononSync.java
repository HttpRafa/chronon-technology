package de.rafael.mods.chronon.technology.client.network;

import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 18/08/2023
 */

public class PacketPlayInChrononSync {

    public static void receive(@NotNull Minecraft client, ClientPacketListener listener, @NotNull FriendlyByteBuf byteBuf, PacketSender packetSender) {
        BlockPos blockPos = byteBuf.readBlockPos();
        long chronons = byteBuf.readLong();

        assert client.level != null;
        if(client.level.getBlockEntity(blockPos) instanceof CollectorBlockEntity entity) {
            entity.setStoredChronons(chronons);
        }
    }

}
