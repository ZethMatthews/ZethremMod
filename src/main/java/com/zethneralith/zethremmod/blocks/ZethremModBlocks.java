package com.zethneralith.zethremmod.blocks;

import com.zethneralith.zethremmod.ZethremMod;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class ZethremModBlocks
{
    public static BlockWoodenCrate block_wooden_crate;

    public static void preInit()
    {
        block_wooden_crate = new BlockWoodenCrate();

        registerBlocks();
    }

    public static void registerBlocks()
    {
        registerBlock(block_wooden_crate);
    }

    private static void registerBlock(Block block)
    {
        GameRegistry.register(block, new ResourceLocation(ZethremMod.MODID, block.getUnlocalizedName().substring(5)));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(ZethremMod.MODID, block.getUnlocalizedName().substring(5)));
    }

    public static void registerRenderers()
    {
        registerRenderer(block_wooden_crate);
    }

    private static void registerRenderer(Block block)
    {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ZethremMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
