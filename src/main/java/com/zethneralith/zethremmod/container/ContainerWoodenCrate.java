package com.zethneralith.zethremmod.container;

import com.zethneralith.zethremmod.tileentity.TileEntityWoodenCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class ContainerWoodenCrate extends Container
{
    private TileEntityWoodenCrate tileentity;

    public ContainerWoodenCrate(IInventory playerInv, TileEntityWoodenCrate tileentity)
    {
        this.tileentity = tileentity;
        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 62, 17));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 80, 17));
        this.addSlotToContainer(new SlotItemHandler(handler, 2, 98, 17));
        this.addSlotToContainer(new SlotItemHandler(handler, 3, 62, 35));
        this.addSlotToContainer(new SlotItemHandler(handler, 4, 80, 35));
        this.addSlotToContainer(new SlotItemHandler(handler, 5, 98, 35));
        this.addSlotToContainer(new SlotItemHandler(handler, 6, 62, 53));
        this.addSlotToContainer(new SlotItemHandler(handler, 7, 80, 53));
        this.addSlotToContainer(new SlotItemHandler(handler, 8, 98, 53));

        int xPos = 8;
        int yPos = 84;

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            if (fromSlot < handler.getSlots()) {
                // From the block breaker inventory to player's inventory
                if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
                    return null;
            } else {
                // From the player's inventory to block breaker's inventory
                if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
                    return null;
            }

            if (current.stackSize == 0) //Use func_190916_E() instead of stackSize 1.11 only 1.11.2 use getCount()
                slot.putStack(null); //Use ItemStack.field_190927_a instead of (ItemStack)null for a blank item stack. In 1.11.2 use ItemStack.EMPTY
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }


}
