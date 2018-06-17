package io.darkcraft.dnd.services.monster;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.darkcraft.dnd.external.dnd5eapi.MonsterAPI;
import io.darkcraft.dnd.monster.MonsterSheet;
import io.darkcraft.dnd.store.MonsterSheetRepository;

@Service
@Cacheable
public class MonsterService
{
    @Autowired
    private MonsterSheetRepository repo;

    @Autowired
    private MonsterAPI monsterAPI;

    public List<String> getAllNames()
    {
        return Stream.concat(StreamSupport.stream(repo.findAll().spliterator(), false)
                .map(sheet->sheet.name),
                monsterAPI.getMonsterNames().stream())
            .distinct()
            .sorted(String.CASE_INSENSITIVE_ORDER)
            .collect(Collectors.toList());
    }

    public MonsterSheet findByName(String name)
    {
        return repo.findByName(name)
            .orElseGet(()->{
                Optional<MonsterSheet> sheet = monsterAPI.getMonster(name);
                if(sheet.isPresent())
                    return repo.save(sheet.get());
                return null;
            });
    }

    public Optional<MonsterSheet> findByID(String id)
    {
        return repo.findById(id);
    }

    public MonsterSheet saveSheet(MonsterSheet sheet)
    {
        return repo.save(sheet);
    }
}
