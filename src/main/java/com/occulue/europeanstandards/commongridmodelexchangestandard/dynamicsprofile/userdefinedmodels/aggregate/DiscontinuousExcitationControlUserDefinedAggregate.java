package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels..aggregate;

import com.occulue.api.*;
import com.occulue.aggregate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

/**
 * Aggregate handler for DiscontinuousExcitationControlUserDefined as outlined for the CQRS pattern, all write responsibilities 
 * related to DiscontinuousExcitationControlUserDefined are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DiscontinuousExcitationControlUserDefinedAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DiscontinuousExcitationControlUserDefinedAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DiscontinuousExcitationControlUserDefinedAggregate(CreateDiscontinuousExcitationControlUserDefinedCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDiscontinuousExcitationControlUserDefinedCommand" );
    	CreateDiscontinuousExcitationControlUserDefinedEvent event = new CreateDiscontinuousExcitationControlUserDefinedEvent(command.getDiscontinuousExcitationControlUserDefinedId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDiscontinuousExcitationControlUserDefinedCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDiscontinuousExcitationControlUserDefinedCommand" );
    	UpdateDiscontinuousExcitationControlUserDefinedEvent event = new UpdateDiscontinuousExcitationControlUserDefinedEvent(command.getDiscontinuousExcitationControlUserDefinedId(), command.getProprietary());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDiscontinuousExcitationControlUserDefinedCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDiscontinuousExcitationControlUserDefinedCommand" );
        apply(new DeleteDiscontinuousExcitationControlUserDefinedEvent(command.getDiscontinuousExcitationControlUserDefinedId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignProprietaryToDiscontinuousExcitationControlUserDefinedCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignProprietaryToDiscontinuousExcitationControlUserDefinedCommand" );
    	
    	if (  proprietary != null && proprietary.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "Proprietary already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignProprietaryToDiscontinuousExcitationControlUserDefinedEvent(command.getDiscontinuousExcitationControlUserDefinedId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignProprietaryFromDiscontinuousExcitationControlUserDefinedCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignProprietaryFromDiscontinuousExcitationControlUserDefinedCommand" );

    	if (  proprietary == null )
    		throw new ProcessingException( "Proprietary already has nothing assigned." );  

    	apply(new UnAssignProprietaryFromDiscontinuousExcitationControlUserDefinedEvent(command.getDiscontinuousExcitationControlUserDefinedId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDiscontinuousExcitationControlUserDefinedEvent event) {	
    	LOGGER.info( "Event sourcing CreateDiscontinuousExcitationControlUserDefinedEvent" );
    	this.discontinuousExcitationControlUserDefinedId = event.getDiscontinuousExcitationControlUserDefinedId();
    }
    
    @EventSourcingHandler
    void on(UpdateDiscontinuousExcitationControlUserDefinedEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.proprietary = event.getProprietary();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignProprietaryToDiscontinuousExcitationControlUserDefinedEvent event ) {	
    	LOGGER.info( "Event sourcing AssignProprietaryToDiscontinuousExcitationControlUserDefinedEvent" );
    	this.proprietary = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignProprietaryFromDiscontinuousExcitationControlUserDefinedEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignProprietaryFromDiscontinuousExcitationControlUserDefinedEvent" );
		this.proprietary = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID discontinuousExcitationControlUserDefinedId;
    
    private BooleanProxy proprietary = null;

    private static final Logger LOGGER 	= Logger.getLogger(DiscontinuousExcitationControlUserDefinedAggregate.class.getName());
}
