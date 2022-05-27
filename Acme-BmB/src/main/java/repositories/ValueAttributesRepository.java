package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ValueAttributes;

@Repository
public interface ValueAttributesRepository extends JpaRepository<ValueAttributes, Integer>{

}
