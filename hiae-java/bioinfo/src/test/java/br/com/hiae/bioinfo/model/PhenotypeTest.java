package br.com.hiae.bioinfo.model;

import org.junit.Assert;
import org.junit.Test;


public class PhenotypeTest {
	
	@Test
	public void TestEquasl() {
		
		String phenotypeId1844 = "HP:0011844";
		String phenotypeId1845 = "HP:0011845";
		String phenotypeName = "Abnormal appendicular skeleton morphology";
		
		Phenotype phenotypeTarget = new Phenotype(phenotypeId1844, phenotypeName);
		Phenotype phenotype1844 = new Phenotype(phenotypeId1844, phenotypeName);
		Phenotype phenotype1845 = new Phenotype(phenotypeId1845, phenotypeName);
		
		Assert.assertEquals(phenotypeTarget, phenotype1844);
		Assert.assertNotEquals(phenotypeTarget, phenotype1845);

	}
	
	@Test
	public void TestToString() {
		String phenotypeId1844 = "HP:0011844";
		String phenotypeName = "Abnormal appendicular skeleton morphology";
		
		Phenotype phenotypeTarget = new Phenotype(phenotypeId1844, phenotypeName);
		
		Assert.assertEquals(phenotypeName, phenotypeTarget.toString());
	}


}
