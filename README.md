# VVProject1
Project1- VV
Déclenchement d'un NullPointerException
    - Utilisation d'une variable non affectée ou initialisée a nul.
    
Le principe de recherche de nullPointerException est le suivant, le déclenchement se faisant sur la lecture d'une variable on recherche la lecture des variables et on va chercher la dernière affectation.
Pour cela on créer au préalable un "workflow" d'un variable (enchaînement de lecture et d'écriture d'une variable).

Cas lors de la recherche de l'affectation précédant :
    - La variable est initialisé ou affecté a nul -> NPE possible.
    - La variable est non initialisé (paramètre de méthode) -> NPE possible.
    - Cas d'affectation dans un IF :
        - si affectation (non nul) dans if AND else -> non NPE possible.
        - si affectation dans if OU else -> recherche de l'affectation précédente.
        - si affectation a nul dans un des deux cas -> NPE possible.
    - Cas d'affectation dans une loop :
        - si affectation a nul -> possible NPE.
        - si affectation (non nul) -> Recherche de l'affectation précédente.
    - Lecture encapsulé dans un if var != nul (pas d'affectation avant dans ce if) -> non NPE possible
