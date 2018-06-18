package io.darkcraft.dnd.character.data;

public enum Attribute
{
	STR("Strength"),
	DEX("Dexterity"),
	CON("Constitution"),
	INT("Intelligence"),
	WIS("Wisdom"),
	CHR("Charisma");
	
	private final String displayName;
    Attribute(String displayName)
    {
        this.displayName = displayName;
    }
	
	public static Attribute get(String name)
	{
	    for(Attribute a : values())
	    {
	        if(a.name().equalsIgnoreCase(name) || a.displayName.equalsIgnoreCase(name))
	            return a;
	    }
	    throw new IllegalStateException("No attribute found for: " + name);
	}
}
