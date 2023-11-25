package com.snackpirate.tinkerswizardry.tools;

import com.snackpirate.tinkerswizardry.TinkersWizardry;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

public class WizardryTools
{
    private static final Item.Properties TOOL = new Item.Properties().stacksTo(1);

    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersWizardry.MOD_ID);

    public static final ItemObject<ModifiableItem> WAND = ITEMS.register("wand", () -> new ModifiableWandItem(TOOL, ModifiableWandItem.WAND));
}