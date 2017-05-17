package com.zethneralith.zethremmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Created by z3pro_000 on 5/16/2017.
 */
public class TileZethremFurnace extends TileEntity implements IInventory, ITickable
{
    public int FuelSlotsCount = 3;
    public int InputSlotsCount = 3;
    public int OutputSlotsCount = 9;

    public int FirstFuelSlotIndex;
    public int FirstInputSlotIndex;
    public int FirstOutputSlotIndex;

    private int[] burnTimeRemaining;
    private int[] burnTimeInitialValue;

    private ItemStack[] inventory;
    private int fluidByProduct;
    private int fluidExperience;

    public TileZethremFurnace()
    {
        inventory = new ItemStack[getTotalSlots()];
        fluidByProduct = 0;
        fluidExperience = 0;
        clear();

        burnTimeRemaining = new int[FuelSlotsCount];
        burnTimeInitialValue = new int[FuelSlotsCount];

        RecalcSlots();
    }

    // This function is called when the number of slots on the device changes.
    public void RecalcSlots()
    {
        FirstFuelSlotIndex = 0;
        FirstInputSlotIndex = FirstFuelSlotIndex + FuelSlotsCount;
        FirstOutputSlotIndex = FirstInputSlotIndex + InputSlotsCount;
    }

    public int getTotalSlots()
    {
        return FuelSlotsCount + InputSlotsCount + OutputSlotsCount;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index)
    {
        if (index >= 0 && index < inventory.length)
            return inventory[index];
        else
            return null;
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        // Not sure if this is the right logic.
        if (index >= 0 && index < inventory.length)
        {
            ItemStack stack = inventory[index];

            if (stack.stackSize >= count)
            {
                inventory[index].stackSize -= count;
                return new ItemStack(inventory[index].getItem(), count);
            }
            else
            {
                return inventory[index];
            }
        }
        else
        {
            return null;
        }
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack retVal = inventory[index];
        inventory[index] = null;
        return retVal;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
        inventory[index] = stack;

        if (stack != null)
        {
            if (stack.stackSize > getInventoryStackLimit())
                inventory[index].stackSize = getInventoryStackLimit();
            else if (stack.stackSize == 0)
                inventory[index] = null;
        }

        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    // Return true if the given player is able to use this block. In this case it checks that
    // 1) the world tileentity hasn't been replaced in the meantime, and
    // 2) the player isn't too far away from the centre of the block
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (this.worldObj.getTileEntity(this.pos) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return false;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        // ItemStack.field_190927_a doesn't exist, so we'll use null and
        // hope for the best.
        Arrays.fill(inventory, null);
    }

    @Override
    public void update()
    {

    }

    @Override
    public String getName()
    {
        return "container.zethrem_furnace.name";
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    public static boolean isItemValidForFuelSlot(ItemStack stack)
    {
        // For now, everything is fuel. XD
        return true;
    }

    public static boolean isItemValidForInputSlot(ItemStack stack)
    {
        // For now, we smelt all the things!
        return true;
    }

    public static boolean isItemValidForOutputSlot(ItemStack stack)
    {
        // for now, we can stick anything here.
        return true;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        NBTTagCompound nbtInventory = new NBTTagCompound();
        NBTTagList nbtItems = new NBTTagList();

        for (int i = 0; i < inventory.length; i++)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound nbtItem = inventory[i].serializeNBT();
                nbtItem.setInteger("Slot", i);

                nbtItems.appendTag(nbtItem);
            }
        }

        nbtInventory.setInteger("Size", nbtItems.tagCount());
        nbtInventory.setTag("Items", nbtItems);

        compound.setTag("Inventory", nbtInventory);
        compound.setInteger("fluidByProduct", fluidByProduct);
        compound.setInteger("fluidExperience", fluidExperience);

        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("Inventory"))
        {
            NBTTagCompound nbtInventory = compound.getCompoundTag("Inventory");

            NBTTagList nbtItems = nbtInventory.getTagList("Items", 10);

            for (int i = 0; i < nbtInventory.getInteger("Size"); i++)
            {
                NBTTagCompound nbtItem = nbtItems.getCompoundTagAt(i);

                int slot = nbtItem.getInteger("Slot");
                nbtItem.removeTag("Slot");

                ItemStack stack = new ItemStack(Blocks.DIRT);
                stack.deserializeNBT(nbtItem);

                inventory[slot] = stack;
            }
        }

        fluidByProduct = compound.getInteger("fluidByProduct");
        fluidExperience = compound.getInteger("fluidExperience");

        super.readFromNBT(compound);
    }
}
