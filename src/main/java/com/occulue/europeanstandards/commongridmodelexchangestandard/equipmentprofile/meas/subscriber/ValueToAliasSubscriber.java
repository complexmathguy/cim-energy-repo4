/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas..subscriber;

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
 * Subscriber for ValueToAlias related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("valueToAlias-subscriber")
public class ValueToAliasSubscriber extends BaseSubscriber {

	public ValueToAliasSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ValueToAlias>, ValueToAlias> valueToAliasSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllValueToAliasQuery(), 
                		ResponseTypes.multipleInstancesOf(ValueToAlias.class),
                		ResponseTypes.instanceOf(ValueToAlias.class));
    }

    public SubscriptionQueryResult<ValueToAlias, ValueToAlias> valueToAliasSubscribe(@DestinationVariable UUID valueToAliasId) {
        return queryGateway
                .subscriptionQuery(new FindValueToAliasQuery(new LoadValueToAliasFilter(valueToAliasId)), 
                		ResponseTypes.instanceOf(ValueToAlias.class),
                		ResponseTypes.instanceOf(ValueToAlias.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

