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
 * Subscriber for ExcPIC related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("excPIC-subscriber")
public class ExcPICSubscriber extends BaseSubscriber {

	public ExcPICSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ExcPIC>, ExcPIC> excPICSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllExcPICQuery(), 
                		ResponseTypes.multipleInstancesOf(ExcPIC.class),
                		ResponseTypes.instanceOf(ExcPIC.class));
    }

    public SubscriptionQueryResult<ExcPIC, ExcPIC> excPICSubscribe(@DestinationVariable UUID excPICId) {
        return queryGateway
                .subscriptionQuery(new FindExcPICQuery(new LoadExcPICFilter(excPICId)), 
                		ResponseTypes.instanceOf(ExcPIC.class),
                		ResponseTypes.instanceOf(ExcPIC.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

