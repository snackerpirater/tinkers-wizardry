package com.snackpirate.tinkerswizardry.tools;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableLauncherItem;

import java.util.function.Predicate;

public class ModifiableWandItem extends ModifiableLauncherItem
{
    public static final ToolDefinition WAND = ToolDefinition.builder(TWTools.WAND).ranged().build();
    public ModifiableWandItem(Properties properties, ToolDefinition toolDefinition)
    {
        super(properties, toolDefinition);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return null;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }
}
