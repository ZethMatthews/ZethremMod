package com.zethneralith.zethremmod.blocks;

import com.sun.glass.ui.View;
import com.zethneralith.zethremmod.ZethremMod;
import com.zethneralith.zethremmod.client.gui.GuiHandler;
import com.zethneralith.zethremmod.item.ZethremModItems;
import com.zethneralith.zethremmod.tileentity.TileEntityWoodenCrate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class BlockWoodenCrate extends Block implements ITileEntityProvider
{
    private static String description;

    public BlockWoodenCrate() {
        super(Material.WOOD);
        setUnlocalizedName("wooden_crate");
        setHardness(2F);
        setCreativeTab(ZethremMod.tabPrimary);
        setSoundType(SoundType.WOOD);

        description = "A simple wooden storage crate.";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        tooltip.add(description);
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEntityWoodenCrate();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileEntityWoodenCrate)
            {
                //IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

                playerIn.openGui(ZethremMod.instance, GuiHandler.WOODEN_CRATE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            TileEntityWoodenCrate tileEntity = (TileEntityWoodenCrate) worldIn.getTileEntity(pos);

            // Drop all items in inventory:
            IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            for (int i = 0; i < inventory.getSlots() - 1; ++i)
            {
                ItemStack stack = inventory.getStackInSlot(i);

                if (stack != null) InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        return super.getDrops(world, pos, state, fortune);

        // Set this to "return null" to handle drops elsewhere.
    }
}
