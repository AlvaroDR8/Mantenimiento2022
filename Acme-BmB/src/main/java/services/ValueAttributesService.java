package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.ValueAttributes;
import repositories.ValueAttributesRepository;

@Service
@Transactional
public class ValueAttributesService {
	
	// Managed Repository
	
	@Autowired
	private ValueAttributesRepository valueAttributesRepository;
	
	public ValueAttributesService() {
		super();
	}
	
	public ValueAttributes create() {
		ValueAttributes result;
		result = new ValueAttributes();
		return result;
	}
	
	public Collection<ValueAttributes> findAll() {
		Collection<ValueAttributes> result;

		result = valueAttributesRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public ValueAttributes findOne(int valueAttributesId) {
		ValueAttributes result;

		result = valueAttributesRepository.findOne(valueAttributesId);
		Assert.notNull(result);

		return result;
	}
	
	public ValueAttributes save(ValueAttributes valueAttributes) {
		Assert.notNull(valueAttributes);

		ValueAttributes result;
		result = valueAttributesRepository.save(valueAttributes);

		return result;
	}
	
	public void delete(ValueAttributes valueAttributes) {
		Assert.notNull(valueAttributes);
		Assert.isTrue(valueAttributes.getId() != 0);

		valueAttributesRepository.delete(valueAttributes);
	}
}