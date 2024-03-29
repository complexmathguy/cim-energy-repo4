/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.excitationsystemdynamics..subscriber;

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
 * Subscriber for ExcitationSystemDynamics related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("excitationSystemDynamics-subscriber")
public class ExcitationSystemDynamicsSubscriber extends BaseSubscriber {

	public ExcitationSystemDynamicsSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ExcitationSystemDynamics>, ExcitationSystemDynamics> excitationSystemDynamicsSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllExcitationSystemDynamicsQuery(), 
                		ResponseTypes.multipleInstancesOf(ExcitationSystemDynamics.class),
                		ResponseTypes.instanceOf(ExcitationSystemDynamics.class));
    }

    public SubscriptionQueryResult<ExcitationSystemDynamics, ExcitationSystemDynamics> excitationSystemDynamicsSubscribe(@DestinationVariable UUID excitationSystemDynamicsId) {
        return queryGateway
                .subscriptionQuery(new FindExcitationSystemDynamicsQuery(new LoadExcitationSystemDynamicsFilter(excitationSystemDynamicsId)), 
                		ResponseTypes.instanceOf(ExcitationSystemDynamics.class),
                		ResponseTypes.instanceOf(ExcitationSystemDynamics.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

