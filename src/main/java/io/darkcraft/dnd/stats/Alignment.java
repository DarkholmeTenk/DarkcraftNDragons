package io.darkcraft.dnd.stats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Alignment
{
	@JsonProperty
	public final Law lawfulness;

	@JsonProperty
	public final Good goodness;

	@JsonCreator
	public Alignment(@JsonProperty("lawfulness") Law lawfulness,
			@JsonProperty("goodness") Good goodness)
	{
		this.lawfulness = lawfulness;
		this.goodness = goodness;
	}

	public enum Law
	{
		LAWFUL,
		NEUTRAL,
		CHAOTIC
	}

	public enum Good
	{
		GOOD,
		NEUTRAL,
		EVIL
	}
}
