package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import domain.Auditor;
import repositories.AuditorRepository;
import security.UserAccount;

@Service
@Transactional
public class AuditorService {

	// Managed Repository
	@Autowired
	private AuditorRepository auditorRepository;
	
	// Supporting Services

	public AuditorService() {
		super();
	}

	public Auditor create() {
		Auditor result;
		result = new Auditor();

		return result;
	}

	

	public Collection<Auditor> findAll() {
		Collection<Auditor> result;

		result = auditorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	

	public Auditor findOne(int auditorId) {
		Auditor result;

		result = auditorRepository.findOne(auditorId);
		Assert.notNull(result);

		return result;
	}

	

	public Auditor save(Auditor auditor) {
		Assert.notNull(auditor);

		Auditor result;
		result = auditorRepository.save(auditor);

		return result;
	}

	

	public void delete(Auditor auditor) {
		Assert.notNull(auditor);

		Assert.isTrue(auditor.getId() != 0);

		auditorRepository.delete(auditor);
	}

	

	public Collection<Auditor> findByCompanyName(String companyName) {

		Assert.notNull(companyName);
		Collection<Auditor> result;
		result = auditorRepository.findByCompanyName(companyName);

		return result;
	}
	
	public Auditor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Auditor result;

		result = auditorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}

