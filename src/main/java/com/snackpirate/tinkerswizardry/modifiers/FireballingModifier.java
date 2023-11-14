package com.snackpirate.tinkerswizardry.modifiers;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class FireballingModifier extends SpellModifier
{
    @Override
    protected void castSpell(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {
        //copied from TiC Firebreath Modifier. soz
        player.playNotifySound(SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 2.0F, (RANDOM.nextFloat() - RANDOM.nextFloat()) * 0.2F + 1.0F);
        if (!player.level.isClientSide)
        {
            Vec3 lookVec = player.getLookAngle().multiply(2.0f, 2.0f, 2.0f);
            SmallFireball fireball = new SmallFireball(player.level, player, lookVec.x + player.getRandom().nextGaussian() / 16, lookVec.y, lookVec.z + player.getRandom().nextGaussian() / 16);
            lookVec = lookVec.multiply(0.5D, 0.5D, 0.5D);
            fireball.setPos(fireball.getX()+lookVec.x, player.getY(0.5D) + 0.5D+lookVec.y, fireball.getZ()+lookVec.z);
            player.level.addFreshEntity(fireball);
        }
    }
}
