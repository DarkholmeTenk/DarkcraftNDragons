package io.darkcraft.dnd.store;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.darkcraft.dnd.character.CharSheet;

@Repository
public interface CharSheetRepository extends MongoRepository<CharSheet, String>
{
	@Override
	public Optional<CharSheet> findById(String id);
}
