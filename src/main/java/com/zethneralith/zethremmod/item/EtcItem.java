package com.zethneralith.zethremmod.item;

import com.zethneralith.zethremmod.ZethremMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class EtcItem extends Item
{
    private String description;

    public EtcItem(String name, String desc) {
        setCreativeTab(ZethremMod.tabPrimary);
        setUnlocalizedName(name);

        description = desc;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(description);
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
