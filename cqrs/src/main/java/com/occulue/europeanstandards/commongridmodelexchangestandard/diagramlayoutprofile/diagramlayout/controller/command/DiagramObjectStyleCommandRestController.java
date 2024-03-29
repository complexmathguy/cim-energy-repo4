/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.diagramlayoutprofile.diagramlayout..controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import com.occulue.controller.command.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller command CQRS processing for entity DiagramObjectStyle.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DiagramObjectStyle")
public class DiagramObjectStyleCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a DiagramObjectStyle.  if not key provided, calls create, otherwise calls save
     * @param		DiagramObjectStyle	diagramObjectStyle
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateDiagramObjectStyleCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = DiagramObjectStyleBusinessDelegate.getDiagramObjectStyleInstance().createDiagramObjectStyle( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a DiagramObjectStyle.  if no key provided, calls create, otherwise calls save
     * @param		DiagramObjectStyle diagramObjectStyle
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateDiagramObjectStyleCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateDiagramObjectStyleCommand
			// -----------------------------------------------
			completableFuture = DiagramObjectStyleBusinessDelegate.getDiagramObjectStyleInstance().updateDiagramObjectStyle(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "DiagramObjectStyleController:update() - successfully update DiagramObjectStyle - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a DiagramObjectStyle entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID diagramObjectStyleId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteDiagramObjectStyleCommand command = new DeleteDiagramObjectStyleCommand( diagramObjectStyleId );

    	try {
        	DiagramObjectStyleBusinessDelegate delegate = DiagramObjectStyleBusinessDelegate.getDiagramObjectStyleInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted DiagramObjectStyle with key " + command.getDiagramObjectStyleId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	



//************************************************************************    
// Attributes
//************************************************************************
    protected DiagramObjectStyle diagramObjectStyle = null;
    private static final Logger LOGGER = Logger.getLogger(DiagramObjectStyleCommandRestController.class.getName());
    
}
