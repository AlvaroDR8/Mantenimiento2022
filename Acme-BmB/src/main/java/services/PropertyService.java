package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Property;
import repositories.PropertyRepository;

@Service
@Transactional
public class PropertyService {
	
		// Managed Repository

		@Autowired
		private PropertyRepository propertyRepository;

		// Supporting Services


		public PropertyService() {
			super();
		}
		
		public Property create() {
			Property result;

			result = new Property();
			
			return result;
		}
		
		public Collection<Property> findAll() {
			Collection<Property> result;
			
			Assert.notNull(this.propertyRepository);
			result = this.propertyRepository.findAll();
			Assert.notNull(result);
			
			return result;
		}
		
		public Property findOne(final int propertyId) {
			Property result;
			
			result = this.propertyRepository.findOne(propertyId);
			
			return result;
		}
		
		public Property save(final Property property) {
			assert property != null;
			Property result;
			
			result = this.propertyRepository.save(property);
			return result;
		}
		
		public void delete(final Property property) {
			assert property != null;
			assert property.getId() != 0;
			
			Assert.isTrue(this.propertyRepository.exists(property.getId()));
			
			this.propertyRepository.delete(property);
		}
		
		public Collection<Property> findByProvince(String province) {
			Assert.notNull(province);
			
			Collection<Property> result;
			result = this.propertyRepository.findByProvince(province);
			
			return result;
		}
		
		public Collection<Property> findByLessorId(int lessorId) {
			Assert.notNull(lessorId);
			
			Collection<Property> result;
			result = this.propertyRepository.findByLessorId(lessorId);
			
			return result;
		}
}
