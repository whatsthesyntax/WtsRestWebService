# What's The Syntax - Rest API Documentation

Cette api permet de créer, récupérer et modifer des utilisateurs ainsi que des codes.
Dans les fonctionnalités citées ci-dessous, tous les échanges de données se font en format JSON.

## User Service

### Créer un utilisateur (inscription)

url : http://vps381611.ovh.net:8080/WTSAPI/users

méthode: POST

json: 
```json
{
  "name":"",
  "email":"",
  "password":""
}
```

### Authentification

url : http://vps381611.ovh.net:8080/WTSAPI/login

méthode: POST

Il faut spécifier dans l'en-tête HTTP le mot clé "Authorization" suivi de la méthode utilisée "Basic" puis
de la représentation en Base64 du nom de l'utilisateur et du mot de passe séparés par deux points ":" .
Par exemple: `Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==` envoi les identifiants `Aladdin:open sesame`

Si l'authentification passe, un objet json est retourné, contenant les informations de l'utilisateur.
Sinon, le serveur retourne une erreur 401 Unauthorized.
```json
{
  "id":"",
  "name":"",
  "email":"",
  "roles":[],
  "password":""
}
```
### Récupérer tous les utilisateurs

url : http://vps381611.ovh.net:8080/WTSAPI/users/

méthode: GET

Retourne en format json la liste de tous les utilisateurs qui existent dans la base de données.


### Récupérer un utilisateur

url : http://vps381611.ovh.net:8080/WTSAPI/users/{id}

méthode: GET

Retourne en format json l'utilisateur dont l'id est spécifié dans l'url. si l'utilisateur n'existe pas, le résultat est vide.

### Modifier un utilisateur

url : http://vps381611.ovh.net:8080/WTSAPI/users

méthode: PUT

json: par exemple pour modifier le nom d'un utilisateur:
```json
{
  "id":"",
  "name":""
}
```

### Supprimer un utilisateur

url : http://vps381611.ovh.net:8080/WTSAPI/users/{id}

méthode: DELETE

Supprime l'utilisateur dont l'id est spécifié dans l'url.


### Récupérer les codes d'un utilisateur

url : http://vps381611.ovh.net:8080/WTSAPI/users/usercodes/{id}

méthode: GET


### Récupérer les langages des codes d'un utilisateur

url : http://vps381611.ovh.net:8080/WTSAPI/users/userlanguages/{id}

méthode: GET


## Code Service

### Créer un code

url: http://vps381611.ovh.net:8080/WTSAPI/codes

méthode: POST

json: 
```json
{
   "code":
      "code": "texte du code",
      "description": "optionnelle"
   },
   "tags": [
     {
       "tag": ""
     }
   ],
   "langage":{
       "langage":""
   }
}
```

### Créer un code appartenant à un utilisateur (code personnel)

url: http://vps381611.ovh.net:8080/WTSAPI/codes

méthode: POST

json: il faut rensigner l'id de l'utilisateur connecté, ainsi qu'un boolean "visible" pour indiquer si le code est publique ou privé.
```json
{
    "code":{
      "code": "",
      "description": ""
    },
    "tags": [
      {
        "tag": ""
      }
    ],
    "langage":{
        "langage":""
    },
    "userid": "",
    "visible": true
}
```

### Modifier un code perso

url: http://vps381611.ovh.net:8080/WTSAPI/codes

méthode: PUT

json: reprendre le format que retourne un GET http://vps381611.ovh.net:8080/WTSAPI/codes/{id} et modifier le contenu du code
```json
{
  "codeId": 36,
  "code": "for ($i = 1; $i <= 30; $i++){\n    echo $i;\n}",
  "description": "",
  "tags": [
    {
      "tagId": 25,
      "tag": "for"
    },
    {
      "tagId": 3,
      "tag": "boucle"
    }
  ],
  "langage": {
    "langageId": 3,
    "langage": "php"
  },
  "user": {
    "userId": 999,
    "email": "",
    "password": "",
    "roles": [],
    "name": ""
  },
  "visible": false,
  "valide": false
}
```



### Récupérer les codes d'un utilisateur

Cette fonction est différente de la première car seul l'administrateur y aura accès pour avoir une vue sur tous les codes, les filtrer par utilisateur et contrôler des codes qui ne sont pas encore publiques par exemple.

url: http://vps381611.ovh.net:8080/WTSAPI/codes/user/{id}

méthode: GET

json: le résultat est une liste de codes appartenant à l'itilisateur dont l'id est passé en paramètre
```json
[
  {
    "codeId": 1,
    "code": "",
    "description": "",
    "tags": [
      {
        "tagId": 1,
        "tag": ""
      }
    ],
    "langage": {
      "langageId": 1,
      "langage": ""
    },
    "user": {
      "userId": 134,
      "email": "",
      "password": "",
      "roles": [
        {
          "roleId": 1,
          "role": "User"
        }
      ],
      "name": ""
    },
    "visible": false
  },
  {
    "codeId": 2,
    "code": "",
    "description": "",
    "tags": [
      1
    ],
    "langage": 1,
    "user": 134,
    "visible": true
  }
]

```

### Recherche par tags et langage

url: http://vps381611.ovh.net:8080/WTSAPI/search

méthode: POST

text/plain: poster un simple texte contenant des mots clés séparés par des espaces. Exemple: "java boucle".



### Valider un code

url: http://vps381611.ovh.net:8080/WTSAPI/codes/validate/{id}

méthode: POST

{id} : identifiant du code.
Retourne: true si tout se passe bien, false sinon.


### Rendre un code publique (visible=true)

url: http://vps381611.ovh.net:8080/WTSAPI/codes/show/{id}

méthode: POST


{id} : identifiant du code.
Retourne: true si tout se passe bien, false sinon.



### Rendre un code privé


url: http://vps381611.ovh.net:8080/WTSAPI/codes/hide/{id}

méthode: POST


{id} : identifiant du code.
Retourne: true si tout se passe bien, false sinon.



## Langages

### Récupérer tous les langages

url: http://vps381611.ovh.net:8080/WTSAPI/langages

méthode: Get

json: Retourne une liste de langages
```json
[
  {
    "langageId": 1,
    "langage": "java"
  },
  {
    "langageId": 2,
    "langage": "php"
  },
  {
    "langageId": 3,
    "langage": "c"
  }
]

```


### Créer un langage

url: http://vps381611.ovh.net:8080/WTSAPI/langages

méthode: POST

json:
```json
{
    "langage":""
}

```

## Tags

### Récupérer tous les tags

url: http://vps381611.ovh.net:8080/WTSAPI/tags

méthode: GET

json: Retourne une liste de tags
```json
[
  {
    "tagId": 1,
    "tag": "boucle"
  },
  {
    "tagId": 2,
    "tag": "fonction"
  },
  {
    "tagId": 3,
    "tag": "condition"
  }
]
```

### Créer un tag

url: http://vps381611.ovh.net:8080/WTSAPI/tags

méthode: POST

json:
```json
{
  "tag":""
}
```







