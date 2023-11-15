package com.snackpirate.tinkerswizardry.modifiers;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.TankModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.definition.module.ToolModuleHooks;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
//if it stupid and it works it ain't stupid, until now
public class FireballingModifier extends TankModifier implements GeneralInteractionModifierHook
{
    private ModifierTank tank;
    public FireballingModifier(int capacity) {
        super(capacity);
    }

    @Override
    public int getPriority()
    {
        return 81;
    }

    protected int coolDownTime() { return 10;}

    protected int spellCost() { return 20;}
    protected int fluidCost() { return 100;}
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source)
    {
        if ((player.isCreative() || consumeNeeded(tool, player)) && tool.getDefinitionData().getModule(ToolModuleHooks.INTERACTION).canInteract(tool, modifier.getId(), source))
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

    protected boolean consumeNeeded(IToolStackView tool, Player player)
    {
        FluidStack fluid = getFluid(tool);
        ResourceLocation overslime = new ResourceLocation("tconstruct:overslime");
        if (tool.getPersistentData().contains(overslime, Tag.TAG_INT) && tool.getPersistentData().getInt(overslime)>=this.spellCost() && (fluid.getFluid().getAttributes().getTemperature()>= Fluids.LAVA.getAttributes().getTemperature() && fluid.getAmount()>=(player.isCreative() ? 1 : fluidCost())))
        {
            drain(tool, fluid, fluidCost());
            tool.getPersistentData().putInt(overslime, tool.getPersistentData().getInt(overslime)-this.spellCost());
            return true;
        }
        return false;
    }

}


