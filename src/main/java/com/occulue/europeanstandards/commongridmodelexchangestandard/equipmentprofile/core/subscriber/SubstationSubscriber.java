/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.core..subscriber;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.axonframework.messaging.responsetypes.ResponseTypes;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.subscriber.*;

/**
 * Subscriber for Substation related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("substation-subscriber")
public class SubstationSubscriber extends BaseSubscriber {

	public SubstationSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Substation>, Substation> substationSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllSubstationQuery(), 
                		ResponseTypes.multipleInstancesOf(Substation.class),
                		ResponseTypes.instanceOf(Substation.class));
    }

    public SubscriptionQueryResult<Substation, Substation> substationSubscribe(@DestinationVariable UUID substationId) {
        return queryGateway
                .subscriptionQuery(new FindSubstationQuery(new LoadSubstationFilter(substationId)), 
                		ResponseTypes.instanceOf(Substation.class),
                		ResponseTypes.instanceOf(Substation.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

