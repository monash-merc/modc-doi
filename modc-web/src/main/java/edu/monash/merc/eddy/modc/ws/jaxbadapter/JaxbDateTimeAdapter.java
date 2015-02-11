package edu.monash.merc.eddy.modc.ws.jaxbadapter;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by simonyu on 1/08/2014.
 */
public class JaxbDateTimeAdapter extends XmlAdapter<String, Date> {

    //private static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

   // private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date unmarshal(String strDate) throws Exception {
       return DatatypeConverter.parseDateTime(strDate).getTime();
    }

    @Override
    public String marshal(Date date) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeConverter.printDateTime(calendar);
    }
}
