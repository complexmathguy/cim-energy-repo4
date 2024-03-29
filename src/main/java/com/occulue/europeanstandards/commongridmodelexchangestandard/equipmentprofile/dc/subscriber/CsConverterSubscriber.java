/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.dc..subscriber;

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
 * Subscriber for CsConverter related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("csConverter-subscriber")
public class CsConverterSubscriber extends BaseSubscriber {

	public CsConverterSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<CsConverter>, CsConverter> csConverterSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllCsConverterQuery(), 
                		ResponseTypes.multipleInstancesOf(CsConverter.class),
                		ResponseTypes.instanceOf(CsConverter.class));
    }

    public SubscriptionQueryResult<CsConverter, CsConverter> csConverterSubscribe(@DestinationVariable UUID csConverterId) {
        return queryGateway
                .subscriptionQuery(new FindCsConverterQuery(new LoadCsConverterFilter(csConverterId)), 
                		ResponseTypes.instanceOf(CsConverter.class),
                		ResponseTypes.instanceOf(CsConverter.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

