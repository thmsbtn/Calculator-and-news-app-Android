# Calculator-and-news-app-Android-.kt-
Calculette et application de news en kotlin


CALCULETTE :
Elle possède deux modes. Le mode normal coloré et le mode scientifique ont switch entre eux avec le bouton en bas à gauche.
Les boutons se bloc pour éviter des erreurs.
par exemple on ne peut marquer -/ donc on bloc les touches / et * pour eviter les erreurs
Le but etant de contraindre au plus l'utilisateur pour eviter les erreurs
On utilise une librairie qui gère les sqrt et mode scientifique et gère les multiplications implicites
Toutes les librairies se trouve dans le graddle.



NEWS : 
Avec le spinner on selectionne un catégorie et on contruit une nouvelle query
Attention il faut avoir internet sinon l'appli crash
quand on click sur le spinner réactualise le fragment
puis quand on click sur le card sxitch d'activité et affiche une web view
