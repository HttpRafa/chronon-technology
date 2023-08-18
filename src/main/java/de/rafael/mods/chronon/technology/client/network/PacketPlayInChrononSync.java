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
package de.rafael.mods.chronon.technology.client.network;

import de.rafael.mods.chronon.technology.block.entity.CollectorBlockEntity;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 18/08/2023
 */

public class PacketPlayInChrononSync {

    private final BlockPos blockPos;
    private final long chronons;

    public PacketPlayInChrononSync(BlockPos blockPos, long chronons) {
        this.blockPos = blockPos;
        this.chronons = chronons;
    }

    public PacketPlayInChrononSync(@NotNull FriendlyByteBuf byteBuf) {
        this.blockPos = byteBuf.readBlockPos();
        this.chronons = byteBuf.readLong();
    }

    public void fillBuffer(@NotNull FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(this.blockPos);
        byteBuf.writeLong(this.chronons);
    }

    public void handle(@NotNull Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().level != null;
            if(Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof CollectorBlockEntity entity) {
                entity.setStoredChronons(chronons);
            }
        });
    }

}
