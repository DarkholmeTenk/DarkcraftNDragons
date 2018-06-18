package io.darkcraft.dnd.external.dnd5eapi;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import io.darkcraft.dnd.character.data.Attribute;
import io.darkcraft.dnd.stats.Spellcasting;

final class SpellcastingInfoGrabber
{
    private SpellcastingInfoGrabber() {}
    private static final Pattern LEVEL = Pattern.compile("(\\d+)\\S\\S[-\\s]level spellcaster", CASE_INSENSITIVE);
    private static final Pattern SPELL_SAVE_DC = Pattern.compile("spell save DC (\\d+)", CASE_INSENSITIVE);
    private static final Pattern SPELL_MOD = Pattern.compile("([+-]?\\d+) to hit", CASE_INSENSITIVE);
    private static final Pattern ABILITY = Pattern.compile("(?:spellcasting|spell casting) ability is (\\S+)", CASE_INSENSITIVE);
    private static final Pattern CANTRIPS = Pattern.compile("cantrips(?:.*?): (.*?)\\n", CASE_INSENSITIVE);
    
    static int getLevel(String data)
    {
        Matcher m = LEVEL.matcher(data);
        if(m.find())
            return Integer.parseInt(m.group(1));
        throw new IllegalStateException("Can't find spell caster level");

    }
    
    static int getSaveDC(String data)
    {
        Matcher m = SPELL_SAVE_DC.matcher(data);
        if(m.find())
            return Integer.parseInt(m.group(1));
        throw new IllegalStateException("Can't find spell save DC");
    }
    
    static int getSpellAttackMod(String data)
    {
        Matcher m = SPELL_MOD.matcher(data);
        if(m.find())
            return Integer.parseInt(m.group(1));
        throw new IllegalStateException("Can't find spell attack mod");
    }
    
    static Attribute getAttribute(String data)
    {
        Matcher m = ABILITY.matcher(data);
        if(m.find())
        {
            return Attribute.get(m.group(1));
        }
        throw new IllegalStateException("Can't find spell attribute"); 
    }
    
    static Map<Integer, Integer> getSlots(String data)
    {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int i = 1; i < 10; i++)
        {
            Pattern p = Pattern.compile("(?:"+i+"\\S\\S)(?:-|\\s)level\\s\\((\\d+) slots?\\)", CASE_INSENSITIVE);
            Matcher m = p.matcher(data);
            if(m.find())
                map.put(i, Integer.parseInt(m.group(1)));
        }
        return map;
    }
    
    private static Collection<String> splitSpells(String spells)
    {
        return Arrays.stream(spells.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
    
    static Multimap<Integer, String> getSpells(String data)
    {
        Multimap<Integer, String> map = TreeMultimap.create();
        
        {
            Matcher m = CANTRIPS.matcher(data);
            if(m.find())
                map.putAll(0, splitSpells(m.group(1)));
        }
        for(int i = 1; i < 10; i++)
        {
            Pattern p = Pattern.compile(i+"\\S\\S[-\\s]level\\s\\(\\d+ slots?\\): (.*?)$", Pattern.MULTILINE | CASE_INSENSITIVE);
            Matcher m = p.matcher(data);
            if(m.find())
                map.putAll(i, splitSpells(m.group(1)));
        }
        return map;
    }
    
    public static Spellcasting getInfo(String data)
    {
        Spellcasting sc = new Spellcasting();
        sc.level = getLevel(data);
        sc.dc = getSaveDC(data);
        sc.attackMod = getSpellAttackMod(data);
        sc.ability = getAttribute(data);
        sc.slots = getSlots(data);
        sc.spells = getSpells(data);
        return sc;
    }
}
