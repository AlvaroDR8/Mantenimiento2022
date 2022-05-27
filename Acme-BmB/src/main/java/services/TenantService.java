package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Tenant;
import repositories.TenantRepository;
import security.UserAccount;

@Service
@Transactional
public class TenantService {

	// Managed Repository
	@Autowired
	private TenantRepository tenantRepository;
	
	// Supporting Services

	public TenantService() {
		super();
	}

	public Tenant create() {
		Tenant result;
		result = new Tenant();

		return result;
	}

	

	public Collection<Tenant> findAll() {
		Collection<Tenant> result;

		result = tenantRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	

	public Tenant findOne(int tenantId) {
		Tenant result;

		result = tenantRepository.findOne(tenantId);
		Assert.notNull(result);

		return result;
	}

	

	public Tenant save(Tenant tenant) {
		Assert.notNull(tenant);

		Tenant result;
		result = tenantRepository.save(tenant);

		return result;
	}

	

	public void delete(Tenant tenant) {
		Assert.notNull(tenant);

		Assert.isTrue(tenant.getId() != 0);

		tenantRepository.delete(tenant);
	}
	
	public Tenant findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Tenant result;

		result = tenantRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}

