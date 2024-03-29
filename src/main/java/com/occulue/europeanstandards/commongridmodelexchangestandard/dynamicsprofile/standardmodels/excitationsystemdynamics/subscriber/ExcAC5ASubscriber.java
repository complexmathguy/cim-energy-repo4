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
 * Subscriber for ExcAC5A related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("excAC5A-subscriber")
public class ExcAC5ASubscriber extends BaseSubscriber {

	public ExcAC5ASubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ExcAC5A>, ExcAC5A> excAC5ASubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllExcAC5AQuery(), 
                		ResponseTypes.multipleInstancesOf(ExcAC5A.class),
                		ResponseTypes.instanceOf(ExcAC5A.class));
    }

    public SubscriptionQueryResult<ExcAC5A, ExcAC5A> excAC5ASubscribe(@DestinationVariable UUID excAC5AId) {
        return queryGateway
                .subscriptionQuery(new FindExcAC5AQuery(new LoadExcAC5AFilter(excAC5AId)), 
                		ResponseTypes.instanceOf(ExcAC5A.class),
                		ResponseTypes.instanceOf(ExcAC5A.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

