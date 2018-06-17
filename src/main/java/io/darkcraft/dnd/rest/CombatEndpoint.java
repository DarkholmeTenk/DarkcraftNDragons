package io.darkcraft.dnd.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.darkcraft.dnd.combat.CombatSet;
import io.darkcraft.dnd.store.CombatRepository;

@RestController
public class CombatEndpoint
{
    @Autowired
    private CombatRepository repo;

    @RequestMapping("/getAllCombats")
    public List<CombatSet> getAllCombats()
    {
        return repo.findAll();
    }

    @RequestMapping("/save")
    public CombatSet saveCombat(CombatSet set)
    {
        return repo.save(set);
    }

    @RequestMapping("/get")
    public Optional<CombatSet> getCombat(String id)
    {
        return repo.findById(id);
    }
}
