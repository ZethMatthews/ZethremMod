package com.zethneralith.zethremmod.proxy;

import com.zethneralith.zethremmod.ZethremMod;
import com.zethneralith.zethremmod.blocks.ZethremModBlocks;
import com.zethneralith.zethremmod.client.gui.GuiHandler;
import com.zethneralith.zethremmod.item.ZethremModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        NetworkRegistry.INSTANCE.registerGuiHandler(ZethremMod.instance, new GuiHandler());
        ZethremModItems.registerRenderers();
        ZethremModBlocks.registerRenderers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
