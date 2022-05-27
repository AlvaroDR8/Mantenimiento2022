package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Audit;
import repositories.AuditRepository;

@Service
@Transactional
public class AuditService {
	
	// Managed Repository
	
	@Autowired
	private AuditRepository auditRepository;
	
	public AuditService() {
		super();
	}
	public Audit create() {
		Audit result;
		result = new Audit();
		return result;
	}
	public Collection<Audit> findAll() {
		Collection<Audit> result;

		result = auditRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public Audit findOne(int auditId) {
		Audit result;

		result = auditRepository.findOne(auditId);
		Assert.notNull(result);

		return result;
	}
	
	public Audit save(Audit audit) {
		Assert.notNull(audit);

		Audit result;
		result = auditRepository.save(audit);

		return result;
	}
	
	public void delete(Audit audit) {
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);

		auditRepository.delete(audit);
	}
}
