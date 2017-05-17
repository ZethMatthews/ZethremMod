package com.zethneralith.zethremmod.blocks;

import com.zethneralith.zethremmod.ZethremMod;
import com.zethneralith.zethremmod.client.gui.GuiHandler;
import com.zethneralith.zethremmod.tileentity.TileZethremFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by z3pro_000 on 5/16/2017.
 */
public class BlockZethremFurnace extends Block implements ITileEntityProvider
{
    private static final String UNLOCALIZED_NAME = "zethrem_furnace";

    public BlockZethremFurnace()
    {
        super(Material.ROCK);
        this.setUnlocalizedName(UNLOCALIZED_NAME);
        this.setCreativeTab(ZethremMod.tabPrimary);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileZethremFurnace();
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileZethremFurnace();
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote) return true;

        playerIn.openGui(ZethremMod.instance, GuiHandler.ZETHREM_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (tileEntity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileEntity);
        }

        super.breakBlock(worldIn, pos, state);
    }
}
