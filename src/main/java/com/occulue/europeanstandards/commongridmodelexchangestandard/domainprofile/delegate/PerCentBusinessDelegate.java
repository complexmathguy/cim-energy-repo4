/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile..delegate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.concurrent.*;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;

/**
 * PerCent business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of PerCent related services in the case of a PerCent business related service failing.</li>
 * <li>Exposes a simpler, uniform PerCent interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill PerCent business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class PerCentBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public PerCentBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* PerCent Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	PerCentBusinessDelegate
	*/
	public static PerCentBusinessDelegate getPerCentInstance() {
		return( new PerCentBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createPerCent( CreatePerCentCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getPerCentId() == null )
				command.setPerCentId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	PerCentValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreatePerCentCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreatePerCentCommand of PerCent is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create PerCent - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdatePerCentCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updatePerCent( UpdatePerCentCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	PerCentValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdatePerCentCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save PerCent - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeletePerCentCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeletePerCentCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	PerCentValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeletePerCentCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete PerCent using Id = "  + command.getPerCentId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the PerCent via PerCentFetchOneSummary
     * @param 	summary PerCentFetchOneSummary 
     * @return 	PerCentFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public PerCent getPerCent( PerCentFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "PerCentFetchOneSummary arg cannot be null" );
    	
    	PerCent entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	PerCentValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a PerCent
        	// --------------------------------------
        	CompletableFuture<PerCent> futureEntity = queryGateway.query(new FindPerCentQuery( new LoadPerCentFilter( summary.getPerCentId() ) ), ResponseTypes.instanceOf(PerCent.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate PerCent with id " + summary.getPerCentId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all PerCents
     *
     * @return 	List<PerCent> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<PerCent> getAllPerCent() 
    throws ProcessingException {
        List<PerCent> list = null;

        try {
        	CompletableFuture<List<PerCent>> futureList = queryGateway.query(new FindAllPerCentQuery(), ResponseTypes.multipleInstancesOf(PerCent.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all PerCent";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign Value on PerCent
     * @param		command AssignValueToPerCentCommand	
     * @exception	ProcessingException
     */     
	public void assignValue( AssignValueToPerCentCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getPerCentId() );
		
		FloatProxyBusinessDelegate childDelegate 	= FloatProxyBusinessDelegate.getFloatProxyInstance();
		PerCentBusinessDelegate parentDelegate = PerCentBusinessDelegate.getPerCentInstance();			
		UUID childId = command.getAssignment().getFloatProxyId();
		FloatProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	PerCentValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get FloatProxy using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign Value on PerCent
     * @param		command UnAssignValueFromPerCentCommand
     * @exception	ProcessingException
     */     
	public void unAssignValue( UnAssignValueFromPerCentCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	PerCentValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Value on PerCent";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		PerCent
	 */
	private PerCent load( UUID id ) throws ProcessingException {
		perCent = PerCentBusinessDelegate.getPerCentInstance().getPerCent( new PerCentFetchOneSummary(id) );	
		return perCent;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private PerCent perCent 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(PerCentBusinessDelegate.class.getName());
    
}
