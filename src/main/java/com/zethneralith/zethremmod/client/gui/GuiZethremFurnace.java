package com.zethneralith.zethremmod.client.gui;

import com.zethneralith.zethremmod.ZethremMod;
import com.zethneralith.zethremmod.client.gui.components.GuiBarGauge;
import com.zethneralith.zethremmod.container.ContainerZethremFurnace;
import com.zethneralith.zethremmod.tileentity.TileZethremFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

/**
 * Created by z3pro_000 on 5/16/2017.
 */
@SideOnly(Side.CLIENT)
public class GuiZethremFurnace extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation(ZethremMod.MODID, "textures/gui/container/gui_zethrem_furnace.png");
    private TileZethremFurnace tileEntity;
    private IInventory invPlayer;

    private GuiButton btnTest;
    private GuiBarGauge barTest;

    public GuiZethremFurnace(InventoryPlayer invPlayer, TileZethremFurnace tileInventoryFurnace)
    {
        super(new ContainerZethremFurnace(invPlayer, tileInventoryFurnace));

        this.xSize = 176;
        this.ySize = 206;

        this.tileEntity = tileInventoryFurnace;
        this.invPlayer = invPlayer;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        buttonList.clear();

        btnTest = new GuiButton(0, this.guiLeft + 130, this.guiTop + 94, 36, 20, "Test");
        barTest = new GuiBarGauge(1, this.guiLeft + 10, this.guiTop + 23, 18, 69, "Bar Test", 0, 100);

        this.addButton(btnTest);
        this.addButton(barTest);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = I18n.format("container.zethrem_furnace"); //Gets the formatted name for the container from the language file
        this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); //Draws the container name in the center on the top of the gui
        this.mc.fontRendererObj.drawString(this.invPlayer.getDisplayName().getFormattedText(), 8, 114, 4210752); //The player's inventory name
        this.mc.fontRendererObj.drawString("Fuel", 62, 84, 4210752);

        // These are temporary:
        this.mc.fontRendererObj.drawString("---->", 83, 29, 4210752);
        this.mc.fontRendererObj.drawString("---->", 83, 29 + 18, 4210752);
        this.mc.fontRendererObj.drawString("---->", 83, 29 + 36, 4210752);

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
                System.out.println("Player pressed the test button. (ID: 0)");
                barTest.setValue(barTest.getValue() + 5);
                break;
            default:
                System.out.println("Unknown button pressed. ID: " + button.id);
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
