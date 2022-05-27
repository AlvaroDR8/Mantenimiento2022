package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Request;
import repositories.RequestRepository;

@Service
@Transactional
public class RequestService {

	// Managed Repository

	@Autowired
	private RequestRepository requestRepository;

	// Supporting Services

	
	public RequestService() {
		super();
	}
	
	public Request create() {
		Request result;
		result = new Request();
		return result;
	}
	
	public Collection<Request> findAll() {
		Collection<Request> result;

		result = requestRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public Request findOne(int requestId) {
		Request result;

		result = requestRepository.findOne(requestId);
		Assert.notNull(result);

		return result;
	}
	
	public Request save(Request request) {
		Assert.notNull(request);

		Request result;
		result = requestRepository.save(request);

		return result;
	}
	
	public void delete(Request request) {
		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);

		requestRepository.delete(request);
	}
	
	public Collection<Request> findByLessorId(int lessorId) {
		Assert.notNull(lessorId);

		Collection<Request> result;

		result = requestRepository.findByLessorId(lessorId);

		return result;
	}
	
	public Collection<Request> findByTenantId(int tenantId) {
		Assert.notNull(tenantId);

		Collection<Request> result;

		result = requestRepository.findByTenantId(tenantId);

		return result;
	}

}
