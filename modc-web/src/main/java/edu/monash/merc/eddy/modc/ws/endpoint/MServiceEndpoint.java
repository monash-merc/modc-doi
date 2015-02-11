package edu.monash.merc.eddy.modc.ws.endpoint;

import edu.monash.merc.eddy.modc.ws.model.*;
import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * Created by simonyu on 1/08/2014.
 */
@Endpoint
public class MServiceEndpoint {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private final String SERVICE_NS = "http://merc.monash.edu/ws/schema/mds";

    private final ObjectFactory JAXB_OBJECT_FACTORY = new ObjectFactory();

    @PayloadRoot(localPart = "WPublishRequest", namespace = SERVICE_NS)
    @ResponsePayload
    public WPublishResponse publishMd(@RequestPayload WPublishRequest publishRequest) {
        return processRequest(publishRequest);
    }

    public WPublishResponse processRequest(WPublishRequest publishRequest) {

        WCollection collection = publishRequest.getCollection();
        String collectionId = collection.getKey();

        logger.info("=== created date : " + collection.getCreatedDate());
        logger.info("=== end date : " + collection.getEndDate());
        logger.info("=== Received MDService request");

        List<WParty> parties = collection.getParty();

        for (WParty p : parties) {
            WGroup group = p.getGroup();
            WPerson person = p.getPerson();
            if (group != null) {
                logger.info("Party -- group : " + group.getName());
            }
            if (person != null) {
                logger.info("Party -- person : " + person.getFirstName() + " " + person.getLastName());
            }
        }

        WPublishResponse response = JAXB_OBJECT_FACTORY.createWPublishResponse();

        response.setCode(WPublishResponseCode.SUCCESS);
        response.setMessage("Published Collection is successful.");
        WPublishResponseResult result = new WPublishResponseResult();
        //collection response
        WCollecionResponse collecionResponse = new WCollecionResponse();
        collecionResponse.setKey(collectionId);

        WIdentifier identifier = new WIdentifier();
        identifier.setType(WIdentifierType.HANDLE);

        identifier.setValue("200.1.100/test123");

        collecionResponse.getIdentifier().add(identifier);

        for (WParty p : parties) {

            WPublishPartyResponse partyResponse = null;
            String pkey = p.getKey();
            WGroup group = p.getGroup();
            WPartyRelationType relation = p.getRelation();
            if (group != null) {
                partyResponse = new WPublishPartyResponse();
                partyResponse.setKey(pkey);

                WIdentifier pIdentifier = new WIdentifier();

                pIdentifier.setType(WIdentifierType.LOCAL);
                pIdentifier.setValue("TEST-G-" + pkey);
                partyResponse.getIdentifier().add(pIdentifier);
                partyResponse.setRelation(relation);

            }

            WPerson person = p.getPerson();
            if (person != null) {
                partyResponse = new WPublishPartyResponse();
                partyResponse.setKey(pkey);

                WIdentifier pIdentifier = new WIdentifier();

                pIdentifier.setType(WIdentifierType.LOCAL);
                pIdentifier.setValue("TEST-P-" + pkey);
                partyResponse.getIdentifier().add(pIdentifier);
                partyResponse.setRelation(relation);
            }
            if (partyResponse != null) {
                collecionResponse.getParty().add(partyResponse);
            }

        }


        result.setCollection(collecionResponse);

        response.setResult(result);

        return response;
    }
}
