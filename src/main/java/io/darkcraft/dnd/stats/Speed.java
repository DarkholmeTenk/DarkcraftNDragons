package io.darkcraft.dnd.stats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class Speed
{
    @JsonProperty
	public final String ground;
    @JsonProperty
	public final String swim;
    @JsonProperty
	public final String fly;
    @JsonProperty
    public final String climb;
    @JsonProperty
    public final String burrow;

	@JsonCreator
	public Speed(
			@JsonProperty("ground") String ground,
			@JsonProperty("swim") String swim,
			@JsonProperty("fly") String fly,
			@JsonProperty("climb") String climb,
	        @JsonProperty("burrow") String burrow)
	{
		this.ground = ground;
		this.swim = swim;
		this.fly = fly;
		this.climb = climb;
		this.burrow = burrow;
	}
}
