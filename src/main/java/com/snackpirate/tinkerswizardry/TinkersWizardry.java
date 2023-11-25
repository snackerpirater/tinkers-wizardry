package com.snackpirate.tinkerswizardry;

import com.mojang.logging.LogUtils;
import com.snackpirate.tinkerswizardry.modifiers.TWModifierProvider;
import com.snackpirate.tinkerswizardry.tools.WizardryTools;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TinkersWizardry.MOD_ID)
public class TinkersWizardry
{
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "tinkers_wizardry";

    public TinkersWizardry()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        TWModifierProvider.MODIFIERS.register(bus);
        WizardryTools.ITEMS.register(bus);
    }
}
