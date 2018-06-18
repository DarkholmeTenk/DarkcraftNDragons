package io.darkcraft.dnd.monster;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.darkcraft.dnd.character.StatSheet;
import io.darkcraft.dnd.stats.AbilityScoreBlock;
import io.darkcraft.dnd.stats.Alignment;
import io.darkcraft.dnd.stats.Feature;
import io.darkcraft.dnd.stats.SheetType;
import io.darkcraft.dnd.stats.Size;
import io.darkcraft.dnd.stats.Speed;
import io.darkcraft.dnd.stats.Spellcasting;

@JsonInclude(Include.NON_EMPTY)
public class MonsterSheet implements StatSheet
{
    @Id
    public String id;
    @JsonProperty
    public String source;
	@JsonProperty
	@Indexed(unique=true)
	public String name;
	@JsonProperty
	public double cr;
	@JsonProperty
	public Size size;
	@JsonProperty
	public CreatureType type;
	@JsonProperty
	public Alignment alignment;
	@JsonProperty
	public int ac;
	@JsonProperty
	public int maxHP;
	@JsonProperty
	public int pp;
	@JsonProperty
	public String hitDice;
	@JsonProperty
	public Speed speed;
	@JsonProperty
	public AbilityScoreBlock abilities;
	@JsonProperty
	public AbilityScoreBlock savingThrows;
	@JsonProperty
	public List<String> vulnerabilities;
	@JsonProperty
	public List<String> resistances;
	@JsonProperty
	public List<String> damageImmunities;
	@JsonProperty
	public List<String> conditionImmunities;
	@JsonProperty
	public List<String> senses;
	@JsonProperty
	public List<String> languages;
	@JsonProperty
	public List<Feature> specialAbilities;
	@JsonProperty
	public List<Feature> actions;
	@JsonProperty
	public List<Feature> legendaryActions;
	@JsonProperty
    public Spellcasting spellcasting;

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
        return SheetType.MONSTER;
    }

    @JsonIgnore
    public Iterable<Feature> getAllFeatures()
    {
        return Stream.of(actions, legendaryActions, specialAbilities)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                ::iterator;
    }
}
