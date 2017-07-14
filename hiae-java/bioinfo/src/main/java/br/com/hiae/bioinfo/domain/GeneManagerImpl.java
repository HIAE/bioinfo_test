package br.com.hiae.bioinfo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiae.bioinfo.dao.GeneDAO;
import br.com.hiae.bioinfo.dto.PhenotypeByGeneTO;
import br.com.hiae.bioinfo.model.Gene;
import br.com.hiae.bioinfo.model.Phenotype;

@RequestScoped
public class GeneManagerImpl implements GeneManager {
	
	private static final Logger logger = LoggerFactory.getLogger(GeneManagerImpl.class);
	
	@Inject private GeneDAO geneDAO;
	
	public GeneManagerImpl() {
		
	}
	
	
	public GeneManagerImpl(GeneDAO geneDAO) {
		this.geneDAO = geneDAO;
	}
	
	
	public List<PhenotypeByGeneTO> searchPhenotypeByGeneNameList(Set<String> geneNameSet){
		
		List<PhenotypeByGeneTO> phenotypeByGeneToList = new ArrayList<PhenotypeByGeneTO>();
		for (String geneName : geneNameSet) {
			try {
				Gene gene = geneDAO.findByGeneName(geneName);
				List<Phenotype> phenotypeList = gene.getPhenotypeList();
				
				for (Phenotype phenotype : phenotypeList) {
					
					phenotypeByGeneToList.add(
							new PhenotypeByGeneTO(
									phenotype.getPhenotypeIdentification(),
									phenotype.getPhenotypeName(),
									gene.getGeneName(),
									gene.getGeneIdentification())
							);
				}
			} catch (NoResultException nre) {
				logger.warn("There no phenotype found to gene: "+ geneName);
				logger.debug("There no phenotype found to gene: "+ geneName, nre);
			}
		}
		
		return phenotypeByGeneToList;
	}
	
	

}
