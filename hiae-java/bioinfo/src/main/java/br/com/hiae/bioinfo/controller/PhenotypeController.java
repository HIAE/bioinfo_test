package br.com.hiae.bioinfo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.hiae.bioinfo.domain.GeneManager;
import br.com.hiae.bioinfo.dto.PhenotypeByGeneTO;

@Controller
public class PhenotypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhenotypeController.class);
	
	private final Result result;
	private GeneManager geneManager;

	
	protected PhenotypeController() {
		this(null, null);
	}
	
	@Inject
	public PhenotypeController(Result result, GeneManager geneManager) {
		this.result = result;
		this.geneManager = geneManager;
	}
	
	@Get("/phenotypes/genes/{listOfGeneNames}")
	public void searchPhenotypeByGeneName(String listOfGeneNames) {
		try {
			@SuppressWarnings("unchecked")
			Set<String> setOfGeneNames = new Gson().fromJson(listOfGeneNames, HashSet.class);
			
			List<PhenotypeByGeneTO> searchPhenotypeByGeneNameList = geneManager.searchPhenotypeByGeneNameList(setOfGeneNames);
			result.use(Results.json()).withoutRoot().from(searchPhenotypeByGeneNameList).serialize();
			
		} catch (Exception e) {
			logger.warn("Something is wrong in request. Verify the url, parameters and format - " + e.getMessage());
			logger.debug(e.getMessage(), e);
			result.use(Results.status()).badRequest("Something is wrong in request. Verify the url, parameters and format.");
		}
		
	}

}
