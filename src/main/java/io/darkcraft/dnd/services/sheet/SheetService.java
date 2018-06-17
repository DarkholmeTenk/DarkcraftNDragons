package io.darkcraft.dnd.services.sheet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.darkcraft.dnd.character.QuickSheet;
import io.darkcraft.dnd.character.StatSheet;
import io.darkcraft.dnd.monster.MonsterSheet;
import io.darkcraft.dnd.services.monster.MonsterService;
import io.darkcraft.dnd.stats.SheetType;
import io.darkcraft.dnd.store.QuickSheetRepository;

@Component
public class SheetService
{
    @Autowired
    private MonsterService monster;

    @Autowired
    private QuickSheetRepository qsRepo;

    public Optional<? extends StatSheet> getSheet(SheetType type, String id)
    {
        switch(type)
        {
        case MONSTER: return monster.findByID(id);
        case QUICK: return qsRepo.findById(id);
        }
        throw new RuntimeException("Unavailable sheet type: " + type);
    }

    public List<QuickSheet> getQuickSheets()
    {
        return qsRepo.findAll();
    }

    public Optional<QuickSheet> getQuickSheet(String id)
    {
        return qsRepo.findById(id);
    }

    public QuickSheet saveQuickSheet(QuickSheet sheet)
    {
        return qsRepo.save(sheet);
    }

    public List<String> getMonsterNames()
    {
        return monster.getAllNames();
    }

    public Optional<MonsterSheet> getMonsterSheet(String name)
    {
        return Optional.ofNullable(monster.findByName(name));
    }

    public Optional<MonsterSheet> getMonsterSheetByID(String id)
    {
        return monster.findByID(id);
    }

    public MonsterSheet saveMonsterSheet(MonsterSheet sheet)
    {
        return monster.saveSheet(sheet);
    }
}
