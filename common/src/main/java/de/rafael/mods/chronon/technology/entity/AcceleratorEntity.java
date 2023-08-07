package de.rafael.mods.chronon.technology.entity;

import de.rafael.mods.chronon.technology.registry.ModEntities;
import de.rafael.mods.chronon.technology.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
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
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

/**
 * @author Rafael K.
 * @since 06/08/2023
 */

@Getter
@Setter
public class AcceleratorEntity extends Entity {

    private static final EntityDataAccessor<Integer> tickRate = SynchedEntityData.defineId(AcceleratorEntity.class, EntityDataSerializers.INT);

    private BlockPos targetBlock;
    private int ticksLeft;

    public AcceleratorEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        this.targetBlock = null;

        entityData.set(tickRate, 1);
    }

    public AcceleratorEntity(Level worldIn, @NotNull BlockPos targetBlock) {
        super(ModEntities.ACCELERATOR.get(), worldIn);
        this.targetBlock = targetBlock;
        this.setPos(targetBlock.getX() + 0.5f, targetBlock.getY() + 0.5f, targetBlock.getZ() + 0.5f);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void tick() {
        super.tick();
        Level level = level();
        if(level.isClientSide()) return;
        if(targetBlock == null) {
            this.remove(RemovalReason.KILLED);
            return;
        }

        {
            var blockState = level.getBlockState(targetBlock);
            var blockEntity = level.getBlockEntity(targetBlock);
            if(blockEntity != null) {
                BlockEntityTicker<BlockEntity> ticker = blockState.getTicker(level, (BlockEntityType<BlockEntity>) blockEntity.getType());
                if(ticker != null) {
                    for (int i = 0; i < tickRate(); i++) {
                        ticker.tick(level, targetBlock, blockState, blockEntity);
                    }
                }
            } else if(level instanceof ServerLevel serverLevel && blockState.isRandomlyTicking()) {
                for (int i = 0; i < tickRate(); i++) {
                    blockState.randomTick(serverLevel, targetBlock, level.random);
                }
            } else {
                this.remove(RemovalReason.KILLED);
                return;
            }
        }

        ticksLeft--;
        if(ticksLeft <= 0) {
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(tickRate, 1);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        entityData.set(tickRate, compoundTag.getInt(Constants.TICK_RATE));
        this.ticksLeft = compoundTag.getInt(Constants.TICKS_LEFT);
        this.targetBlock = NbtUtils.readBlockPos(compoundTag.getCompound(Constants.TARGET_BLOCK));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        compoundTag.putInt(Constants.TICK_RATE, entityData.get(tickRate));
        compoundTag.putInt(Constants.TICKS_LEFT, this.ticksLeft);
        compoundTag.put(Constants.TARGET_BLOCK, NbtUtils.writeBlockPos(this.targetBlock));
    }

    protected boolean canAddPassenger(Entity entity) {
        return false;
    }

    protected boolean couldAcceptPassenger() {
        return false;
    }

    protected void addPassenger(Entity entity) {
        throw new IllegalStateException("Should never addPassenger without checking couldAcceptPassenger()");
    }

    public @NotNull PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    public void tickRate(int rate) {
        entityData.set(tickRate, rate);
    }

    public int tickRate() {
        return entityData.get(tickRate);
    }

    public void extendLifetime(int time) {
        this.ticksLeft += time;
    }

}
