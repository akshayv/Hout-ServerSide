package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JaxbDateAdapter extends XmlAdapter<String,Date> {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

	@Override
	public String marshal(Date date) throws Exception {
		String result = null;

		if (date != null) {
			DateFormat simpleDateFormat = dateFormat;
			result = simpleDateFormat.format(date);
		}

		return result;
	}

	@Override
	public Date unmarshal(String string) throws Exception {
        Date result = null;

        if (!string.equals("")) {
			DateFormat dateFormatter = dateFormat;
			ParsePosition pp = new ParsePosition(0);
            result = dateFormatter.parse(string, pp);
		}

        return result;
	}
}