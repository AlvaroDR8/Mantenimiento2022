package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Invoice;
import repositories.InvoiceRepository;

@Service
@Transactional
public class InvoiceService {
	
	// Managed Repository
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public InvoiceService() {
		super();
	}
	
	public Invoice create() {
		Invoice result;
		result = new Invoice();
		return result;
	}
	
	public Collection<Invoice> findAll() {
		Collection<Invoice> result;

		result = invoiceRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public Invoice findOne(int invoiceId) {
		Invoice result;

		result = invoiceRepository.findOne(invoiceId);
		Assert.notNull(result);

		return result;
	}
	
	public Invoice save(Invoice invoice) {
		Assert.notNull(invoice);

		Invoice result;
		result = invoiceRepository.save(invoice);

		return result;
	}
	
	public void delete(Invoice invoice) {
		Assert.notNull(invoice);
		Assert.isTrue(invoice.getId() != 0);

		invoiceRepository.delete(invoice);
	}
	
	public Collection<Invoice> findByTenantId(int tenantId) {
		Assert.notNull(tenantId);

		Collection<Invoice> result;

		result = invoiceRepository.findByTenantId(tenantId);

		return result;
	}
	
	public Collection<Invoice> findByLessorId(int lessorId) {
		Assert.notNull(lessorId);

		Collection<Invoice> result;

		result = invoiceRepository.findByLessorId(lessorId);

		return result;
	}
}