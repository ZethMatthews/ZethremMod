package com.zethneralith.zethremmod.item;

import com.zethneralith.zethremmod.ZethremMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class UseItem extends Item
{
    private String description;
    private int useXPCost;

    public UseItem(String name, String desc, boolean unique)
    {
        setCreativeTab(ZethremMod.tabPrimary);
        setUnlocalizedName(name);
        description = desc;
        useXPCost = 0;

        if (unique)
        {
            setMaxStackSize(1);
        }
    }

    public int getUseXPCost()
    {
        return useXPCost;
    }

    public void setUseXPCost(int cost)
    {
        useXPCost = cost;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(description + "\nUse Cost: " + useXPCost + " levels");
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
