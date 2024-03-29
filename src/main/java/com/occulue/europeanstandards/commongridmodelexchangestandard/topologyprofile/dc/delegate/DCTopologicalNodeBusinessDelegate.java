/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.topologyprofile.dc..delegate;

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
 * DCTopologicalNode business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of DCTopologicalNode related services in the case of a DCTopologicalNode business related service failing.</li>
 * <li>Exposes a simpler, uniform DCTopologicalNode interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill DCTopologicalNode business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class DCTopologicalNodeBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public DCTopologicalNodeBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* DCTopologicalNode Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	DCTopologicalNodeBusinessDelegate
	*/
	public static DCTopologicalNodeBusinessDelegate getDCTopologicalNodeInstance() {
		return( new DCTopologicalNodeBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createDCTopologicalNode( CreateDCTopologicalNodeCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getDCTopologicalNodeId() == null )
				command.setDCTopologicalNodeId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DCTopologicalNodeValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateDCTopologicalNodeCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateDCTopologicalNodeCommand of DCTopologicalNode is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create DCTopologicalNode - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateDCTopologicalNodeCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateDCTopologicalNode( UpdateDCTopologicalNodeCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	DCTopologicalNodeValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateDCTopologicalNodeCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save DCTopologicalNode - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteDCTopologicalNodeCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteDCTopologicalNodeCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DCTopologicalNodeValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteDCTopologicalNodeCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete DCTopologicalNode using Id = "  + command.getDCTopologicalNodeId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the DCTopologicalNode via DCTopologicalNodeFetchOneSummary
     * @param 	summary DCTopologicalNodeFetchOneSummary 
     * @return 	DCTopologicalNodeFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public DCTopologicalNode getDCTopologicalNode( DCTopologicalNodeFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "DCTopologicalNodeFetchOneSummary arg cannot be null" );
    	
    	DCTopologicalNode entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	DCTopologicalNodeValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a DCTopologicalNode
        	// --------------------------------------
        	CompletableFuture<DCTopologicalNode> futureEntity = queryGateway.query(new FindDCTopologicalNodeQuery( new LoadDCTopologicalNodeFilter( summary.getDCTopologicalNodeId() ) ), ResponseTypes.instanceOf(DCTopologicalNode.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate DCTopologicalNode with id " + summary.getDCTopologicalNodeId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all DCTopologicalNodes
     *
     * @return 	List<DCTopologicalNode> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<DCTopologicalNode> getAllDCTopologicalNode() 
    throws ProcessingException {
        List<DCTopologicalNode> list = null;

        try {
        	CompletableFuture<List<DCTopologicalNode>> futureList = queryGateway.query(new FindAllDCTopologicalNodeQuery(), ResponseTypes.multipleInstancesOf(DCTopologicalNode.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all DCTopologicalNode";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }


    /**
     * add DCTopologicalNode to DCTopologicalNode 
     * @param		command AssignDCTopologicalNodeToDCTopologicalNodeCommand
     * @exception	ProcessingException
     */     
	public void addToDCTopologicalNode( AssignDCTopologicalNodeToDCTopologicalNodeCommand command ) throws ProcessingException {
		
		
		// -------------------------------------------
		// load the parent
		// -------------------------------------------
		load( command.getDCTopologicalNodeId() );

		DCTopologicalNodeBusinessDelegate childDelegate 	= DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance();
		DCTopologicalNodeBusinessDelegate parentDelegate = DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance();		
		UUID childId = command.getAddTo().getDCTopologicalNodeId();
		
		try {		
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DCTopologicalNodeValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );			
		}
		catch( Exception exc ) {
			final String msg = "Failed to add a DCTopologicalNode as DCTopologicalNode to DCTopologicalNode" ; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}

	}

    /**
     * remove DCTopologicalNode from DCTopologicalNode
     * @param		command RemoveDCTopologicalNodeFromDCTopologicalNodeCommand
     * @exception	ProcessingException
     */     	
	public void removeFromDCTopologicalNode( RemoveDCTopologicalNodeFromDCTopologicalNodeCommand command ) throws ProcessingException {		
		
		DCTopologicalNodeBusinessDelegate childDelegate 	= DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance();
		UUID childId = command.getRemoveFrom().getDCTopologicalNodeId();

		try {
			
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DCTopologicalNodeValidator.getInstance().validate( command );    

	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );

		}
		catch( Exception exc ) {
			final String msg = "Failed to remove child using Id " + childId; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		DCTopologicalNode
	 */
	private DCTopologicalNode load( UUID id ) throws ProcessingException {
		dCTopologicalNode = DCTopologicalNodeBusinessDelegate.getDCTopologicalNodeInstance().getDCTopologicalNode( new DCTopologicalNodeFetchOneSummary(id) );	
		return dCTopologicalNode;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private DCTopologicalNode dCTopologicalNode 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(DCTopologicalNodeBusinessDelegate.class.getName());
    
}
