/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires..subscriber;

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
 * Subscriber for PhaseTapChangerSymmetrical related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("phaseTapChangerSymmetrical-subscriber")
public class PhaseTapChangerSymmetricalSubscriber extends BaseSubscriber {

	public PhaseTapChangerSymmetricalSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<PhaseTapChangerSymmetrical>, PhaseTapChangerSymmetrical> phaseTapChangerSymmetricalSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllPhaseTapChangerSymmetricalQuery(), 
                		ResponseTypes.multipleInstancesOf(PhaseTapChangerSymmetrical.class),
                		ResponseTypes.instanceOf(PhaseTapChangerSymmetrical.class));
    }

    public SubscriptionQueryResult<PhaseTapChangerSymmetrical, PhaseTapChangerSymmetrical> phaseTapChangerSymmetricalSubscribe(@DestinationVariable UUID phaseTapChangerSymmetricalId) {
        return queryGateway
                .subscriptionQuery(new FindPhaseTapChangerSymmetricalQuery(new LoadPhaseTapChangerSymmetricalFilter(phaseTapChangerSymmetricalId)), 
                		ResponseTypes.instanceOf(PhaseTapChangerSymmetrical.class),
                		ResponseTypes.instanceOf(PhaseTapChangerSymmetrical.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

