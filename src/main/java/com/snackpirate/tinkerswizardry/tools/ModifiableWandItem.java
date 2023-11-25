package com.snackpirate.tinkerswizardry.tools;

import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;



public class ModifiableWandItem extends ModifiableItem
{
    public static final ToolDefinition WAND = ToolDefinition.builder(WizardryTools.WAND).ranged().build();

    public ModifiableWandItem(Properties properties, ToolDefinition toolDefinition)
    {
        super(properties, toolDefinition);
    }
}
