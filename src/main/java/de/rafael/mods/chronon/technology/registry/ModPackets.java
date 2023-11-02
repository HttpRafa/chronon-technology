/*
 * MIT License
 *
 * Copyright (c) 2023 Rafael
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.rafael.mods.chronon.technology.registry;

import de.rafael.mods.chronon.technology.ChrononTech;
import de.rafael.mods.chronon.technology.client.network.PacketPlayInChrononSync;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.PlayNetworkDirection;
import net.neoforged.neoforge.network.simple.SimpleChannel;

/**
 * @author Rafael K.
 * @since 18/08/2023
 */

public class ModPackets {

    public static final int PROTOCOL_VERSION = 1;
    public static final AtomicInteger ID = new AtomicInteger();

    private static SimpleChannel CHANNEL;

    public static void registerPackets() {
        CHANNEL = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ChrononTech.MOD_ID, "packets"))
                .networkProtocolVersion(() -> String.valueOf(PROTOCOL_VERSION))
                .clientAcceptedVersions(ModPackets::shouldAccept)
                .serverAcceptedVersions(ModPackets::shouldAccept)
                .simpleChannel();

        CHANNEL.messageBuilder(PacketPlayInChrononSync.class, ID.getAndIncrement(), PlayNetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketPlayInChrononSync::new)
                .encoder(PacketPlayInChrononSync::fillBuffer)
                .consumerMainThread(PacketPlayInChrononSync::handle)
                .add();
    }

    public static SimpleChannel channel() {
        return CHANNEL;
    }

    public static boolean shouldAccept(String version) {
        return true; // TODO: Add better version check for packets
    }

}
