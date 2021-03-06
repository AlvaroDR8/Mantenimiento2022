package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TenantRepository;
import domain.Tenant;

@Component
@Transactional
public class StringToTenantConverter implements Converter<String, Tenant> {

	@Autowired
	TenantRepository	tenantRepository;


	@Override
	public Tenant convert(final String text) {
		Tenant result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.tenantRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}