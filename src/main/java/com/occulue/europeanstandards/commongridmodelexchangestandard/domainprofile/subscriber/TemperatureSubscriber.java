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
 * Subscriber for Temperature related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("temperature-subscriber")
public class TemperatureSubscriber extends BaseSubscriber {

	public TemperatureSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Temperature>, Temperature> temperatureSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllTemperatureQuery(), 
                		ResponseTypes.multipleInstancesOf(Temperature.class),
                		ResponseTypes.instanceOf(Temperature.class));
    }

    public SubscriptionQueryResult<Temperature, Temperature> temperatureSubscribe(@DestinationVariable UUID temperatureId) {
        return queryGateway
                .subscriptionQuery(new FindTemperatureQuery(new LoadTemperatureFilter(temperatureId)), 
                		ResponseTypes.instanceOf(Temperature.class),
                		ResponseTypes.instanceOf(Temperature.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

