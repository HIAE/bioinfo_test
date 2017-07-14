package br.com.hiae.bioinfo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="gene")
public class Gene {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ge_id")
	private Long geneIdentification;
	
	@Column(name="ge_name")
	private String geneName;
	
	
	@ManyToMany
	@JoinTable(name="ph_on_ge", joinColumns={@JoinColumn(name="gene_id")}, inverseJoinColumns={@JoinColumn(name="phenotype_id")})
	private List<Phenotype> phenotypeList;

	public Gene() {
	}

	public Gene(Long geneIdentification, String geneName) {
		this.geneIdentification = geneIdentification;
		this.geneName = geneName;
	}
	
	public void setPhenotypeList(List<Phenotype> phenotypes){
		this.phenotypeList = phenotypes;
	}

	public Long getId() {
		return id;
	}

	public Long getGeneIdentification() {
		return geneIdentification;
	}

	public String getGeneName() {
		return geneName;
	}
	
	public List<Phenotype> getPhenotypeList(){
		return phenotypeList;
	}

	@Override
	public int hashCode() {
		return Math.toIntExact(this.geneIdentification);
	}

	@Override
	public String toString() {
		return getGeneName();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Gene)) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		Gene gene = (Gene) obj;
		return this.geneIdentification.equals(gene.getGeneIdentification());
	}

}
