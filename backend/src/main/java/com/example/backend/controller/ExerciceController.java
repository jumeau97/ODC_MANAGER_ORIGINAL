package com.example.backend.controller;

import java.util.List;

import com.example.backend.model.Activite;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Exercice;
import com.example.backend.service.ExerciceService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ExerciceController {
	@Autowired
	ExerciceService exerciceService;
	 //ajouter un exercice
	@PostMapping("/ajoutExercice")
	@ApiOperation(value = "Enregistrer un exercice", notes = "cette methode permet d'ajouter un exercice", response = Exercice.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet exercie cree"),
			@ApiResponse(code = 400, message = "l'objet exercice n'est pas valide") })
    public String ajouExercice(@RequestBody Exercice exercice){
        exerciceService.ajoutExercice(exercice);
        return  "exercice enregistrer avec succès";
    }

	 //lister les exercice
	  @GetMapping("/listeExercice")
	  @ApiOperation(value = "renvoi la liste des execices", notes = "cette methode permet de chercher et renvoyer la liste des execices qui existent"
			  + "dans la BDD", responseContainer = "liste<Execices>", response = Exercice.class)
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des exercices / une liste vide") })
	    public List<Exercice> listExercice(){
	        return exerciceService.listExercice();
	    }

	//un exercice par son identifiant
	    @GetMapping("/ExerciceById/{id}")
		@ApiOperation(value = "rechercher un exercice", notes = "cette methode permet de rechercher un exercice par son id", response = Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "l'exercice trouvé dans la BDD"),
				@ApiResponse(code = 404, message = "aucun exercice avec cet id n'existe dans la BDD") })
	    public Exercice unExercice(@PathVariable("id") Long id){
	        return exerciceService.ExerciceById(id);
	    }

	  //mise à jour exercice
	    @PutMapping("/updateExercice/{id}")
		@ApiOperation(value = "Modifier un exercice", notes = "cette methode permet de modifier un exercice", response = Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet exercice modifié"),
				@ApiResponse(code = 400, message = "l'objet exercice n'est pas valide") })
	    public String reExercice(@PathVariable Long id, @RequestBody Exercice exercice){
	        exerciceService.updateExcercice(id, exercice);
	        return "exercice modifier avec succès";
	    }

	  //supprimer un exercice
	    @DeleteMapping("/supprimerExercice/{id}")
		@ApiOperation(value = "supprimer un exercice", notes = "cette methode permet de supprimer une exercice par son id", response = Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "l'exercice a été supprimé"),
				@ApiResponse(code = 404, message = "aucun exercice avec cet id n'existe dans la BDD") })
	    public String supExercice(@PathVariable Long id){
	        exerciceService.deleteExercice(id);
	        return "exercice supprimer avec succès";
	    }

	    @GetMapping("/ExerciceByYear={annee}")
		@ApiOperation(value = "renvoi la liste des execices par une année donné", notes = "cette methode permet de chercher et renvoyer la liste des execices qui ont été éffectué dans une année"
				+ "dans la BDD", responseContainer = "liste<Execices>", response = Exercice.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des exercices / une liste vide") })
	    public List<Exercice> recherExerciceAnnee(@PathVariable("annee") String annee){
	        return this.exerciceService.getExerciceByAnnee(annee);
	    }

}
