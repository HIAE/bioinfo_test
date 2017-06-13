from django.db import models

class Genes(models.Model):
    """This class represents the genes model."""
    hpo = models.CharField(max_length=255, blank=True)
    phenotype = models.CharField(max_length=255, blank=True)
    gene = models.CharField(max_length=255, blank=True)
    gene_id = models.CharField(max_length=255, blank=True)

    def __str__(self):
        """Return a human readable representation of the model instance."""
        return "{}".format(self)