package com.example.backend.controller;

import java.util.List;

import com.example.backend.model.Exercice;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.model.LogActivites;
import com.example.backend.service.LogActivitesService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class LogActivitesController {
	@Autowired
	LogActivitesService logactivitesService;

	 //ajouter d'une historique
	@PostMapping("/addlog")
	@ApiOperation(value = "Enregistrer une historique", notes = "cette methode permet d'ajouter une historique de l'activité", response = LogActivites.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet historique est cree avec succès"),
			@ApiResponse(code = 400, message = "l'objet historique n'est pas valide") })
    public String savelog(@RequestBody LogActivites logactivites){
		logactivitesService.addLogActivites(logactivites);
        return  "historique enregistrer avec succès";
    }

	//lister historique
	  @GetMapping("/listehistorique")
	  @ApiOperation(value = "renvoi la liste des historiques", notes = "cette methode permet de chercher et renvoyer la liste des historiques des activités qui existent"
			  + "dans la BDD", responseContainer = "liste<LogActivites>", response = LogActivites.class)
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des historiques / une liste vide") })
	  public List<LogActivites> listLogActivites(){
	        return logactivitesService.listLogActivites();
	    }

	//histoire activite par  identifiant
	    @GetMapping("/histoireById/{id}")
		@ApiOperation(value = "rechercher une historique", notes = "cette methode permet de rechercher une historique d'un activité par son id", response = LogActivites.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "l'historique trouvé dans la BDD"),
				@ApiResponse(code = 404, message = "aucune historique d'activité avec cet id n'existe dans la BDD") })
	    public LogActivites listLogActivitebyid(@PathVariable("id") Long id){
	    	return logactivitesService.listLogActivitebyid(id);
	    }

	    //supprimer une histoirique
	    @DeleteMapping("/deletehistoire/{id}")
		@ApiOperation(value = "supprimer une historique d'un activité", notes = "cette methode permet de supprimer une historique d'un activité par son id", response = LogActivites.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "l'historique a été supprimé"),
				@ApiResponse(code = 404, message = "aucun historique avec cet id n'existe dans la BDD") })
	    public String suppressionhistoire(@PathVariable Long id){
	    	logactivitesService.deleteLogActivitesByid(id);
	        return "historique supprimer avec succès";
	    }
}
