package br.com.hiae.bioinfo.dto;

import org.junit.Assert;
import org.junit.Test;


public class PhenotypeByGeneTOTest {
	
	@Test
	public void testGetters() {
		String hpo = "HP:0011845";
		String phenotype = "Abnormal appendicular skeleton morphology";
		String gene = "GLI3";
		Long geneId = 123l;
		PhenotypeByGeneTO target = new PhenotypeByGeneTO(hpo, phenotype, gene, geneId);
		
		Assert.assertEquals(target.getHpo(), hpo);
		Assert.assertEquals(target.getPhenotype(), phenotype);
		Assert.assertEquals(target.getGene(), gene);
		Assert.assertEquals(target.getGeneId(), geneId);
		
	}

}
