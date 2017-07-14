package br.com.hiae.bioinfo.dto;

import com.google.gson.annotations.SerializedName;

public class PhenotypeByGeneTO {
	
	private String hpo;
	private String phenotype;
	private String gene;
	@SerializedName("gene_id")
	private Long geneId;
	
	public PhenotypeByGeneTO() {}
	
	public PhenotypeByGeneTO(String hpo, String phenotype, String gene, Long geneId) {
		this.hpo = hpo;
		this.phenotype = phenotype;
		this.gene = gene;
		this.geneId = geneId;
	}

	public String getHpo() {
		return hpo;
	}

	public String getPhenotype() {
		return phenotype;
	}

	public String getGene() {
		return gene;
	}

	public Long getGeneId() {
		return geneId;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PhenotypeByGeneTO)) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		PhenotypeByGeneTO phenotypeByGeneTO = (PhenotypeByGeneTO) obj;
		return this.getGeneId().equals(phenotypeByGeneTO.getGeneId()) &&
				this.getHpo().equals(phenotypeByGeneTO.getHpo());
		
	}

}
