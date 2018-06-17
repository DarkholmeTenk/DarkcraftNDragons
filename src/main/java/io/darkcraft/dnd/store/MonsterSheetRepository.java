package io.darkcraft.dnd.store;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.darkcraft.dnd.monster.MonsterSheet;

@Repository
public interface MonsterSheetRepository extends MongoRepository<MonsterSheet, String>
{
    @Override
    Optional<MonsterSheet> findById(String id);

    Optional<MonsterSheet> findByName(String name);
}
