package com.snackpirate.tinkerswizardry.modifiers;

import com.snackpirate.tinkerswizardry.TinkersWizardry;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TWModifierProvider extends AbstractModifierProvider
{
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersWizardry.MOD_ID);
    public static final StaticModifier<Modifier> SPELL = MODIFIERS.register("test_spell", () -> new SpellModifier(0));
    public static final StaticModifier<Modifier> FIREBALLING = MODIFIERS.register("fireballing", () -> new FireballingModifier(1000));

    public static final StaticModifier<Modifier> CHARGE_SPELL = MODIFIERS.register("charging_test", () -> new ChargeableTestModifier(0));

    public TWModifierProvider(DataGenerator g)
    {
        super(g);
    }

    @Override
    protected void addModifiers()
    {

    }

    @Override
    public String getName() {
        return "Tinkers' Wizardry Modifiers";
    }
}
