package com.zethneralith.zethremmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class ItemButterflyWing extends UseItem
{
    public ItemButterflyWing(String name, String desc)
    {
        super(name, desc, false);

        setUseXPCost(0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (!worldIn.isRemote)
        {
            BlockPos bpBedLoc = playerIn.getBedLocation().up();
            if (bpBedLoc != null)
            {
                playerIn.setPositionAndUpdate(bpBedLoc.getX(), bpBedLoc.getY(), bpBedLoc.getZ());
                itemStackIn.stackSize--;
            }
        }

        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
