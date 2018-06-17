package io.darkcraft.dnd.store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.darkcraft.dnd.character.QuickSheet;

@Repository
public interface QuickSheetRepository extends MongoRepository<QuickSheet, String>
{

}
