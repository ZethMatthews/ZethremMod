package com.zethneralith.zethremmod.client.gui;

import com.zethneralith.zethremmod.container.ContainerWoodenCrate;
import com.zethneralith.zethremmod.tileentity.TileEntityWoodenCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class GuiHandler implements IGuiHandler
{
    public static final int WOODEN_CRATE = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == WOODEN_CRATE)
        {
            return new ContainerWoodenCrate(player.inventory, (TileEntityWoodenCrate) world.getTileEntity(new BlockPos(x, y, z)));
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == WOODEN_CRATE)
        {
            return new GuiWoodenCrate(player.inventory, (TileEntityWoodenCrate) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
