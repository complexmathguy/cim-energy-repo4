/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.topologyboundaryprofile..subscriber;

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
 * Subscriber for TopologyBoundaryVersion related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("topologyBoundaryVersion-subscriber")
public class TopologyBoundaryVersionSubscriber extends BaseSubscriber {

	public TopologyBoundaryVersionSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<TopologyBoundaryVersion>, TopologyBoundaryVersion> topologyBoundaryVersionSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllTopologyBoundaryVersionQuery(), 
                		ResponseTypes.multipleInstancesOf(TopologyBoundaryVersion.class),
                		ResponseTypes.instanceOf(TopologyBoundaryVersion.class));
    }

    public SubscriptionQueryResult<TopologyBoundaryVersion, TopologyBoundaryVersion> topologyBoundaryVersionSubscribe(@DestinationVariable UUID topologyBoundaryVersionId) {
        return queryGateway
                .subscriptionQuery(new FindTopologyBoundaryVersionQuery(new LoadTopologyBoundaryVersionFilter(topologyBoundaryVersionId)), 
                		ResponseTypes.instanceOf(TopologyBoundaryVersion.class),
                		ResponseTypes.instanceOf(TopologyBoundaryVersion.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

