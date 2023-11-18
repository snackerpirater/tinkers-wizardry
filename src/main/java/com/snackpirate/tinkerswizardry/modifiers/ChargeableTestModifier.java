package com.snackpirate.tinkerswizardry.modifiers;

import com.mojang.logging.LogUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ChargeableTestModifier extends SpellModifier
{
    public ChargeableTestModifier(int capacity) {
        super(capacity);
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return 72000;
    }

    @Override
    protected void castSpell(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {
        LogUtils.getLogger().info("charge test cast");
    }

    @Override
    public void onUsingTick(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int timeLeft)
    {
        LogUtils.getLogger().info("charge test tick");
    }
}
