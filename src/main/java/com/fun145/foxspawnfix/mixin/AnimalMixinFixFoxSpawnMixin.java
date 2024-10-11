package com.fun145.foxspawnfix.mixin;

import com.fun145.foxspawnfix.FoxSpawnFix;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(AnimalEntity.class)
public abstract class AnimalMixinFixFoxSpawnMixin extends PassiveEntity {
    @Unique
    private static final Tag<Block> FOX_SPAWN_BLOCKS_TAG = TagFactory.BLOCK.create(new Identifier(FoxSpawnFix.MOD_ID, "foxes_spawnable_on"));

    @Unique
    private static boolean isFoxType(EntityType<? extends AnimalEntity> type){
        return EntityType.FOX.equals(type);
    }

    @Unique
    private static boolean canFoxSpawnOn( WorldAccess world, BlockPos pos){
        boolean result = world.getBlockState(pos.down()).isIn(FOX_SPAWN_BLOCKS_TAG);
        result = result && (world.getBaseLightLevel(pos, 0) > 8);//has light level to allow spawn for passive entities
        return result;
    }

    public AnimalMixinFixFoxSpawnMixin(EntityType<? extends PassiveEntity> entityType, World world){
        super(entityType, world);
    }

    @Inject(method="isValidNaturalSpawn", at=@At("HEAD"), cancellable = true)
    private static void isValidNaturalFoxSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir){
        if(isFoxType(type)){
            boolean foxCanSpawn = canFoxSpawnOn( world, pos);
            if(foxCanSpawn){
            cir.setReturnValue(foxCanSpawn);
            }
        }
    }
}
