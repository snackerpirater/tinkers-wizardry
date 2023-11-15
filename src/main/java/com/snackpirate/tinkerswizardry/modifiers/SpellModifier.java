package com.snackpirate.tinkerswizardry.modifiers;

import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.TankModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.definition.module.ToolModuleHooks;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SpellModifier extends Modifier implements GeneralInteractionModifierHook
{
    @Override
    public int getPriority()
    {
        return 81;
    }

    protected int coolDownTime() { return 10;}

    protected int spellCost() { return 5;}
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {
        if ((player.isCreative() || consumeOverslime(tool)) && tool.getDefinitionData().getModule(ToolModuleHooks.INTERACTION).canInteract(tool, modifier.getId(), source))
        {
            if (!player.level.isClientSide())
            {
                player.getCooldowns().addCooldown(tool.getItem(), this.coolDownTime());
                castSpell(tool, modifier, player, hand, source);
            }
            player.swing(hand);
            return InteractionResult.PASS;
        }
        return InteractionResult.FAIL;
    }

    protected void castSpell(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {}

    protected boolean consumeOverslime(IToolStackView tool)
    {
        ResourceLocation overslime = new ResourceLocation("tconstruct:overslime");
        if (tool.getPersistentData().contains(overslime, Tag.TAG_INT) && tool.getPersistentData().getInt(overslime)>=this.spellCost())
        {
            tool.getPersistentData().putInt(overslime, tool.getPersistentData().getInt(overslime)-this.spellCost());
            return true;
        }
        return false;
    }
}
