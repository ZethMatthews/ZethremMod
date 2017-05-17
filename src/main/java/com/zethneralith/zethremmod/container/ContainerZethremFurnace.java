package com.zethneralith.zethremmod.container;

import com.zethneralith.zethremmod.tileentity.TileZethremFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by z3pro_000 on 5/16/2017.
 */
public class ContainerZethremFurnace extends Container
{
    private TileZethremFurnace tileInventoryFurnace;

    private int[] cachedFields;

    // These are also stored in the tile entity... Perhaps don't duplicate them?
    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public ContainerZethremFurnace(InventoryPlayer invPlayer, TileZethremFurnace tileEntity)
    {
        tileInventoryFurnace = tileEntity;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 183;

        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlotToContainer(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        final int PLAYER_INVENTORY_YPOS = 125;
        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlotToContainer(new Slot(invPlayer, slotNumber,  xpos, ypos));
            }
        }

        final int FUEL_SLOTS_XPOS = 62;
        final int FUEL_SLOTS_YPOS = 96;
        // Add the tile fuel slots
        for (int x = 0; x < tileEntity.FuelSlotsCount; x++) {
            int slotNumber = x + tileEntity.FirstFuelSlotIndex;
            addSlotToContainer(new SlotFuel(tileInventoryFurnace, slotNumber, FUEL_SLOTS_XPOS + SLOT_X_SPACING * x, FUEL_SLOTS_YPOS));
        }

        final int INPUT_SLOTS_XPOS = 62;
        final int INPUT_SLOTS_YPOS = 24;
        // Add the tile input slots
        for (int y = 0; y < tileEntity.InputSlotsCount; y++) {
            int slotNumber = y + tileEntity.FirstInputSlotIndex;
            addSlotToContainer(new SlotSmeltableInput(tileInventoryFurnace, slotNumber, INPUT_SLOTS_XPOS, INPUT_SLOTS_YPOS+ SLOT_Y_SPACING * y));
        }

        final int OUTPUT_SLOTS_XPOS = 116;
        final int OUTPUT_SLOTS_YPOS = 24;
        // Add the tile output slots
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < tileEntity.OutputSlotsCount / 3; y++) {
                int slotNumber = (x * 3) + y + tileEntity.FirstOutputSlotIndex;
                addSlotToContainer(new SlotOutput(tileInventoryFurnace, slotNumber, OUTPUT_SLOTS_XPOS + SLOT_X_SPACING * x, OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
            }
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return tileInventoryFurnace.isUseableByPlayer(playerIn);
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        return super.transferStackInSlot(playerIn, index);
    }

    @Override
    public void detectAndSendChanges()
    {
        // Do nothing for now...
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);
    }

    public class SlotFuel extends Slot
    {
        public SlotFuel(IInventory inventoryIn, int index, int xPosition, int yPosition)
        {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nullable ItemStack stack)
        {
            return TileZethremFurnace.isItemValidForFuelSlot(stack);
        }
    }

    public class SlotSmeltableInput extends Slot
    {
        public SlotSmeltableInput(IInventory inventoryIn, int index, int xPosition, int yPosition)
        {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nullable ItemStack stack)
        {
            return TileZethremFurnace.isItemValidForInputSlot(stack);
        }
    }

    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return tileInventoryFurnace.isItemValidForOutputSlot(stack);
        }
    }
}
