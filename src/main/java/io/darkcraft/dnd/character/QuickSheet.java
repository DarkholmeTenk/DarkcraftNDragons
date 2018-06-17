package io.darkcraft.dnd.character;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.darkcraft.dnd.stats.AbilityScoreBlock;
import io.darkcraft.dnd.stats.SheetType;
import io.darkcraft.dnd.stats.Speed;

@JsonInclude(Include.NON_EMPTY)
public class QuickSheet implements StatSheet
{
    @Id
    public String id;
    @JsonProperty
    public String name;
    @JsonProperty
    public int ac;
    @JsonProperty
    public int maxHP;
    @JsonProperty
    public int pp;
    @JsonProperty
    public Speed speed;
    @JsonProperty
    public AbilityScoreBlock abilities;
    @JsonProperty
    public AbilityScoreBlock savingThrows;

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getAc()
    {
        return ac;
    }

    @Override
    public int getMaxHP()
    {
        return maxHP;
    }

    @Override
    public int getPp()
    {
        return pp;
    }

    @Override
    public Speed getSpeed()
    {
        return speed;
    }

    @Override
    public AbilityScoreBlock getAbilities()
    {
        return abilities;
    }

    @Override
    public AbilityScoreBlock getSavingThrows()
    {
        return savingThrows;
    }

    @Override
    @JsonProperty
    public SheetType getSheetType()
    {
        return SheetType.QUICK;
    }
}
