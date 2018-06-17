package io.darkcraft.dnd.character.data;

import static io.darkcraft.dnd.character.data.Attribute.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Skill
{
	ATHLETICS(STR),
	ACROBATICS(DEX),
	SLEIGHTOFHAND(DEX),
	STEALTH(DEX),
	ARCANA(INT),
	HISTORY(INT),
	INVESTIGATION(INT),
	NATURE(INT),
	RELIGION(INT),
	ANIMALHANDLING(WIS),
	INSIGHT(WIS),
	MEDICINE(WIS),
	PERCEPTION(WIS),
	SURVIVAL(WIS),
	DECEPTION(CHR),
	INTIMIDATION(CHR),
	PERFORMANCE(CHR),
	PERSUASION(CHR);

	private final Attribute mainAttribute;

	Skill(Attribute mainAttribute)
	{
		this.mainAttribute = mainAttribute;
	}

	@JsonProperty
	public String getName()
	{
		return name();
	}

	@JsonProperty
	public Attribute getMainAttribute()
	{
		return mainAttribute;
	}
}
