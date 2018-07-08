package io.darkcraft.dnd.external;

import java.util.List;
import java.util.Optional;

import io.darkcraft.dnd.monster.MonsterSheet;

public interface SpellProvider
{
	public String getIdentifier();

	public List<String> getMonsterNames();

	public Optional<MonsterSheet> getMonster(String name);
}
