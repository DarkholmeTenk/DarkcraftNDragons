package io.darkcraft.dnd.external.dnd5eapi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import io.darkcraft.dnd.external.MonsterProvider;
import io.darkcraft.dnd.monster.CreatureType;
import io.darkcraft.dnd.monster.MonsterSheet;
import io.darkcraft.dnd.stats.AbilityScoreBlock;
import io.darkcraft.dnd.stats.Alignment;
import io.darkcraft.dnd.stats.Alignment.Good;
import io.darkcraft.dnd.stats.Alignment.Law;
import io.darkcraft.dnd.stats.Feature;
import io.darkcraft.dnd.stats.Size;
import io.darkcraft.dnd.stats.Speed;

@Component
@Cacheable("dnd5eapi")
public class MonsterAPI implements MonsterProvider
{
	private static final String URL = "http://www.dnd5eapi.co/api/monsters/";

	private static final TypeReference<List<Feature>> featureType = new TypeReference<List<Feature>>(){};

	private static final Pattern PP_PATTERN = Pattern.compile("passive perception (\\d+)");

	@Autowired
	private ObjectMapper mapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(MonsterAPI.class);

	private RestTemplate rest;

	public MonsterAPI()
	{
	    rest = new RestTemplate();
	}

	ObjectMapper getOM()
	{
		ObjectMapper om = mapper.copy();
		om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return om;
	}

	@Override
	public String getIdentifier()
	{
		return "http://www.dnd5eapi.co/";
	}

	@Cacheable("dnd5eapi")
	ResultStructure getBase()
	{
		return rest.getForObject(URL, ResultStructure.class);
	}

	@Override
	public List<String> getMonsterNames()
	{
		return getBase().results.stream()
				.map(o->o.name)
				.collect(Collectors.toList());
	}

	private Integer getInteger(JsonNode node, String id)
	{
	    JsonNode n = node.get(id);
	    if((n != null) && n.isInt())
	        return n.asInt();
	    return null;
	}

	private AbilityScoreBlock getStats(JsonNode node, String suffix)
	{
		Integer str = getInteger(node, "intelligence" + suffix);
		Integer dex = getInteger(node, "dexterity" + suffix);
		Integer con = getInteger(node, "constitution" + suffix);
		Integer inte = getInteger(node, "intelligence" + suffix);
		Integer wis = getInteger(node, "wisdom" + suffix);
		Integer chr = getInteger(node, "charisma" + suffix);
		return new AbilityScoreBlock(str,dex,con,inte,wis,chr);
	}

	private List<String> splitFeatures(JsonNode node, String id)
	{
		String d = node.get(id).asText("");
		return Arrays.stream(d.split(","))
				.map(String::trim)
				.filter(s->!s.isEmpty())
				.collect(Collectors.toList());
	}

	private Speed getSpeed(List<String> speeds)
	{
	    String gr = null;
	    String sw = null;
	    String fl = null;
	    String cl = null;
	    String br = null;
	    for(String s : speeds)
	    {
	        if(s.matches("\\d+.*"))
	            gr = s;
	        else if(s.startsWith("swim "))
	            sw = s.substring(5);
	        else if(s.startsWith("fly "))
	            fl = s.substring(4);
	        else if(s.startsWith("climb "))
	            cl = s.substring(6);
	        else if(s.startsWith("burrow "))
	            br = s.substring(7);
	        else
	            LOGGER.error("Unknown speed type: " + s);
	    }
	    return new Speed(gr, sw, fl, cl, br);
	}

	private Alignment getAlignment(String alignment)
	{
	    String alignmentL = alignment.toLowerCase();
	    if(alignmentL.equals("unaligned"))
	        return null;
	    if(alignmentL.equals("neutral"))
	        return new Alignment(Law.NEUTRAL, Good.NEUTRAL);
	    Law l = Law.NEUTRAL;
	    if(alignmentL.startsWith("lawful"))
	        l = Law.LAWFUL;
	    if(alignmentL.startsWith("chaotic"))
	        l = Law.CHAOTIC;
	    Good g = Good.NEUTRAL;
	    if(alignmentL.endsWith("good"))
	        g = Good.GOOD;
	    if(alignmentL.endsWith("evil"))
	        g = Good.EVIL;
	    return new Alignment(l, g);
	}

	private int getPP(List<String> senses)
	{
	    for(String s : senses)
	    {
	        Matcher m = PP_PATTERN.matcher(s.toLowerCase());
	        if(m.find())
	            return Integer.parseInt(m.group(1));
	    }
	    return 0;
	}

	private MonsterSheet parse(String data)
	{
		ObjectMapper mapper = getOM();
		try
		{
			MonsterSheet sheet = new MonsterSheet();
			sheet.source = getIdentifier();
			JsonNode node = mapper.readTree(data);
			sheet.name = node.get("name").textValue();
			sheet.ac = node.get("armor_class").intValue();
			sheet.cr = node.get("challenge_rating").doubleValue();
			sheet.maxHP = node.get("hit_points").intValue();
			sheet.hitDice = node.get("hit_dice").asText();
			sheet.size = mapper.convertValue(node.get("size"), Size.class);
			sheet.type = mapper.convertValue(node.get("type"), CreatureType.class);
			sheet.speed = getSpeed(splitFeatures(node, "speed"));

			//Stats
			sheet.abilities = getStats(node,"");
			sheet.savingThrows = getStats(node, "_save");

			//Misc
			sheet.languages = splitFeatures(node, "languages");
			sheet.senses = splitFeatures(node, "senses");
			sheet.vulnerabilities = splitFeatures(node, "damage_vulnerabilities");
			sheet.resistances = splitFeatures(node, "damage_resistances");
			sheet.damageImmunities = splitFeatures(node, "damage_immunities");
			sheet.conditionImmunities = splitFeatures(node, "condition_immunities");
			sheet.alignment = getAlignment(node.get("alignment").asText());
			sheet.pp = getPP(sheet.senses);

			//Features
			sheet.actions = mapper.convertValue(node.get("actions"), featureType);
			sheet.specialAbilities = mapper.convertValue(node.get("special_abilities"), featureType);
			sheet.legendaryActions = mapper.convertValue(node.get("legendary_actions"), featureType);
			return sheet;
		}
		catch(Exception e)
		{
			LOGGER.error("Error parsing data: " + data, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<MonsterSheet> getMonster(String name)
	{
	    LOGGER.info("Requesting data for monster: " + name);
		return getBase().results.stream()
			.filter(r->name.equalsIgnoreCase(r.name))
			.findFirst()
			.map(r->rest.getForObject(r.url, String.class))
			.map(this::parse);
	}
}