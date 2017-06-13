from rest_framework import serializers
from .models import Genes

class GenesSerializer(serializers.ModelSerializer):
    """Serializer to map the Model instance into JSON format."""

    class Meta:
        """Meta class to map serializer's fields with the model fields."""
        model = Genes
        fields = ('hpo', 'phenotype', 'gene', 'gene_id')