/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.extension..subscriber;

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
 * Subscriber for ENTSOETopologicalNode related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("eNTSOETopologicalNode-subscriber")
public class ENTSOETopologicalNodeSubscriber extends BaseSubscriber {

	public ENTSOETopologicalNodeSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ENTSOETopologicalNode>, ENTSOETopologicalNode> eNTSOETopologicalNodeSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllENTSOETopologicalNodeQuery(), 
                		ResponseTypes.multipleInstancesOf(ENTSOETopologicalNode.class),
                		ResponseTypes.instanceOf(ENTSOETopologicalNode.class));
    }

    public SubscriptionQueryResult<ENTSOETopologicalNode, ENTSOETopologicalNode> eNTSOETopologicalNodeSubscribe(@DestinationVariable UUID eNTSOETopologicalNodeId) {
        return queryGateway
                .subscriptionQuery(new FindENTSOETopologicalNodeQuery(new LoadENTSOETopologicalNodeFilter(eNTSOETopologicalNodeId)), 
                		ResponseTypes.instanceOf(ENTSOETopologicalNode.class),
                		ResponseTypes.instanceOf(ENTSOETopologicalNode.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

