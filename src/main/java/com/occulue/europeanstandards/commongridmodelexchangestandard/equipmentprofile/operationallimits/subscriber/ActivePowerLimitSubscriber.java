/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits..subscriber;

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
 * Subscriber for ActivePowerLimit related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("activePowerLimit-subscriber")
public class ActivePowerLimitSubscriber extends BaseSubscriber {

	public ActivePowerLimitSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ActivePowerLimit>, ActivePowerLimit> activePowerLimitSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllActivePowerLimitQuery(), 
                		ResponseTypes.multipleInstancesOf(ActivePowerLimit.class),
                		ResponseTypes.instanceOf(ActivePowerLimit.class));
    }

    public SubscriptionQueryResult<ActivePowerLimit, ActivePowerLimit> activePowerLimitSubscribe(@DestinationVariable UUID activePowerLimitId) {
        return queryGateway
                .subscriptionQuery(new FindActivePowerLimitQuery(new LoadActivePowerLimitFilter(activePowerLimitId)), 
                		ResponseTypes.instanceOf(ActivePowerLimit.class),
                		ResponseTypes.instanceOf(ActivePowerLimit.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

