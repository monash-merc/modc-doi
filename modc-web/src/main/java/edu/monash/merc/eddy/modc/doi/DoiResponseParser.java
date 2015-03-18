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

package edu.monash.merc.eddy.modc.doi;

import edu.monash.merc.eddy.modc.common.exception.MXmlParseException;
import edu.monash.merc.eddy.modc.common.util.MDUtils;
import org.apache.commons.lang.StringUtils;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.StringReader;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 11/09/2014
 */
public class DoiResponseParser {

    private static final String RESPONSE_PATH = "//response";

    private static final String TYPE_ATTR = "type";

    private static final String NODE_RESPONSE_CODE = "responsecode";

    private static final String NODE_MESSAGE = "message";

    private static final String NODE_DOI = "doi";

    private static final String NODE_URL = "url";

    private static final String NODE_APP_ID = "app_id";

    private static final String NODE_VERBOSE_MESSAGE = "verbosemessage";

    private static SAXBuilder parser = new SAXBuilder();

    private static Document parseXML(String responseXML) {
        StringReader reader = null;
        try {
            reader = new StringReader(responseXML);
            InputSource inputSource = new InputSource(reader);
            return parser.build(inputSource);
        } catch (Exception e) {
            throw new MXmlParseException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static DoiResponse parseDOIXML(String responseXML) {
        String encodedResponseXML = MDUtils.replaceURLAmpsands(responseXML);
        Document doc = parseXML(encodedResponseXML);
        DoiResponse doiResponse = new DoiResponse();
        try {
            JDOMXPath responsePath = new JDOMXPath(RESPONSE_PATH);
            Element responseEl = (Element) responsePath.selectSingleNode(doc);

            Attribute typeAttr = responseEl.getAttribute(TYPE_ATTR);
            //set response type
            doiResponse.setType(typeAttr.getValue());

            Element responseCodeEl = responseEl.getChild(NODE_RESPONSE_CODE);
            //set response code
            doiResponse.setResponseCode(responseCodeEl.getValue());

            Element messageEl = responseEl.getChild(NODE_MESSAGE);
            //set message
            doiResponse.setMessage(messageEl.getValue());

            Element doiEl = responseEl.getChild(NODE_DOI);
            //set doi
            doiResponse.setDoi(doiEl.getValue());

            Element urlEl = responseEl.getChild(NODE_URL);
            //set url
            String urlVale = urlEl.getValue();
            if (StringUtils.isNotBlank(urlVale)) {
                urlVale = MDUtils.replaceAmpEncode(urlVale);
            }
            doiResponse.setUrl(urlVale);

            Element appIdEl = responseEl.getChild(NODE_APP_ID);
            //set appId
            doiResponse.setAppId(appIdEl.getValue());

            Element verboseMessageEl = responseEl.getChild(NODE_VERBOSE_MESSAGE);
            //set verbose message
            doiResponse.setVerboseMessage(verboseMessageEl.getValue());

        } catch (Exception ex) {
            throw new MXmlParseException(ex);
        }
        return doiResponse;
    }

    public static void main(String[] args) {

        String reesponseXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response type=\"success\">\t<responsecode>MT001</responsecode>\t<message>DOI 10.5072/20/541158418D476 was successfully minted.</message>\t<doi>10.5072/20/541158418D476</doi>\t<url>https://platforms.monash.edu/eresearch</url>\t<app_id>4fb08353943adf1f733c528c14293db711a5b03d</app_id>\t<verbosemessage>OK</verbosemessage></response>";

        DoiResponse response = DoiResponseParser.parseDOIXML(reesponseXML);

        System.out.println("type : " + response.getType());
        System.out.println("responsecode : " + response.getResponseCode());
        System.out.println("message : " + response.getMessage());
        System.out.println("doi : " + response.getDoi());
        System.out.println("url : " + response.getUrl());
        System.out.println("appId : " + response.getAppId());
        System.out.println("verbose message : " + response.getVerboseMessage());

    }
}
