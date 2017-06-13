from django.shortcuts import render
from rest_framework import generics
from .serializers import GenesSerializer
from .models import Genes
from django_filters.rest_framework import DjangoFilterBackend
import ast

class CreateView(generics.ListCreateAPIView):
    """This class defines the create behavior of our rest api."""
    queryset = Genes.objects.all()
    serializer_class = GenesSerializer

    def perform_create(self, serializer):
        """Save the post data when creating a new gene."""
        serializer.save()

class DetailsView(generics.RetrieveUpdateDestroyAPIView):
    """This class handles the http GET, PUT and DELETE requests."""

    queryset = Genes.objects.all()
    serializer_class = GenesSerializer

class PhenotypeList(generics.ListAPIView):
    serializer_class = GenesSerializer
    filter_fields = ('gene',)

    def get_queryset(self):
        """
        This view should return a list of all the phenotypes for
        the gene as determined by the gene portion of the URL.
        """
        gene_value = self.request.query_params.get('gene', None)
        if gene_value:
            gene_list = ast.literal_eval(gene_value)
            queryset = Genes.objects.filter(gene__in=gene_list)

        return queryset

class GenesList(generics.ListAPIView):
    queryset = Genes.objects.all()
    serializer_class = GenesSerializer
    filter_backends = (DjangoFilterBackend,)
    filter_fields = ('gene', 'hpo')