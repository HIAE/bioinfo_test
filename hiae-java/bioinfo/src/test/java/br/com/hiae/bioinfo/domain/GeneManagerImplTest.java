package br.com.hiae.bioinfo.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtMost;

import br.com.hiae.bioinfo.dao.GeneDAO;
import br.com.hiae.bioinfo.dto.PhenotypeByGeneTO;
import br.com.hiae.bioinfo.model.Gene;
import br.com.hiae.bioinfo.model.Phenotype;

public class GeneManagerImplTest {
	
	private static final int ONE = 1;
	
	@Test
	public void testVerifyIfRequestScopedAnnotationIsPresent() throws Exception {
		GeneManagerImpl geneManager = new GeneManagerImpl();
		RequestScoped[] ApplicationScopedAnnotations = geneManager.getClass().getAnnotationsByType(RequestScoped.class);
		Assert.assertEquals(ONE, ApplicationScopedAnnotations.length);
	}
	
	@Test
	public void test() {
		
		String geneGLI3 = "GLI3";
		Long geneIdGLI3 = 123l;
		Set<String> geneNameSet = new HashSet<String>();
		
		geneNameSet.add(geneGLI3);
		
		Phenotype phenotype01 = new Phenotype("hop:011", "teste 0111");
		Phenotype phenotype02 = new Phenotype("hop:012", "test 3 012");
		
		ArrayList<Phenotype> phenotypes = new ArrayList<Phenotype>();
		phenotypes.add(phenotype01);
		phenotypes.add(phenotype02);
		
		
		List<PhenotypeByGeneTO> phenotypeByGeneTOListExpected = createPhenotypeByGeneToListExpected(geneGLI3, geneIdGLI3, phenotypes);
		
		
		Gene geneResult = new Gene(geneIdGLI3, geneGLI3);
		geneResult.setPhenotypeList(phenotypes);
		
		
		GeneDAO geneDAOMock = Mockito.mock(GeneDAO.class);
		Mockito.when(geneDAOMock.findByGeneName(geneGLI3)).thenReturn(geneResult);
		
		GeneManager target = new GeneManagerImpl(geneDAOMock);
		
		
		Assert.assertEquals(phenotypeByGeneTOListExpected, target.searchPhenotypeByGeneNameList(geneNameSet));
		
		Mockito.verify(geneDAOMock, new AtMost(1)).findByGeneName(geneGLI3);

	}
	
	@Test
	public void testWhenThereNoGeneFoundShoudBeReturnEmptyList() throws Exception {
		
		String geneGLI3 = "GLI3";
		Set<String> geneNameSet = new HashSet<String>();
		geneNameSet.add(geneGLI3);
		
		
		GeneDAO geneDAOMock = Mockito.mock(GeneDAO.class);
		Mockito.when(geneDAOMock.findByGeneName(geneGLI3)).thenThrow(NoResultException.class);
		
		GeneManager target = new GeneManagerImpl(geneDAOMock);
		
		
		Assert.assertEquals(Collections.emptyList(), target.searchPhenotypeByGeneNameList(geneNameSet));
		
		Mockito.verify(geneDAOMock, new AtMost(1)).findByGeneName(geneGLI3);
		
	}

	private List<PhenotypeByGeneTO> createPhenotypeByGeneToListExpected(String geneGLI3, Long geneIdGLI3, ArrayList<Phenotype> phenotypes) {
		List<PhenotypeByGeneTO> phenotypeByGeneTOListExpected = new ArrayList<PhenotypeByGeneTO>();
		for (Phenotype phenotype : phenotypes) {
			phenotypeByGeneTOListExpected.add(new PhenotypeByGeneTO(phenotype.getPhenotypeIdentification(), phenotype.getPhenotypeName(), geneGLI3, geneIdGLI3));
		}
		return phenotypeByGeneTOListExpected;
	}

}
