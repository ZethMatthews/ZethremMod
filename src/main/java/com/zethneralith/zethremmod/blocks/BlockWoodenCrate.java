package com.zethneralith.zethremmod.blocks;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.lwjgl.input.Keyboard;
import scala.util.parsing.json.JSON;

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

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ItemStackHandler"))
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                NBTTagCompound nbtISH = stack.getSubCompound("ItemStackHandler", false);

                NBTTagList nbtItems = nbtISH.getTagList("Items", nbtISH.getId());

                tooltip.add("");
                tooltip.add("Items:");

                for (int i = 0; i < nbtItems.tagCount(); ++i)
                {
                    NBTTagCompound nbtItem = nbtItems.getCompoundTagAt(i);

                    // There has to be a better way to get the name:
                    Item objItem = Item.getByNameOrId(nbtItem.getString("id"));
                    String strItemFQRN = objItem.getItemStackDisplayName(new ItemStack(objItem)); // "item." + nbtItem.getString("id").split(":")[1] + ".name";

                    tooltip.add(" " + strItemFQRN + " - " + nbtItem.getByte("Count"));
                }
            }
            else
            {
                tooltip.add("");
                tooltip.add("[SHIFT] for contents.");
            }
        }

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
                playerIn.openGui(ZethremMod.instance, GuiHandler.WOODEN_CRATE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if(!worldIn.isRemote)
        {
            // Try to copy tag from item to block. This works! :D

            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ItemStackHandler"))
            {
                NBTTagCompound invNBT = stack.getTagCompound().getCompoundTag("ItemStackHandler");

                System.out.println("Found inventory!\n" + invNBT.toString());

                TileEntityWoodenCrate tileEntity = (TileEntityWoodenCrate) worldIn.getTileEntity(pos);

                if (tileEntity != null)
                {
                    //IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

                    NBTTagCompound nbt = tileEntity.serializeNBT();
                    nbt.setTag("ItemStackHandler", invNBT);
                    tileEntity.deserializeNBT(nbt);

                    System.out.println("Loaded inventory... sort of.");
                } else
                    System.out.println("Tile entity at " + pos + " is null. Maybe it doesn't exist yet?");

            }
        }

        //super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            TileEntityWoodenCrate tileEntity = (TileEntityWoodenCrate) worldIn.getTileEntity(pos);

            ItemStack drop = new ItemStack(ZethremModBlocks.block_wooden_crate, 1);

            NBTTagCompound tileNBT = tileEntity.serializeNBT();

            if (tileNBT.hasKey("ItemStackHandler"))
            {
                NBTTagCompound invNBT = tileNBT.getCompoundTag("ItemStackHandler");

                NBTBase itemsNBT = invNBT.getTag("Items");

                if (itemsNBT == null || itemsNBT.hasNoTags())
                {
                    //No items! Not writing empty inventory to block.
                }
                else
                {
                    //IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

                    NBTTagCompound tmp = drop.serializeNBT();
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setTag("ItemStackHandler", invNBT);
                    tmp.setTag("tag", tag);

                    drop.readFromNBT(tmp);
                }

                //System.out.println("Item Final NBT:\n" + drop.serializeNBT().toString());
            }

            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), drop);
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        return new ArrayList<ItemStack>();
    }
}
