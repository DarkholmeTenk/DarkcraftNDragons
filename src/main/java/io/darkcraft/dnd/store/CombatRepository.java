package io.darkcraft.dnd.store;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.darkcraft.dnd.combat.CombatSet;

public interface CombatRepository extends MongoRepository<CombatSet, String>
{

}
