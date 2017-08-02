# bioinfo_test
Bem-vindo ao Teste de Bioinformática

# Objetivo
Criar API que irá retornar o(s) fenótipo(s) de um determinado gene ou lista de genes

# Requisitos
Desenvolvimento em Python.

A API deve seguir os princípios RESTful.

Documentar como rodar o projeto.

# Operações
O usuário poderá buscar os fenótipos de uma lista de genes.

A base de genes e fenótipos deve ser retirada deste link:

http://compbio.charite.de/jenkins/job/hpo.annotations.monthly/lastStableBuild/artifact/annotation/ALL_SOURCES_ALL_FREQUENCIES_phenotype_to_genes.txt

Desenvolver crawler para tratar e salvar dados.

A base deve ser armazenada em Postgree.


Exemplo de requsição:

Input: ['ATR', 'GLI3']

Output: 
```
[
  {
    phenotype: [
      {
          hpo: 'HP:0000878',
          name: '11 pairs of ribs'
      }
    ],
    gene:'ATR', 
    gene_id:'545'
  }, 
  {
    phenotype: [
      {
        hpo: 'HP:0001459',
        name: '1-3 toe syndactyly'
      }
    ], 
    gene:'GLI3', 
    gene_id:2737
   }
]
```

Crie uma branch com o seu nome no repositório.

Com o trabalho concluído, nos envie um e-mail com o link da sua branch.

Boa sorte!
