package com.unascribed.lib39.sandman;

import java.util.Set;

import com.unascribed.lib39.core.Lib39Log;
import com.unascribed.lib39.sandman.api.TicksAlwaysItem;
import com.unascribed.lib39.sandman.mixin.AccessorAbstractHorseEntity;
import com.unascribed.lib39.sandman.mixin.AccessorLootableContainerBlockEntity;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;

public class TickAlwaysItemHandler {

	public static final boolean DISABLED = Boolean.getBoolean("lib39.disable-sandman");
	
	static {
		if (DISABLED) Lib39Log.warn("Disabling Sandman, as you requested. I hope you're running a large-scale convention and breaking behavior is worth the performance!");
	}
	
	public static void startServerWorldTick(ServerWorld world) {
		if (DISABLED) return;
		var rnd = ThreadLocalRandom.current();
		for (var wc : Lib39Sandman.getLoadedChunks(world)) {
			if (rnd.nextInt(40) == 0) {
				var bes = wc.getBlockEntities();
				if (bes != null) {
					for (BlockEntity be : bes.values()) {
						if (rnd.nextInt(3) == 0) {
							if (be instanceof AccessorLootableContainerBlockEntity a && a.lib39Sandman$getLootTableId() != null)
								continue;
							if (be instanceof Inventory inv) {
								for (int i = 0; i < inv.size(); i++) {
									ItemStack is = inv.getStack(i);
									if (is.getItem() instanceof TicksAlwaysItem tai) {
										tai.blockInventoryTick(is, world, be.getPos(), i);
										inv.setStack(i, is);
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		for (Entity e : world.getEntitiesByType(TypeFilter.instanceOf(Entity.class), Predicates.alwaysTrue())) {
			if (e instanceof PlayerEntity) {
				EnderChestInventory inv = ((PlayerEntity) e).getEnderChestInventory();
				for (int i = 0; i < inv.size(); i++) {
					ItemStack is = inv.getStack(i);
					if (is.getItem() instanceof TicksAlwaysItem) {
						((TicksAlwaysItem)is.getItem()).entityInventoryTick(is, world, e, i, false);
						inv.setStack(i, is);
					}
				}
				continue;
			}
			if (e instanceof ItemEntity) {
				ItemStack is = ((ItemEntity) e).getStack();
				if (is.getItem() instanceof TicksAlwaysItem) {
					((TicksAlwaysItem)is.getItem()).entityInventoryTick(is, world, e, 0, false);
					if (is.isEmpty()) e.discard();
				}
				continue;
			}
			if (e instanceof ItemFrameEntity) {
				ItemStack is = ((ItemFrameEntity) e).getHeldItemStack();
				if (is.getItem() instanceof TicksAlwaysItem) {
					((TicksAlwaysItem)is.getItem()).entityInventoryTick(is, world, e, 0, false);
					if (is.isEmpty()) {
						((ItemFrameEntity) e).setHeldItemStack(ItemStack.EMPTY, true);
					}
				}
				continue;
			}
			if (rnd.nextInt(40) == 0) {
				if (e.getClass().getName().equals("net.mehvahdjukaar.snowyspirit.common.entity.ContainerHolderEntity")) {
					var nbt = new NbtCompound();
					e.writeNbt(nbt);
					if (nbt.contains("LootTable")) continue;
				}
				Set<ItemStack> seen = Sets.newIdentityHashSet();
				if (e instanceof AbstractHorseEntity) {
					SimpleInventory inv = ((AccessorAbstractHorseEntity)e).lib39Sandman$getItems();
					for (int i = 0; i < inv.size(); i++) {
						ItemStack is = inv.getStack(i);
						if (is.getItem() instanceof TicksAlwaysItem && seen.add(is)) {
							((TicksAlwaysItem)is.getItem()).entityInventoryTick(is, world, e, i, false);
							inv.setStack(i, is);
						}
					}
				}
				if (e instanceof LivingEntity) {
					for (EquipmentSlot slot : EquipmentSlot.values()) {
						ItemStack is = ((LivingEntity) e).getEquippedStack(slot);
						if (is.getItem() instanceof TicksAlwaysItem && seen.add(is)) {
							((TicksAlwaysItem)is.getItem()).entityInventoryTick(is, world, e, slot.getEntitySlotId(), false);
							((LivingEntity) e).equipStack(slot, is);
						}
					}
				}
				if (e instanceof Inventory) {
					Inventory inv = (Inventory)e;
					for (int i = 0; i < inv.size(); i++) {
						ItemStack is = inv.getStack(i);
						if (is.getItem() instanceof TicksAlwaysItem && seen.add(is)) {
							((TicksAlwaysItem)is.getItem()).entityInventoryTick(is, world, e, i, false);
							inv.setStack(i, is);
						}
					}
				}
			}
		}
	}

}
