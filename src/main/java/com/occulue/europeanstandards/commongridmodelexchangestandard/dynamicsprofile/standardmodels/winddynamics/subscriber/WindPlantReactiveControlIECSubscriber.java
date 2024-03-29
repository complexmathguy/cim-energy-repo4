/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.winddynamics..subscriber;

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
 * Subscriber for WindPlantReactiveControlIEC related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("windPlantReactiveControlIEC-subscriber")
public class WindPlantReactiveControlIECSubscriber extends BaseSubscriber {

	public WindPlantReactiveControlIECSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<WindPlantReactiveControlIEC>, WindPlantReactiveControlIEC> windPlantReactiveControlIECSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllWindPlantReactiveControlIECQuery(), 
                		ResponseTypes.multipleInstancesOf(WindPlantReactiveControlIEC.class),
                		ResponseTypes.instanceOf(WindPlantReactiveControlIEC.class));
    }

    public SubscriptionQueryResult<WindPlantReactiveControlIEC, WindPlantReactiveControlIEC> windPlantReactiveControlIECSubscribe(@DestinationVariable UUID windPlantReactiveControlIECId) {
        return queryGateway
                .subscriptionQuery(new FindWindPlantReactiveControlIECQuery(new LoadWindPlantReactiveControlIECFilter(windPlantReactiveControlIECId)), 
                		ResponseTypes.instanceOf(WindPlantReactiveControlIEC.class),
                		ResponseTypes.instanceOf(WindPlantReactiveControlIEC.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

