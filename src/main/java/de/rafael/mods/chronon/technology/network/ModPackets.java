package de.rafael.mods.chronon.technology.network;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.util.helper.NetworkHelper;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;

/**
 * @author Rafael K.
 * @since 18/08/2023
 */

public class ModPackets {

    @Getter
    public enum Client implements NetworkHelper.Packet {

        CHRONON_SYNC("chronon_sync");

        private final ResourceLocation location;

        Client(String id) {
            this.location = new ResourceLocation(ChrononTech.MOD_ID, id);
        }

    }

}
