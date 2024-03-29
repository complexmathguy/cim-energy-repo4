/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile..projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import com.occulue.repository.*;

/**
 * Projector for ResistancePerLength as outlined for the CQRS pattern.  All event handling and query handling related to ResistancePerLength are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by ResistancePerLengthAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("resistancePerLength")
@Component("resistancePerLength-projector")
public class ResistancePerLengthProjector extends ResistancePerLengthEntityProjector {
		
	// core constructor
	public ResistancePerLengthProjector(ResistancePerLengthRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateResistancePerLengthEvent
     */
    @EventHandler( payloadType=CreateResistancePerLengthEvent.class )
    public void handle( CreateResistancePerLengthEvent event) {
	    LOGGER.info("handling CreateResistancePerLengthEvent - " + event );
	    ResistancePerLength entity = new ResistancePerLength();
        entity.setResistancePerLengthId( event.getResistancePerLengthId() );
        entity.setDenominatorMultiplier( event.getDenominatorMultiplier() );
        entity.setDenominatorUnit( event.getDenominatorUnit() );
        entity.setMultiplier( event.getMultiplier() );
        entity.setUnit( event.getUnit() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllResistancePerLength( entity );
    }

    /*
     * @param	event UpdateResistancePerLengthEvent
     */
    @EventHandler( payloadType=UpdateResistancePerLengthEvent.class )
    public void handle( UpdateResistancePerLengthEvent event) {
    	LOGGER.info("handling UpdateResistancePerLengthEvent - " + event );
    	
	    ResistancePerLength entity = new ResistancePerLength();
        entity.setResistancePerLengthId( event.getResistancePerLengthId() );
        entity.setDenominatorMultiplier( event.getDenominatorMultiplier() );
        entity.setDenominatorUnit( event.getDenominatorUnit() );
        entity.setMultiplier( event.getMultiplier() );
        entity.setUnit( event.getUnit() );
        entity.setValue( event.getValue() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindResistancePerLength( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllResistancePerLength( entity );        
    }
    
    /*
     * @param	event DeleteResistancePerLengthEvent
     */
    @EventHandler( payloadType=DeleteResistancePerLengthEvent.class )
    public void handle( DeleteResistancePerLengthEvent event) {
    	LOGGER.info("handling DeleteResistancePerLengthEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	ResistancePerLength entity = delete( event.getResistancePerLengthId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllResistancePerLength( entity );

    }    

    /*
     * @param	event AssignValueToResistancePerLengthEvent
     */
    @EventHandler( payloadType=AssignValueToResistancePerLengthEvent.class)
    public void handle( AssignValueToResistancePerLengthEvent event) {
	    LOGGER.info("handling AssignValueToResistancePerLengthEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ResistancePerLength entity = assignValue( event.getResistancePerLengthId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindResistancePerLength( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllResistancePerLength( entity );
    }
    

	/*
	 * @param	event UnAssignValueFromResistancePerLengthEvent
	 */
	@EventHandler( payloadType=UnAssignValueFromResistancePerLengthEvent.class)
	public void handle( UnAssignValueFromResistancePerLengthEvent event) {
	    LOGGER.info("handling UnAssignValueFromResistancePerLengthEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ResistancePerLength entity = unAssignValue( event.getResistancePerLengthId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindResistancePerLength( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllResistancePerLength( entity );
	}




    /**
     * Method to retrieve the ResistancePerLength via an ResistancePerLengthPrimaryKey.
     * @param 	id Long
     * @return 	ResistancePerLength
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public ResistancePerLength handle( FindResistancePerLengthQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getResistancePerLengthId() );
    }
    
    /**
     * Method to retrieve a collection of all ResistancePerLengths
     *
     * @param	query	FindAllResistancePerLengthQuery 
     * @return 	List<ResistancePerLength> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<ResistancePerLength> handle( FindAllResistancePerLengthQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindResistancePerLength, 
	 * but only if the id matches
	 * 
	 * @param		entity	ResistancePerLength
	 */
	protected void emitFindResistancePerLength( ResistancePerLength entity ) {
		LOGGER.info("handling emitFindResistancePerLength" );
		
	    queryUpdateEmitter.emit(FindResistancePerLengthQuery.class,
	                            query -> query.getFilter().getResistancePerLengthId().equals(entity.getResistancePerLengthId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllResistancePerLength
	 * 
	 * @param		entity	ResistancePerLength
	 */
	protected void emitFindAllResistancePerLength( ResistancePerLength entity ) {
		LOGGER.info("handling emitFindAllResistancePerLength" );
		
	    queryUpdateEmitter.emit(FindAllResistancePerLengthQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(ResistancePerLengthProjector.class.getName());

}
