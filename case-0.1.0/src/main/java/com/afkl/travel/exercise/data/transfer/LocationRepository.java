
package com.afkl.travel.exercise.data.transfer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.afkl.travel.exercise.dto.LocationDTO;

public interface LocationRepository extends CrudRepository<LocationDTO, Integer> {

	// @Query("select c from Location l inner join l.translations t where l.id =
	// :locationId and t.language = :language")
	@Query("from LocationDTO l inner join l.translations t where  t.language = ?1")
	Optional<List<LocationDTO>> findByLanguage(String language);
   
	//Iterable<LocationDTO> findAll();
	@Query(value = "SELECT l.id, l.code, l.type, l.longitude, l.latitude, "
			+ "(SELECT code FROM location m where m.id= l.parent_id ) as parent_code, "
			+ "(SELECT type from location m where m.id= l.parent_id ) "
			+ "as parent_type from location l inner join translation t on l.id= t.location where l.id=t.location and t.language = ?1 and l.type=?2 and l.code=?3", nativeQuery = true)
	Optional<LocationDTO> findByLocationByTypeAndCode(String language, String type, String code);

	// @Query(value = , nativeQuery = true );
	@Query(value = "SELECT l.id, l.code, l.type, l.longitude, l.latitude, "
			+ "(SELECT code FROM location m where m.id= l.parent_id ) as parent_code, "
			+ "(SELECT type from location m where m.id= l.parent_id ) "
			+ "as parent_type from location l inner join translation t on l.id= t.location where l.id=t.location and t.language=?1", nativeQuery = true)
	Optional<List<LocationDTO>> findByLanguage2(String language);
}
