package edu.monash.merc.eddy.modc.web.support;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by simonyu on 6/08/2014.
 */
public class DateTypeEditor extends PropertyEditorSupport {

    private static final DateFormat TIMESTAMP_M_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final DateFormat DATE_YYYY_MM_DD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final DateFormat DATE_YYYY_FORMAT = new SimpleDateFormat("yyyy");



    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? TIMESTAMP_FORMAT.format(value) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.trim();
        if (!StringUtils.hasText(text)) {
            setValue(null);
            return;
        }
        try {
            if (text.length() == 4) {
                setValue(new Date(DATE_YYYY_FORMAT.parse(text).getTime()));
            } else if (text.length() > 4 && text.length() <= 10) {
                setValue(new Date(DATE_YYYY_MM_DD_FORMAT.parse(text).getTime()));
            } else {
                setValue(new Timestamp(TIMESTAMP_FORMAT.parse(text).getTime()));
            }
        } catch (ParseException ex) {
            IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
            iae.initCause(ex);
            throw iae;
        }
    }

    public static void main(String args[]) throws Exception {

        String date = "2014";
        System.out.println(new Date(DATE_YYYY_FORMAT.parse(date).getTime()));
        String date2 = "2014-11-13 14:04:53.904";
        System.out.println(new Date(TIMESTAMP_FORMAT.parse(date2).getTime()));
    }
}
