package io.darkcraft.dnd.combat;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CombatSet
{
    @JsonProperty
    @Id
    public String id;
    @JsonProperty
    public long created;
    @JsonProperty
    public long updated;
    @JsonProperty
    @Indexed(unique=true)
    public String name;
    @JsonProperty
    public int turn;
    @JsonProperty
    public List<CombatSheet> actors;
}
