/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels..subscriber;

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
 * Subscriber for VoltageAdjusterUserDefined related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("voltageAdjusterUserDefined-subscriber")
public class VoltageAdjusterUserDefinedSubscriber extends BaseSubscriber {

	public VoltageAdjusterUserDefinedSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<VoltageAdjusterUserDefined>, VoltageAdjusterUserDefined> voltageAdjusterUserDefinedSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllVoltageAdjusterUserDefinedQuery(), 
                		ResponseTypes.multipleInstancesOf(VoltageAdjusterUserDefined.class),
                		ResponseTypes.instanceOf(VoltageAdjusterUserDefined.class));
    }

    public SubscriptionQueryResult<VoltageAdjusterUserDefined, VoltageAdjusterUserDefined> voltageAdjusterUserDefinedSubscribe(@DestinationVariable UUID voltageAdjusterUserDefinedId) {
        return queryGateway
                .subscriptionQuery(new FindVoltageAdjusterUserDefinedQuery(new LoadVoltageAdjusterUserDefinedFilter(voltageAdjusterUserDefinedId)), 
                		ResponseTypes.instanceOf(VoltageAdjusterUserDefined.class),
                		ResponseTypes.instanceOf(VoltageAdjusterUserDefined.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

