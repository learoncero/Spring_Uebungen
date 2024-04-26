
package com.example.accessingmongodbdatarest;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Um POST zu machen: POST http://localhost:5001/people
// Um die Funktion findByLastName zu verwenden: GET http://localhost:5001/people/search/findByLastName?name=Roncero
// Um alle möglichen Suchen angezeigt zu bekommen: GET http://localhost:5001/people/search
// Um alle Personen anzuzeigen: GET http://localhost:5001/people
// Um eine Person anzuzeigen mit HATEOS Link: GET http://localhost:5001/people/5e7b3b3b7f3b3b3b3b3b3b3b


@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {
	List<Person> findByLastName(@Param("name") String name);

}
