/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardinterconnections..subscriber;

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
 * Subscriber for Unresolvedname related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("unresolvedname-subscriber")
public class UnresolvednameSubscriber extends BaseSubscriber {

	public UnresolvednameSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Unresolvedname>, Unresolvedname> unresolvednameSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllUnresolvednameQuery(), 
                		ResponseTypes.multipleInstancesOf(Unresolvedname.class),
                		ResponseTypes.instanceOf(Unresolvedname.class));
    }

    public SubscriptionQueryResult<Unresolvedname, Unresolvedname> unresolvednameSubscribe(@DestinationVariable UUID unresolvednameId) {
        return queryGateway
                .subscriptionQuery(new FindUnresolvednameQuery(new LoadUnresolvednameFilter(unresolvednameId)), 
                		ResponseTypes.instanceOf(Unresolvedname.class),
                		ResponseTypes.instanceOf(Unresolvedname.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

