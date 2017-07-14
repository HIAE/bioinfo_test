package br.com.hiae.bioinfo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="phenotype")
public class Phenotype {
	
		@Id
		@GeneratedValue
		private Long id;
		
		@Column(name="ph_id")
		private String phenotypeIdentification;
		
		@Column(name="ph_name")
		private String phenotypeName;

		public Phenotype() {
		}

		public Phenotype(String phenotypeIdentification, String phenotypeName) {
			this.phenotypeIdentification = phenotypeIdentification;
			this.phenotypeName = phenotypeName;
		}

		public Long getId() {
			return id;
		}

		public String getPhenotypeIdentification() {
			return phenotypeIdentification;
		}

		public String getPhenotypeName() {
			return phenotypeName;
		}

		@Override
		public int hashCode() {
			return this.getPhenotypeIdentification().hashCode();
		}

		@Override
		public String toString() {
			return getPhenotypeName();
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Phenotype)) {
				return false;
			}
			if (obj == this) {
				return true;
			}

			Phenotype phenotype = (Phenotype) obj;
			return this.getPhenotypeIdentification().equals(phenotype.getPhenotypeIdentification());
		}

}
