package com.snackpirate.tinkerswizardry.modifiers;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class LightningingModifier extends SpellModifier
{
    public LightningingModifier(int capacity) {
        super(capacity);
    }

    @Override
    protected int coolDownTime(ModifierEntry modifier) {
        return 60;
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return 72000;
    }


    protected void castSpell(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source, int chargeTime)
    {
        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, player.level);
        Vec3 playerPos = player.position();
        double boltX = playerPos.x + ((chargeTime/7.0)*player.getLookAngle().x);
        double boltZ  = playerPos.z + ((chargeTime/7.0)*player.getLookAngle().z);
        double boltY = player.level.getHeight(Heightmap.Types.MOTION_BLOCKING, (int)boltX, (int)boltZ);
        bolt.setPos(new Vec3(boltX, boltY, boltZ));
        player.level.addFreshEntity(bolt);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && !tool.isBroken()) {
            if (!player.level.isClientSide) player.playNotifySound(SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.PLAYERS, 2.0F, (RANDOM.nextFloat() - RANDOM.nextFloat()) * 0.2F + 1.0F);
            ModifierUtil.startUsingItem(tool, modifier.getId(), player, hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onUsingTick(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int timeLeft)
    {
        Player player = (Player)entity;
        Vec3 playerPos = player.getEyePosition();
        int chargeTime = this.getUseDuration(tool, modifier) - timeLeft;
        double boltX = playerPos.x + ((chargeTime/7.0)*player.getLookAngle().x);
        double boltZ  = playerPos.z + ((chargeTime/7.0)*player.getLookAngle().z);
        double boltY = player.level.getHeight(Heightmap.Types.MOTION_BLOCKING, (int)boltX, (int)boltZ);
        entity.getLevel().addParticle(DustParticleOptions.REDSTONE, boltX, boltY, boltZ, 1, 1, 1);
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier)
    {
        return UseAnim.SPEAR;
    }

    @Override
    public boolean onStoppedUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int timeLeft)
    {
        if (!entity.getLevel().isClientSide) {
            this.castSpell(tool, modifier, (Player)entity, entity.getUsedItemHand(), InteractionSource.RIGHT_CLICK, (getUseDuration(tool, modifier) - timeLeft));
            ((Player) entity).getCooldowns().addCooldown(tool.getItem(), this.coolDownTime(modifier));
        }
        entity.swing(entity.getUsedItemHand());
        return false;
    }
}
