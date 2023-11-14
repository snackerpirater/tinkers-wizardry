package com.snackpirate.tinkerswizardry.modifiers;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.InteractionModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class SpellModifier extends InteractionModifier.NoLevels implements GeneralInteractionModifierHook
{
    public static final int SPELL_COST = 5;
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {
        if (subtractOverslime(tool))
        {
            if (!player.level.isClientSide())
            {
                player.getCooldowns().addCooldown(tool.getItem(), 20);
                castSpell(tool, modifier, player, hand, source);
            }
            player.swing(hand);
            return InteractionResult.PASS;
        }
        return InteractionResult.FAIL;
    }

    protected void castSpell(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {
        LogUtils.getLogger().info("spell cast!");
    }

    protected boolean subtractOverslime(IToolStackView tool)
    {
        ResourceLocation overslime = new ResourceLocation("tconstruct:overslime");
        if (tool.getPersistentData().contains(overslime, Tag.TAG_INT) && tool.getPersistentData().getInt(overslime)>=SPELL_COST)
        {
            tool.getPersistentData().putInt(overslime, tool.getPersistentData().getInt(overslime)-SPELL_COST);
            return true;
        }
        return false;
    }
}
