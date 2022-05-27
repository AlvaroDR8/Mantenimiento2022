package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Attributes;

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Integer>{

}
