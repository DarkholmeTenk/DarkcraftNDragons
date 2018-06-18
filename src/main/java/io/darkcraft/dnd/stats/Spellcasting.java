package io.darkcraft.dnd.stats;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Multimap;

import io.darkcraft.dnd.character.data.Attribute;

public class Spellcasting
{
    @JsonProperty
    public Attribute ability;
    @JsonProperty
    public Integer level;
    @JsonProperty
    public Integer dc;
    @JsonProperty
    public Integer attackMod;
    @JsonProperty
    public Map<Integer, Integer> slots;
    @JsonProperty
    public Multimap<Integer, String> spells;
}
