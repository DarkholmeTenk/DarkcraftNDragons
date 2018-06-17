package io.darkcraft.dnd.character;

import io.darkcraft.dnd.stats.AbilityScoreBlock;
import io.darkcraft.dnd.stats.SheetType;
import io.darkcraft.dnd.stats.Speed;

public interface StatSheet
{

    String getId();

    String getName();

    int getAc();

    int getMaxHP();

    int getPp();

    Speed getSpeed();

    AbilityScoreBlock getAbilities();

    AbilityScoreBlock getSavingThrows();

    SheetType getSheetType();
}