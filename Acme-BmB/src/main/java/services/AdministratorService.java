package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrator;
import repositories.AdministratorRepository;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	// Managed Repository
	@Autowired
	private AdministratorRepository administratorRepository;
	
	// Supporting Services

	public AdministratorService() {
		super();
	}

	public Administrator create() {
		Administrator result;
		result = new Administrator();

		return result;
	}

	

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = administratorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	

	public Administrator findOne(int administratorId) {
		Administrator result;

		result = administratorRepository.findOne(administratorId);
		Assert.notNull(result);

		return result;
	}

	

	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);

		Administrator result;
		result = administratorRepository.save(administrator);

		return result;
	}

	

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);

		Assert.isTrue(administrator.getId() != 0);

		administratorRepository.delete(administrator);
	}
	
	public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}

