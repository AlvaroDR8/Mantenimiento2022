package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Attributes;
import repositories.AttributesRepository;

@Service
@Transactional
public class AttributesService {
	
	// Managed Repository
	
	@Autowired
	private AttributesRepository attributesRepository;
	
	public AttributesService() {
		super();
	}
	
	public Attributes create() {
		Attributes result;
		result = new Attributes();
		return result;
	}
	
	public Collection<Attributes> findAll() {
		Collection<Attributes> result;

		result = attributesRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public Attributes findOne(int attributesId) {
		Attributes result;

		result = attributesRepository.findOne(attributesId);
		Assert.notNull(result);

		return result;
	}
	
	public Attributes save(Attributes attributes) {
		Assert.notNull(attributes);

		Attributes result;
		result = attributesRepository.save(attributes);

		return result;
	}
	
	public void delete(Attributes attributes) {
		Assert.notNull(attributes);
		Assert.isTrue(attributes.getId() != 0);

		attributesRepository.delete(attributes);
	}
}