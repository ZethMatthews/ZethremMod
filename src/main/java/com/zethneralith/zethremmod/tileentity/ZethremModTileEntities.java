package com.zethneralith.zethremmod.tileentity;

import com.zethneralith.zethremmod.ZethremMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class ZethremModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityWoodenCrate.class, ZethremMod.MODID + "TileEntityWoodenCrate");
    }
}
