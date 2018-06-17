package io.darkcraft.dnd.stats;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class Feature
{
	@JsonProperty
	public String name;
	@JsonProperty
	public String desc;
	@JsonProperty
	public Integer damageBonus;
	@JsonProperty
	public String damageDice;
	@JsonProperty
	public Integer attackBonus;
}
