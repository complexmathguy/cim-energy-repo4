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
 * Subscriber for AccumulatorReset related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("accumulatorReset-subscriber")
public class AccumulatorResetSubscriber extends BaseSubscriber {

	public AccumulatorResetSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<AccumulatorReset>, AccumulatorReset> accumulatorResetSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllAccumulatorResetQuery(), 
                		ResponseTypes.multipleInstancesOf(AccumulatorReset.class),
                		ResponseTypes.instanceOf(AccumulatorReset.class));
    }

    public SubscriptionQueryResult<AccumulatorReset, AccumulatorReset> accumulatorResetSubscribe(@DestinationVariable UUID accumulatorResetId) {
        return queryGateway
                .subscriptionQuery(new FindAccumulatorResetQuery(new LoadAccumulatorResetFilter(accumulatorResetId)), 
                		ResponseTypes.instanceOf(AccumulatorReset.class),
                		ResponseTypes.instanceOf(AccumulatorReset.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}
