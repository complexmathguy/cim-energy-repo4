/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.winddynamics..subscriber;

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
 * Subscriber for WindPitchContEmulIEC related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("windPitchContEmulIEC-subscriber")
public class WindPitchContEmulIECSubscriber extends BaseSubscriber {

	public WindPitchContEmulIECSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<WindPitchContEmulIEC>, WindPitchContEmulIEC> windPitchContEmulIECSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllWindPitchContEmulIECQuery(), 
                		ResponseTypes.multipleInstancesOf(WindPitchContEmulIEC.class),
                		ResponseTypes.instanceOf(WindPitchContEmulIEC.class));
    }

    public SubscriptionQueryResult<WindPitchContEmulIEC, WindPitchContEmulIEC> windPitchContEmulIECSubscribe(@DestinationVariable UUID windPitchContEmulIECId) {
        return queryGateway
                .subscriptionQuery(new FindWindPitchContEmulIECQuery(new LoadWindPitchContEmulIECFilter(windPitchContEmulIECId)), 
                		ResponseTypes.instanceOf(WindPitchContEmulIEC.class),
                		ResponseTypes.instanceOf(WindPitchContEmulIEC.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

