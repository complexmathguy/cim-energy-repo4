/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.diagramlayoutprofile.diagramlayout..subscriber;

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
 * Subscriber for DiagramObjectPoint related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("diagramObjectPoint-subscriber")
public class DiagramObjectPointSubscriber extends BaseSubscriber {

	public DiagramObjectPointSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<DiagramObjectPoint>, DiagramObjectPoint> diagramObjectPointSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllDiagramObjectPointQuery(), 
                		ResponseTypes.multipleInstancesOf(DiagramObjectPoint.class),
                		ResponseTypes.instanceOf(DiagramObjectPoint.class));
    }

    public SubscriptionQueryResult<DiagramObjectPoint, DiagramObjectPoint> diagramObjectPointSubscribe(@DestinationVariable UUID diagramObjectPointId) {
        return queryGateway
                .subscriptionQuery(new FindDiagramObjectPointQuery(new LoadDiagramObjectPointFilter(diagramObjectPointId)), 
                		ResponseTypes.instanceOf(DiagramObjectPoint.class),
                		ResponseTypes.instanceOf(DiagramObjectPoint.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

