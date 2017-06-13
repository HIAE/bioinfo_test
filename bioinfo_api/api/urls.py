from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from .views import CreateView
from .views import DetailsView 
from .views import PhenotypeList 
from .views import GenesList 

urlpatterns = {
    url(r'^genes/$', CreateView.as_view(), name="create"),
    url(r'^geneslist/$', GenesList.as_view(), name="gene"),
    url(r'^genes/(?P<pk>[0-9]+)/$',
        DetailsView.as_view(), name="details"),
    url(r'^phenotypes/$',
        PhenotypeList.as_view(), name="gene"),
}

urlpatterns = format_suffix_patterns(urlpatterns)