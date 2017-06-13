from django.test import TestCase
from .models import Genes
from rest_framework.test import APIClient
from rest_framework import status
from django.core.urlresolvers import reverse

# Define this after the ModelTestCase
class ViewTestCase(TestCase):
    """Test suite for the api views."""

    def setUp(self):
        """Define the test client and other test variables."""
        self.client = APIClient()
        self.genes_data = {'hpo': 'HP:0001459_teste', 'phenotype':'1-3 toe syndactyly_teste', 'gene': 'GLI3_teste', 'gene_id': '2737_teste'}
        self.response = self.client.post(
            reverse('create'),
            self.genes_data,
            format="json")

    def test_api_can_create_a_gene(self):
        """Test the api has bucket creation capability."""
        self.assertEqual(self.response.status_code, status.HTTP_201_CREATED)

    def test_api_can_get_a_gene(self):
        """Test the api can get a given genes."""
        genes = Genes.objects.get()
        response = self.client.get(
            reverse('details'),
            kwargs={'1': genes.id}, format="json")

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertContains(response, genes)

    def test_api_can_update_genes(self):
        """Test the api can update a given genes."""
        change_genes = {'name': 'Something new'}
        res = self.client.put(
            reverse('details', kwargs={'pk': genes.id}),
            change_genes, format='json'
        )
        self.assertEqual(res.status_code, status.HTTP_200_OK)

    def test_api_can_delete_genes(self):
        """Test the api can delete a genes."""
        genes = Genes.objects.get()
        response = self.client.delete(
            reverse('details', kwargs={'pk': genes.id}),
            format='json',
            follow=True)

        self.assertEquals(response.status_code, status.HTTP_204_NO_CONTENT)