package com.snackpirate.tinkerswizardry.tools;

import com.snackpirate.tinkerswizardry.TinkersWizardry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableLauncherItem;

public class TWTools
{
    private static final Item.Properties TOOL = new Item.Properties().stacksTo(1);

    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersWizardry.MOD_ID);

    public static final ItemObject<ModifiableLauncherItem> WAND = ITEMS.register("wand", () -> new ModifiableWandItem(TOOL, ModifiableWandItem.WAND));

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}