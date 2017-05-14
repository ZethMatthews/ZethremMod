package com.zethneralith.zethremmod.crafting;

import com.zethneralith.zethremmod.blocks.ZethremModBlocks;
import com.zethneralith.zethremmod.item.ZethremModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class ModRecipes
{
    public static void init()
    {
        // Shapeless recipes:
        //GameRegistry.addRecipe(new ItemStack(ZethremModItems.item_Acid_Bottle), Items.GLASS_BOTTLE, ZethremModItems.item_Sticky_Mucus);

        // Shaped recipes:
        GameRegistry.addShapedRecipe(new ItemStack(ZethremModBlocks.block_wooden_crate), "psp", "s s", "psp", 'p', Blocks.PLANKS, 's', Items.STICK);
    }
}
