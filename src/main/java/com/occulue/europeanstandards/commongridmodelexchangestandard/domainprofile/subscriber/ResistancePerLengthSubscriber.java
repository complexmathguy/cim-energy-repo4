/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile..subscriber;

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
 * Subscriber for ResistancePerLength related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("resistancePerLength-subscriber")
public class ResistancePerLengthSubscriber extends BaseSubscriber {

	public ResistancePerLengthSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ResistancePerLength>, ResistancePerLength> resistancePerLengthSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllResistancePerLengthQuery(), 
                		ResponseTypes.multipleInstancesOf(ResistancePerLength.class),
                		ResponseTypes.instanceOf(ResistancePerLength.class));
    }

    public SubscriptionQueryResult<ResistancePerLength, ResistancePerLength> resistancePerLengthSubscribe(@DestinationVariable UUID resistancePerLengthId) {
        return queryGateway
                .subscriptionQuery(new FindResistancePerLengthQuery(new LoadResistancePerLengthFilter(resistancePerLengthId)), 
                		ResponseTypes.instanceOf(ResistancePerLength.class),
                		ResponseTypes.instanceOf(ResistancePerLength.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

