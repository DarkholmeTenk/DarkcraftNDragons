package io.darkcraft.dnd.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.darkcraft.dnd.character.QuickSheet;
import io.darkcraft.dnd.character.StatSheet;
import io.darkcraft.dnd.monster.MonsterSheet;
import io.darkcraft.dnd.services.sheet.SheetService;
import io.darkcraft.dnd.stats.SheetType;

@RestController
@RequestMapping("/sheet")
public class SheetEndpoint
{
    @Autowired
    private SheetService sheetService;

    @RequestMapping("/getQuickSheets")
    public List<QuickSheet> getAllQuickSheets()
    {
        return sheetService.getQuickSheets();
    }

    @RequestMapping("/getQuickSheet")
    public Optional<QuickSheet> getQuickSheet(@RequestParam("id") String id)
    {
        return sheetService.getQuickSheet(id);
    }

    @RequestMapping("/saveQuickSheet")
    public QuickSheet saveQuickSheet(QuickSheet sheet)
    {
        return sheetService.saveQuickSheet(sheet);
    }

    @RequestMapping("/getMonsterNames")
    public List<String> getMonsterNames()
    {
        return sheetService.getMonsterNames();
    }

    @RequestMapping("/getMonster")
    public Optional<MonsterSheet> getMonsterSheet(@RequestParam("name") String name)
    {
        return sheetService.getMonsterSheet(name);
    }

    @RequestMapping("getMonsterByID")
    public Optional<MonsterSheet> getMonsterSheetById(@RequestParam("id") String id)
    {
        return sheetService.getMonsterSheetByID(id);
    }

    @RequestMapping("/saveNewMonster")
    public MonsterSheet saveNewMonster(MonsterSheet sheet)
    {
        return sheetService.saveMonsterSheet(sheet);
    }

    @RequestMapping("/getSheet")
    public Optional<? extends StatSheet> getSheet(@RequestParam("type") SheetType type,
            @RequestParam("id") String id)
    {
        return sheetService.getSheet(type, id);
    }
}
