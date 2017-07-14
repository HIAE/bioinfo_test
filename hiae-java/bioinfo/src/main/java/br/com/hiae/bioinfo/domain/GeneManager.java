package br.com.hiae.bioinfo.domain;

import java.util.List;
import java.util.Set;

import br.com.hiae.bioinfo.dto.PhenotypeByGeneTO;

public interface GeneManager {
	List<PhenotypeByGeneTO> searchPhenotypeByGeneNameList(Set<String> geneNameList);

}
