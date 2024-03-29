/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.statevariablesprofile.statevariables..subscriber;

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
 * Subscriber for SvStatus related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("svStatus-subscriber")
public class SvStatusSubscriber extends BaseSubscriber {

	public SvStatusSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<SvStatus>, SvStatus> svStatusSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllSvStatusQuery(), 
                		ResponseTypes.multipleInstancesOf(SvStatus.class),
                		ResponseTypes.instanceOf(SvStatus.class));
    }

    public SubscriptionQueryResult<SvStatus, SvStatus> svStatusSubscribe(@DestinationVariable UUID svStatusId) {
        return queryGateway
                .subscriptionQuery(new FindSvStatusQuery(new LoadSvStatusFilter(svStatusId)), 
                		ResponseTypes.instanceOf(SvStatus.class),
                		ResponseTypes.instanceOf(SvStatus.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

