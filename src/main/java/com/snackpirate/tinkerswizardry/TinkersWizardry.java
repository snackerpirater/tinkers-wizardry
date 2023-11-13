package com.snackpirate.tinkerswizardry;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TinkersWizardry.MOD_ID)
public class TinkersWizardry
{
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "tinkers_wizardry";

    public TinkersWizardry()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
