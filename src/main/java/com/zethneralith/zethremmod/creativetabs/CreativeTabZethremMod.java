package com.zethneralith.zethremmod.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class CreativeTabZethremMod extends CreativeTabs
{
    public CreativeTabZethremMod(int index, String label) {
        super(index, label);
    }

    @Override
    public Item getTabIconItem() {
        return Items.BOOK;
    }
}
