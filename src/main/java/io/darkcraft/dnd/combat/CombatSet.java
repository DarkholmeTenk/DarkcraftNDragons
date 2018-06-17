package io.darkcraft.dnd.combat;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CombatSet
{
    @JsonProperty
    @Id
    public String id;
    @JsonProperty
    public int turn;
    @JsonProperty
    public List<CombatSheet> actors;
}
