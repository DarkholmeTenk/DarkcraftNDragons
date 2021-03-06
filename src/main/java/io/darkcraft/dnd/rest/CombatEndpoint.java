package io.darkcraft.dnd.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.darkcraft.dnd.combat.CombatSet;
import io.darkcraft.dnd.store.CombatRepository;

@RestController
@RequestMapping("/combat")
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
    public CombatSet saveCombat(@RequestBody CombatSet set)
    {
        if(set.actors == null)
            set.actors = new ArrayList<>();
        if(set.id == null)
            set.created = new Date().getTime();
        set.updated = new Date().getTime();
        return repo.save(set);
    }

    @RequestMapping("/delete")
    public boolean deleteCombat(@RequestParam("id") String id)
    {
        repo.deleteById(id);
        return true;
    }

    @RequestMapping("/get")
    public Optional<CombatSet> getCombat(@RequestParam("name") String id)
    {
        return repo.findById(id);
    }
}
