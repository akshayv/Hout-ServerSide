package com.hout.web.api.marshaller;

import org.jboss.resteasy.spi.StringParameterUnmarshaller;
import org.jboss.resteasy.util.FindAnnotation;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements StringParameterUnmarshaller<Date> {
    private DateFormat format;

    public void setAnnotations(Annotation[] annotations) {
        format = FindAnnotation.findAnnotation(annotations, DateFormat.class);
    }

    public Date fromString(String str) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format.value(), new Locale("sv"));
            return formatter.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}