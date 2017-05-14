package com.zethneralith.zethremmod.proxy;

import com.zethneralith.zethremmod.blocks.ZethremModBlocks;
import com.zethneralith.zethremmod.crafting.ModRecipes;
import com.zethneralith.zethremmod.item.ZethremModItems;
import com.zethneralith.zethremmod.tileentity.ZethremModTileEntities;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event)
    {
        ZethremModItems.preInit();
        ZethremModBlocks.preInit();
    }

    public void init(FMLInitializationEvent event)
    {
        ZethremModTileEntities.init();
        ModRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
