package de.rafael.mods.chronon.technology.util.helper;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 18/08/2023
 */

public abstract class NetworkHelper {

    public static void broadCast(Packet packet, ServerLevel level, BlockPos blockPos, FriendlyByteBuf byteBuf) {
        PlayerLookup.tracking(level, blockPos).forEach(serverPlayer -> send(packet, serverPlayer, byteBuf));
    }

    public static void send(@NotNull Packet packet, ServerPlayer serverPlayer, FriendlyByteBuf byteBuf) {
        ServerPlayNetworking.send(serverPlayer, packet.getLocation(), byteBuf);
    }

    public interface Packet {

        ResourceLocation getLocation();

    }

}
