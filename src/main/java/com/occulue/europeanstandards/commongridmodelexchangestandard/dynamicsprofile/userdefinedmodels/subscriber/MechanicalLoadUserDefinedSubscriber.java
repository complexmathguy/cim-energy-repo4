/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels..subscriber;

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
 * Subscriber for MechanicalLoadUserDefined related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("mechanicalLoadUserDefined-subscriber")
public class MechanicalLoadUserDefinedSubscriber extends BaseSubscriber {

	public MechanicalLoadUserDefinedSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<MechanicalLoadUserDefined>, MechanicalLoadUserDefined> mechanicalLoadUserDefinedSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllMechanicalLoadUserDefinedQuery(), 
                		ResponseTypes.multipleInstancesOf(MechanicalLoadUserDefined.class),
                		ResponseTypes.instanceOf(MechanicalLoadUserDefined.class));
    }

    public SubscriptionQueryResult<MechanicalLoadUserDefined, MechanicalLoadUserDefined> mechanicalLoadUserDefinedSubscribe(@DestinationVariable UUID mechanicalLoadUserDefinedId) {
        return queryGateway
                .subscriptionQuery(new FindMechanicalLoadUserDefinedQuery(new LoadMechanicalLoadUserDefinedFilter(mechanicalLoadUserDefinedId)), 
                		ResponseTypes.instanceOf(MechanicalLoadUserDefined.class),
                		ResponseTypes.instanceOf(MechanicalLoadUserDefined.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

