/*
 * kfetinfo - Logiciel pour la K'Fet du BDE Info de l'IUT Lyon 1
 *  Copyright (C) 2017 Simon Lecutiez

 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package kfetinfo;

import java.io.File;
import java.io.FileReader;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LecteurBase {
	static String path = new File("").getAbsolutePath();
	static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	
	private static JSONObject lireObjet(String path){
		JSONParser parser = new JSONParser();

		Object objet = null;
		try {
		objet = parser.parse(new FileReader(path));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject objetJson = (JSONObject)objet;
		return(objetJson);
	}

	public static Ingredient lireIngredient(String nom){
		JSONObject ingredientJson = new JSONObject();
		ingredientJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Contenus Commandes\\Ingrédients\\" + nom + ".json");

		Ingredient ingredient = new Ingredient((String)ingredientJson.get("id"), (String)ingredientJson.get("nom"), ((Number)ingredientJson.get("cout")).floatValue(), (boolean)ingredientJson.get("estDisponible"), ((Number)ingredientJson.get("nbUtilisations")).intValue(), ((Number)ingredientJson.get("priorite")).intValue());
		return(ingredient);
	}

	public static Sauce lireSauce(String nom){
		JSONObject sauceJson = new JSONObject();
		sauceJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Contenus Commandes\\Sauces\\" + nom + ".json");

		Sauce sauce = new Sauce((String)sauceJson.get("id"), (String)sauceJson.get("nom"), ((Number)sauceJson.get("cout")).floatValue(), (boolean)sauceJson.get("estDisponible"), ((Number)sauceJson.get("nbUtilisations")).intValue(), ((Number)sauceJson.get("priorite")).intValue());
		return(sauce);
	}

	public static Dessert lireDessert(String nom){
		JSONObject dessertJson = new JSONObject();
		dessertJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Contenus Commandes\\Desserts\\" + nom + ".json");

		Dessert dessert = new Dessert((String)dessertJson.get("id"), (String)dessertJson.get("nom"), ((Number)dessertJson.get("cout")).floatValue(), (boolean)dessertJson.get("estDisponible"), ((Number)dessertJson.get("nbUtilisations")).intValue(), ((Number)dessertJson.get("priorite")).intValue(), ((Number)dessertJson.get("prix")).floatValue());
		return(dessert);
	}

	public static Boisson lireBoisson(String nom){
		JSONObject boissonJson = new JSONObject();
		boissonJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Contenus Commandes\\Boissons\\" + nom + ".json");

		Boisson boisson = new Boisson((String)boissonJson.get("id"), (String)boissonJson.get("nom"), ((Number)boissonJson.get("cout")).floatValue(), (boolean)boissonJson.get("estDisponible"), ((Number)boissonJson.get("nbUtilisations")).intValue(), ((Number)boissonJson.get("priorite")).intValue());
		return(boisson);
	}

	public static Plat lirePlat(String nom){
		JSONObject platJson = new JSONObject();
		platJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Contenus Commandes\\Plats\\" + nom + ".json");

		Plat plat = new Plat((String)platJson.get("id"), (String)platJson.get("nom"), ((Number)platJson.get("cout")).floatValue(), (boolean)platJson.get("estDisponible"), ((Number)platJson.get("nbUtilisations")).intValue(), ((Number)platJson.get("priorite")).intValue(), ((Number)platJson.get("nbMaxIngredients")).intValue(), ((Number)platJson.get("nbMaxSauces")).intValue(), ((Number)platJson.get("prix")).floatValue());
		return(plat);
	}

	public static SupplementBoisson lireSupplementBoisson(String nom){
		JSONObject supplementBoissonJson = new JSONObject();
		supplementBoissonJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Contenus Commandes\\Suppléments Boisson\\" + nom + ".json");

		SupplementBoisson supplementBoisson = new SupplementBoisson((String)supplementBoissonJson.get("id"), (String)supplementBoissonJson.get("nom"), ((Number)supplementBoissonJson.get("cout")).floatValue(), (boolean)supplementBoissonJson.get("estDisponible"), ((Number)supplementBoissonJson.get("nbUtilisations")).intValue(), ((Number)supplementBoissonJson.get("priorite")).intValue(), ((Number)supplementBoissonJson.get("prix")).floatValue());
		return(supplementBoisson);
	}

	public static Membre lireMembre(String nomFichier){
		JSONObject membreJson = new JSONObject();
		membreJson = lireObjet(path + "\\src\\main\\resources\\Base de Données\\Membres\\" + nomFichier + ".json");

		try {
			Membre membre = new Membre((String)membreJson.get("id"), (String)membreJson.get("nom"), (String)membreJson.get("prenom"), (String)membreJson.get("surnom"), (String)membreJson.get("poste"), formatDate.parse((String)membreJson.get("dateNaissance")), ((Number)membreJson.get("nbCommandes")).intValue(), ((Number)membreJson.get("nbServices")).intValue(), ((Number)membreJson.get("tempsMoyenCommande")).floatValue());
			return(membre);
		}
		catch (Exception e) {
			e.printStackTrace();
			Membre membre = new Membre("", "erreur", "", "", "", new Date(0), 0, 0, 0f);
			return(membre);
		}
	}
}