package com.zethneralith.zethremmod.item;

import com.zethneralith.zethremmod.ZethremMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by z3pro_000 on 5/12/2017.
 */
public class ZethremModItems
{
    public static EtcItem item_Jellopy;
    public static EtcItem item_Sticky_Mucus;
    public static EtcItem item_Empty_Bottle;
    public static EtcItem item_Acid_Bottle;
    public static EtcItem item_Basic_Screw;
    public static EtcItem item_Cogwheel;
    public static EtcItem item_Mech_Gear;

    public static ItemButterflyWing item_Butterfly_Wing;

    public static ItemArmor item_adventurers_suit_0;

    public static void preInit()
    {
        item_Jellopy = new EtcItem("jellopy", "A small crystallization created by some monsters.");
        item_Sticky_Mucus = new EtcItem("sticky_mucus", "Mysteriously sticky liquid.");
        item_Empty_Bottle = new EtcItem("empty_bottle", "An empty bottle that can be used for carrying liquid.");
        item_Acid_Bottle = new EtcItem("acid_bottle", "A bottle holding a highly corrosive acid.");
        item_Basic_Screw = new EtcItem("basic_screw", "Simple screws used to bind wood or metal.");
        item_Cogwheel = new EtcItem("cogwheel", "A cogwheel used in a machine.");
        item_Mech_Gear = new EtcItem("mech_gear", "A mechanical gear used in machines.");

        item_Butterfly_Wing = new ItemButterflyWing("butterfly_wing", "An enchanted butterfly's wing that instantly sends its user to their Save Point when waved in the air.");

        item_adventurers_suit_0 = new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.CHEST);
        item_adventurers_suit_0.setUnlocalizedName("adventurers_suit_0");
        item_adventurers_suit_0.setCreativeTab(ZethremMod.tabPrimary);

        registerItems();
    }

    public static void registerItems()
    {
        GameRegistry.register(item_Jellopy, new ResourceLocation(ZethremMod.MODID, "jellopy"));
        GameRegistry.register(item_Sticky_Mucus, new ResourceLocation(ZethremMod.MODID, "sticky_mucus"));
        GameRegistry.register(item_Empty_Bottle, new ResourceLocation(ZethremMod.MODID, "empty_bottle"));
        GameRegistry.register(item_Acid_Bottle, new ResourceLocation(ZethremMod.MODID, "acid_bottle"));
        GameRegistry.register(item_Basic_Screw, new ResourceLocation(ZethremMod.MODID, "basic_screw"));
        GameRegistry.register(item_Cogwheel, new ResourceLocation(ZethremMod.MODID, "cogwheel"));
        GameRegistry.register(item_Mech_Gear, new ResourceLocation(ZethremMod.MODID, "mech_gear"));

        GameRegistry.register(item_Butterfly_Wing, new ResourceLocation(ZethremMod.MODID, "butterfly_wing"));

        GameRegistry.register(item_adventurers_suit_0, new ResourceLocation(ZethremMod.MODID, "adventurers_suit_0"));
    }

    public static void registerRenderers()
    {
        RegisterRenderer(item_Jellopy);
        RegisterRenderer(item_Sticky_Mucus);
        RegisterRenderer(item_Empty_Bottle);
        RegisterRenderer(item_Acid_Bottle);
        RegisterRenderer(item_Basic_Screw);
        RegisterRenderer(item_Cogwheel);
        RegisterRenderer(item_Mech_Gear);

        RegisterRenderer(item_Butterfly_Wing);

        RegisterRenderer(item_adventurers_suit_0);
    }

    public static void RegisterRenderer(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ZethremMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
