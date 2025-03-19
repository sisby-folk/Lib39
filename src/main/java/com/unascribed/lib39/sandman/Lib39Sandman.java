package com.unascribed.lib39.sandman;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.unascribed.lib39.core.api.util.ReflectionHelper;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ThreadedChunkManager;
import net.minecraft.world.chunk.WorldChunk;

public class Lib39Sandman implements ModInitializer {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Function<ThreadedChunkManager, Long2ObjectLinkedOpenHashMap<ChunkHolder>> chunkHolders =
			(Function)ReflectionHelper.of(MethodHandles.lookup(), ThreadedChunkManager.class)
				.obtainGetter(Long2ObjectLinkedOpenHashMap.class, "chunkHolders", "field_17220");

	@Override
	public void onInitialize() {
		ServerTickEvents.START_WORLD_TICK.register(TickAlwaysItemHandler::startServerWorldTick);
	}

	public static Iterable<WorldChunk> getLoadedChunks(ServerWorld world) {
        List<WorldChunk> list = new ArrayList<>();
        Long2ObjectLinkedOpenHashMap<ChunkHolder> map = chunkHolders.apply(world.getChunkManager().delegate);

        for (ChunkHolder holder : map.values()) {
            if (holder != null) {
                WorldChunk wc = holder.getWorldChunk();

                if (wc != null) {
                    list.add(wc);
                }
            }
        }

        return list;
	}
	
}
