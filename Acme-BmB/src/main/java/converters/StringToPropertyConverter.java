package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PropertyRepository;
import domain.Property;

@Component
@Transactional
public class StringToPropertyConverter implements Converter<String, Property> {

	@Autowired
	PropertyRepository	propertyRepository;


	@Override
	public Property convert(final String text) {
		Property result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.propertyRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}