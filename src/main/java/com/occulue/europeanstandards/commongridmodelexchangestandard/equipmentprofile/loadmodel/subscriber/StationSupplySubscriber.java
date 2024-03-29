/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.loadmodel..subscriber;

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
 * Subscriber for StationSupply related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("stationSupply-subscriber")
public class StationSupplySubscriber extends BaseSubscriber {

	public StationSupplySubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<StationSupply>, StationSupply> stationSupplySubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllStationSupplyQuery(), 
                		ResponseTypes.multipleInstancesOf(StationSupply.class),
                		ResponseTypes.instanceOf(StationSupply.class));
    }

    public SubscriptionQueryResult<StationSupply, StationSupply> stationSupplySubscribe(@DestinationVariable UUID stationSupplyId) {
        return queryGateway
                .subscriptionQuery(new FindStationSupplyQuery(new LoadStationSupplyFilter(stationSupplyId)), 
                		ResponseTypes.instanceOf(StationSupply.class),
                		ResponseTypes.instanceOf(StationSupply.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

