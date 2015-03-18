/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.common.util;

import edu.monash.merc.eddy.modc.common.Exception.MException;
import org.apache.commons.lang.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 11/09/2014
 */
public class MDUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String YYYY_DATE_FORMAT = "yyyy";

    private static final String YYYYMMDD_DATE_FORMAT = "yyyy-MM-dd";

    private static final String FIRST_TIME_OF_DAY = " 00:00:00";

    public static Date formatDate(final String dateStr) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            throw new MException(e.getMessage());
        }
        return date;
    }

    public static String yyyyDateFormat(Date date) {
        SimpleDateFormat yyyydf = new SimpleDateFormat(YYYY_DATE_FORMAT);
        return yyyydf.format(date);
    }

    public static String yyyyMMddDateFormat(Date date) {
        SimpleDateFormat yyyymmdddf = new SimpleDateFormat(YYYYMMDD_DATE_FORMAT);
        return yyyymmdddf.format(date);
    }

    public static String replaceURLAmpsands(String url) {
        return StringUtils.replace(url, "&", "&amp;");
    }

    public static String replaceAmpEncode(String url) {
        return StringUtils.replace(url, "&amp;", "&");
    }

    public static String pathEncode(String fileName) {
        try {
            return URLEncoder.encode(fileName, "UTF-8");
        } catch (Exception ex) {
            throw new MException(ex);
        }
    }

    public static String pathDecode(String path) {
        try {
            return URLDecoder.decode(path, "UTF-8");
        } catch (Exception ex) {
            throw new MException(ex);
        }
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static Date getToday() {
        Date date = GregorianCalendar.getInstance().getTime();
        String endtimeStr = MDUtils.yyyyMMddDateFormat(date);
        return formatDate(endtimeStr + FIRST_TIME_OF_DAY);
    }


    public static void main(String[] args) {
        String url = "https://platforms.monash.edu/eresearch/index.php?option=com_content&amp;view=category&amp;layout=blog&amp;id=23&amp;Itemid=196";

        System.out.println(" url " + MDUtils.replaceAmpEncode(url));
    }
}
