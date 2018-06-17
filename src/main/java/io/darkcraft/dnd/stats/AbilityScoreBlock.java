package io.darkcraft.dnd.stats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class AbilityScoreBlock implements HasAbilityScore
{
	@JsonProperty
	public final Integer str;
	@JsonProperty
	public final Integer dex;
	@JsonProperty
	public final Integer con;
	@JsonProperty("int")
	public final Integer inte;
	@JsonProperty
	public final Integer wis;
	@JsonProperty
	public final Integer chr;

	@JsonCreator
	public AbilityScoreBlock(@JsonProperty("str") Integer str,
			@JsonProperty("dex") Integer dex,
			@JsonProperty("con") Integer con,
			@JsonProperty("int") Integer inte,
			@JsonProperty("wis") Integer wis,
			@JsonProperty("chr") Integer chr)
	{
		this.str = str;
		this.dex = dex;
		this.con = con;
		this.inte = inte;
		this.wis = wis;
		this.chr = chr;
	}

	@Override
	public Integer getStr()
	{
		return str;
	}

	@Override
	public Integer getDex()
	{
		return dex;
	}

	@Override
	public Integer getCon()
	{
		return con;
	}

	@Override
	public Integer getInt()
	{
		return inte;
	}

	@Override
	public Integer getWis()
	{
		return wis;
	}

	@Override
	public Integer getChr()
	{
		return chr;
	}

}
