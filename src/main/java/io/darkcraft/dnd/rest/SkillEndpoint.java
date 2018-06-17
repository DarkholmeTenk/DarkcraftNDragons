package io.darkcraft.dnd.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.darkcraft.dnd.character.data.Skill;

@RestController
@RequestMapping("/skill")
public class SkillEndpoint
{
	@RequestMapping
	public Skill[] getAll()
	{
		return Skill.values();
	}
}
