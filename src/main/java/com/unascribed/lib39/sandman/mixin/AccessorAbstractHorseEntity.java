package com.unascribed.lib39.sandman.mixin;

import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.inventory.SimpleInventory;

@Mixin(AbstractHorseEntity.class)
public interface AccessorAbstractHorseEntity {

	@Accessor("items")
	SimpleInventory lib39Sandman$getItems();
	
}
