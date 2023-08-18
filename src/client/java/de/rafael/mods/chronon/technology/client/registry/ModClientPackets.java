package de.rafael.mods.chronon.technology.client.registry;

import de.rafael.mods.chronon.technology.client.network.PacketPlayInChrononSync;
import de.rafael.mods.chronon.technology.network.ModPackets;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

/**
 * @author Rafael K.
 * @since 18/08/2023
 */

public class ModClientPackets {

    public static void registerPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ModPackets.Client.CHRONON_SYNC.getLocation(), PacketPlayInChrononSync::receive);
    }

}
