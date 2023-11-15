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
    public static final StaticModifier<Modifier> SPELL = MODIFIERS.register("test_spell", SpellModifier::new);
    public static final StaticModifier<Modifier> FIREBALLING = MODIFIERS.register("fireballing", () -> new FireballingModifier(1000));

    public TWModifierProvider(DataGenerator g)
    {
        super(g);
    }

    @Override
    protected void addModifiers()
    {
        addModifier(new ModifierId(TinkersWizardry.MOD_ID, "spell"), new SpellModifier());
    }

    @Override
    public String getName() {
        return "Tinkers' Wizardry Modifiers";
    }
}
