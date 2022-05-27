package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{

	@Query("select p from Property p where p.address LIKE %?1%")
	Collection<Property> findByProvince(String province);
	
	@Query("select p from Property p where p.lessor.id = ?1")
	Collection<Property> findByLessorId(int lessorId);
}
