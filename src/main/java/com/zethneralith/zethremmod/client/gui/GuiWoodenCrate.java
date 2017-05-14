package com.zethneralith.zethremmod.client.gui;

import com.zethneralith.zethremmod.ZethremMod;
import com.zethneralith.zethremmod.container.ContainerWoodenCrate;
import com.zethneralith.zethremmod.tileentity.TileEntityWoodenCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by z3pro_000 on 5/13/2017.
 */
public class GuiWoodenCrate extends GuiContainer
{
    private TileEntityWoodenCrate tileEntity;
    private IInventory playerInv;

    public GuiWoodenCrate(IInventory playerInv, TileEntityWoodenCrate tileEntity)
    {
        super(new ContainerWoodenCrate(playerInv, tileEntity));

        this.xSize = 176;
        this.ySize = 166;

        this.tileEntity = tileEntity;
        this.playerInv = playerInv;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(ZethremMod.MODID, "textures/gui/container/gui_wooden_crate.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = I18n.format("container.wooden_crate"); //Gets the formatted name for the block breaker from the language file
        this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); //Draws the container name in the center on the top of the gui
        this.mc.fontRendererObj.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 72, 4210752); //The player's inventory name

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
