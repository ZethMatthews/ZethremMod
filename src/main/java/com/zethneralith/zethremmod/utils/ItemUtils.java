package com.zethneralith.zethremmod.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by z3pro_000 on 5/15/2017.
 */
public class ItemUtils
{
    public static NBTTagCompound getTagTag(ItemStack stack)
    {
        if (stack.hasTagCompound())
        {
            return stack.getTagCompound();
        }
        else
        {
            return null;
        }
    }
}
