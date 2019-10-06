# Calculator-and-news-app-Android-.kt-
Calculette et application de news en kotlin


CALCULETTE : (app dans le projet)

Au début on utilisait la librairie éval mais notre volonté de faire un mode scientifique nous a contraint à changer de librairie.
Puis nous avons essayer de contraindre au plus l'utilisateur pour eviter les erreurs.
Par exemple -/ n'est pas possible.
Le premier mode se trouve sur une activité et l'autre sur une autre.


NEWS : (example dans le projet )


Avec le spinner on selectionne un catégorie et on contruit une nouvelle query
Attention il faut avoir internet sinon l'appli crash
Quand on click sur le spinner réactualise le fragment
Puis quand on click sur le card switch d'activité et affiche une web view
La première solution était d'ouvrir le navigateur du device
Mais on a choisi de faire une webview plus interressant à faire
On a eu du mal avec les query de retrofit au final on a fini par utiliser Query car Query map nous a donné du fil à retordre 
