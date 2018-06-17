package io.darkcraft.dnd.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.darkcraft.dnd.character.CharSheet;
import io.darkcraft.dnd.character.data.Attribute;
import io.darkcraft.dnd.external.dnd5eapi.MonsterAPI;
import io.darkcraft.dnd.monster.MonsterSheet;
import io.darkcraft.dnd.store.CharSheetRepository;

@RestController
@RequestMapping("/debug")
public class DebugEndpoint
{
	@Autowired
	private CharSheetRepository repo;

	@Autowired
	private MonsterAPI api;

	@RequestMapping("/save")
	public void save()
	{
		CharSheet cs = new CharSheet();
		HashMap<Attribute, Integer> tempMap = new HashMap<>();
		tempMap.put(Attribute.STR, 5);
		cs.baseAttributes.putAll(tempMap);
		repo.save(cs);
	}

	@RequestMapping("/get")
	public List<CharSheet> get()
	{
		return repo.findAll();
	}

	@RequestMapping("/listMonsters")
	public List<String> listMonsters()
	{
		return api.getMonsterNames();
	}

	@RequestMapping("/getMonster")
	public MonsterSheet getMonster(@RequestParam("name") String name)
	{
		return api.getMonster(name).orElse(null);
	}
}
