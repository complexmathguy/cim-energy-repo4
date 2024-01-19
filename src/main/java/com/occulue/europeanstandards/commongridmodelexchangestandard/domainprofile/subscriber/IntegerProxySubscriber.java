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
 * Subscriber for IntegerProxy related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("integerProxy-subscriber")
public class IntegerProxySubscriber extends BaseSubscriber {

	public IntegerProxySubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<IntegerProxy>, IntegerProxy> integerProxySubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllIntegerProxyQuery(), 
                		ResponseTypes.multipleInstancesOf(IntegerProxy.class),
                		ResponseTypes.instanceOf(IntegerProxy.class));
    }

    public SubscriptionQueryResult<IntegerProxy, IntegerProxy> integerProxySubscribe(@DestinationVariable UUID integerProxyId) {
        return queryGateway
                .subscriptionQuery(new FindIntegerProxyQuery(new LoadIntegerProxyFilter(integerProxyId)), 
                		ResponseTypes.instanceOf(IntegerProxy.class),
                		ResponseTypes.instanceOf(IntegerProxy.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}
