package io.darkcraft.dnd.combat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.darkcraft.dnd.stats.SheetType;

public class CombatSheet
{
    @JsonProperty
    public SheetType type;
    @JsonProperty
    public String id;
    @JsonProperty
    public int hp;
    @JsonProperty
    public int initiative;
    @JsonProperty
    public String quickName;
    @JsonProperty
    public String sheetName;
    @JsonProperty
    public String notes;
}
