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
package de.rafael.mods.chronon.technology.entity;

import de.rafael.mods.chronon.technology.registry.ModEntities;
import de.rafael.mods.chronon.technology.util.values.Constants;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 12/08/2023
 */

@Getter
@Setter
public class AcceleratorEntity extends Entity {

    private static final EntityDataAccessor<Integer> tickRate = SynchedEntityData.defineId(AcceleratorEntity.class, EntityDataSerializers.INT);

    private long ticksLeft;
    private BlockPos blockPos;

    //TODO: Remove animation fields
    private long lastRenderTime = System.currentTimeMillis();
    private boolean animationDirection = true;
    private float animationOffset = 0;

    public AcceleratorEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public AcceleratorEntity(Level level, BlockPos blockPos) {
        super(ModEntities.ACCELERATOR.get(), level);
        this.blockPos = blockPos;
        this.setPos(this.blockPos.getX() + 0.5f, this.blockPos.getY() + 0.5f, this.blockPos.getZ() + 0.5f);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void tick() {
        super.tick();

        Level level = getLevel();
        if(level.isClientSide()) return;
        if(blockPos == null) { remove(); return; }

        BlockState blockState = level.getBlockState(this.blockPos);
        BlockEntity blockEntity = level.getBlockEntity(this.blockPos); // Get target block entity
        if(blockEntity != null) { // Is blockEntity
            BlockEntityTicker<BlockEntity> ticker = blockState.getTicker(level, (BlockEntityType<BlockEntity>) blockEntity.getType());
            if(ticker != null) tick(() -> ticker.tick(level, this.blockPos, blockState, blockEntity));
        } else if(blockState.isRandomlyTicking() && level instanceof ServerLevel serverLevel) {
            tick(() -> blockState.randomTick(serverLevel, this.blockPos, level.getRandom()));
        } else { remove(); return; }

        ticksLeft--;
        if(ticksLeft <= 0) remove();
    }

    private void tick(Runnable runnable) {
        int rate = entityData.get(tickRate) - 1;
        for (int i = 0; i < rate; i++) {
            runnable.run();
        }
    }

    private void remove() {
        this.remove(RemovalReason.KILLED);
    }

    public int getTickRate() {
        return entityData.get(tickRate);
    }

    public void setTickRate(int rate) {
        entityData.set(tickRate, rate);
    }

    @Deprecated(forRemoval = true)
    public void stepAnimation(float speed, float upperBound, float lowerBound) {
        if(animationDirection) {
            this.animationOffset += speed * calcDelta();
            if(this.animationOffset > upperBound) {
                this.animationOffset = upperBound;
                this.animationDirection = !this.animationDirection;
            }
        } else {
            this.animationOffset -= speed * calcDelta();
            if(this.animationOffset < lowerBound) {
                this.animationOffset = lowerBound;
                this.animationDirection = !this.animationDirection;
            }
        }
    }

    @Deprecated(forRemoval = true)
    public float calcDelta() {
        float deltaTime = (System.currentTimeMillis() - lastRenderTime) / 1000f;
        lastRenderTime = System.currentTimeMillis();
        return deltaTime;
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(tickRate, 1);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        entityData.set(tickRate, compoundTag.getInt(Constants.NbtKeys.TICK_RATE));
        this.ticksLeft = compoundTag.getLong(Constants.NbtKeys.TICKS_LEFT);
        this.blockPos = NbtUtils.readBlockPos(compoundTag.getCompound(Constants.NbtKeys.BLOCK_POS));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        compoundTag.putInt(Constants.NbtKeys.TICK_RATE, entityData.get(tickRate));
        compoundTag.putLong(Constants.NbtKeys.TICKS_LEFT, this.ticksLeft);
        compoundTag.put(Constants.NbtKeys.BLOCK_POS, NbtUtils.writeBlockPos(this.blockPos));
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return false;
    }

    @Override
    protected void addPassenger(Entity entity) {
        throw new IllegalStateException("Should never addPassenger without checking couldAcceptPassenger()");
    }

    @Override
    public @NotNull PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

}
