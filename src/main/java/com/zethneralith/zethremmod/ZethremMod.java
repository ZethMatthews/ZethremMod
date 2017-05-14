package com.zethneralith.zethremmod;

import com.zethneralith.zethremmod.creativetabs.CreativeTabZethremMod;
import com.zethneralith.zethremmod.item.ZethremModItems;
import com.zethneralith.zethremmod.tileentity.TileEntityWoodenCrate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import com.zethneralith.zethremmod.proxy.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ZethremMod.MODID, version = ZethremMod.VERSION, name = ZethremMod.NAME)
public class ZethremMod
{
    public static final String MODID = "zethremmod";
    public static final String VERSION = "1.0";
    public static final String NAME = "Zeth's Mod";

    @SidedProxy(clientSide = "com.zethneralith.zethremmod.proxy.ClientProxy", serverSide= "com.zethneralith.zethremmod.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static ZethremMod instance;

    public static CreativeTabZethremMod tabPrimary;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("Zeth's Mod Beginning Pre-Initialization!");

        tabPrimary = new CreativeTabZethremMod(CreativeTabs.getNextID(), "tab_zethrem");

        proxy.preInit(event);
        System.out.println("Zeth's Mod Finished Pre-Initialization!");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
