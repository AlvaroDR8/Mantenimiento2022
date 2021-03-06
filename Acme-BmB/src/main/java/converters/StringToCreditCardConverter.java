package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CreditCard;

@Component
@Transactional
public class StringToCreditCardConverter implements Converter<String, CreditCard> {


	@Override
	public CreditCard convert(final String text) {
		CreditCard result;
		String parts[];

		if (text == null)
			result = null;
		else {
			try {
				parts = text.split("\\|");
				result = new CreditCard();
				result.setOwnerName(URLDecoder.decode(parts[0], "UTF-8"));
				result.setCardNumber(URLDecoder.decode(parts[1], "UTF-8"));
				result.setCompany(URLDecoder.decode(parts[2], "UTF-8"));
				result.setCaducityMonth(Integer.valueOf(URLDecoder.decode(parts[3], "UTF-8")));
				result.setCaducityYear(Integer.valueOf(URLDecoder.decode(parts[4], "UTF-8")));
				result.setCvv(Integer.valueOf(URLDecoder.decode(parts[5], "UTF-8")));
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		}

		return result;
	}

}