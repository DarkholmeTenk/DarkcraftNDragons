package io.darkcraft.dnd.character;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

import io.darkcraft.dnd.character.data.Attribute;

public class CharSheet
{
	@Id
	public String id;

	public Map<Attribute, Integer> baseAttributes = new HashMap<>();
}
