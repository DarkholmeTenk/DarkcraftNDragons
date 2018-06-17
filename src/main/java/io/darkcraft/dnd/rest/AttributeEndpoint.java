package io.darkcraft.dnd.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.darkcraft.dnd.character.data.Attribute;

@RestController
@RequestMapping("/attr")
public class AttributeEndpoint
{
	@GetMapping
	public Attribute[] getAll()
	{
		return Attribute.values();
	}
}
