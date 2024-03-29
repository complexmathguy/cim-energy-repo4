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
 * Subscriber for ExcIEEEST2A related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("excIEEEST2A-subscriber")
public class ExcIEEEST2ASubscriber extends BaseSubscriber {

	public ExcIEEEST2ASubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ExcIEEEST2A>, ExcIEEEST2A> excIEEEST2ASubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllExcIEEEST2AQuery(), 
                		ResponseTypes.multipleInstancesOf(ExcIEEEST2A.class),
                		ResponseTypes.instanceOf(ExcIEEEST2A.class));
    }

    public SubscriptionQueryResult<ExcIEEEST2A, ExcIEEEST2A> excIEEEST2ASubscribe(@DestinationVariable UUID excIEEEST2AId) {
        return queryGateway
                .subscriptionQuery(new FindExcIEEEST2AQuery(new LoadExcIEEEST2AFilter(excIEEEST2AId)), 
                		ResponseTypes.instanceOf(ExcIEEEST2A.class),
                		ResponseTypes.instanceOf(ExcIEEEST2A.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

