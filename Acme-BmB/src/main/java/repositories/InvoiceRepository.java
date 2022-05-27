package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	@Query("select i from Invoice i where i.request.tenant.id = ?1")
	Collection<Invoice> findByTenantId(int tenantId);
	
	@Query("select i from Invoice i where i.request.property.lessor.id = ?1")
	Collection<Invoice> findByLessorId(int lessorId);
}
