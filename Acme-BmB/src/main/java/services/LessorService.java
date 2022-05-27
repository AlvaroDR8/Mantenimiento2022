package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Lessor;
import repositories.LessorRepository;
import security.UserAccount;

@Service
@Transactional
public class LessorService {

	// Managed Repository
	@Autowired
	private LessorRepository lessorRepository;
	
	// Supporting Services

	public LessorService() {
		super();
	}

	public Lessor create() {
		Lessor result;
		result = new Lessor();

		return result;
	}

	

	public Collection<Lessor> findAll() {
		Collection<Lessor> result;

		result = lessorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	

	public Lessor findOne(int lessorId) {
		Lessor result;

		result = lessorRepository.findOne(lessorId);
		Assert.notNull(result);

		return result;
	}

	

	public Lessor save(Lessor lessor) {
		Assert.notNull(lessor);

		Lessor result;
		result = lessorRepository.save(lessor);

		return result;
	}

	

	public void delete(Lessor lessor) {
		Assert.notNull(lessor);

		Assert.isTrue(lessor.getId() != 0);

		lessorRepository.delete(lessor);
	}
	
	public Lessor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Lessor result;

		result = lessorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}

