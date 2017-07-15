package br.com.hiae.bioinfo.model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;




public class GeneTest {

	@Test
	public void TestEquasl() {
		Long geneIdGLI3 = 123L;
		Long geneIdATR = 124L;
		String geneNameGLI3 = "GLI3";
		String geneNameATR = "ATR";
		
		Gene targetGLI3 = new Gene(geneIdGLI3, geneNameGLI3);
		
		Gene geneGLI3 = new Gene(geneIdGLI3, geneNameGLI3);
		Gene geneATR = new Gene(geneIdATR, geneNameATR);
		
		Assert.assertEquals(targetGLI3, geneGLI3);
		Assert.assertNotEquals(targetGLI3, geneATR);

	}
	
	@Test
	public void TestToString() {
		Long geneIdGLI3 = 123L;
		String geneNameGLI3 = "GLI3";
		
		Gene targetGLI3 = new Gene(geneIdGLI3, geneNameGLI3);
		
		Assert.assertEquals(geneNameGLI3, targetGLI3.toString());
	}
	
	@Test
	public void TestGetPhenotypes() {
		Long geneIdGLI3 = 123L;
		String geneNameGLI3 = "GLI3";
		
		Phenotype phenotype01 = new Phenotype("hop:011", "teste 0111");
		Phenotype phenotype02 = new Phenotype("hop:012", "test 3 012");
		ArrayList<Phenotype> phenotypes = new ArrayList<Phenotype>();
		phenotypes.add(phenotype01);
		phenotypes.add(phenotype02);
		
		Gene target = new Gene(geneIdGLI3, geneNameGLI3);
		target.setPhenotypeList(phenotypes);
		
		Assert.assertEquals(phenotypes, target.getPhenotypeList());
	}

}
