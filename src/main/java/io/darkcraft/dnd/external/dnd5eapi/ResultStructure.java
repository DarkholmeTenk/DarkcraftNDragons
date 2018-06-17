package io.darkcraft.dnd.external.dnd5eapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultStructure
{
	@JsonProperty
	List<ResultObj> results;

	static class ResultObj
	{
		@JsonProperty
		String name;
		@JsonProperty
		String url;
	}
}
