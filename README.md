# Documentation API Ollama

## Model Dora :

    - /model/askDora
        - méthode : POST , paramètre requis : “question”
    => Prend le prompt du “prompt.txt” ainsi que le paramètre “question” du POST .
=> Retourne une réponse du prompt et de la question.

## Model libre :

    - “api/conversations/getAll”
        - méthode : GET
    => Retourne toutes les discussions existantes

    - “api/conversations/create”
        - méthode : POST , paramètre requis : “prompt”
    => Prend le prompt donné
=> Créé une nouvelle discussion et retourne une réponse du prompt donné

    - “api/conversations/{conversationId}/messages”
        - méthode : POST , paramètre requis : “prompt”, variable url requise : ID de discussion
    => Prend le prompt donné dans la discussion choisie
=> Retourne une réponse du prompt donnée dans la discussion choisie