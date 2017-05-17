package com.zethneralith.zethremmod.client.gui.components;

import com.zethneralith.zethremmod.ZethremMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by z3pro_000 on 5/17/2017.
 */
public class GuiBarGauge extends GuiButton
{
    private final ResourceLocation texture = new ResourceLocation(ZethremMod.MODID, "textures/gui/component/gui_bar_gauge.png");

    private int maxValue;
    private int minValue;
    private int value;

    public GuiBarGauge(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int minValue, int maxValue)
    {
        super(buttonId, x, y, widthIn, heightIn, buttonText);

        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY)
    {
        super.drawButtonForegroundLayer(0, 0);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        //super.drawButton(mc, mouseX, mouseY);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(xPosition, yPosition, 0, 0, 18, 70);
        GlStateManager.color(1.0F, 0.0f, 0.0f, 1.0f);
        drawTexturedModalRect(xPosition, yPosition + value, 18, 0, 18, 69 - value);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }


}
