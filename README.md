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


### Récupérer les languages de codes d'un utilisateur

url : http://vps381611.ovh.net:8080/WTSAPI/users/userlanguages/{id}

méthode: GET


## Code Service

### Créer un code

url: http://vps381611.ovh.net:8080/WTSAPI/codes

méthode: POST

json:
```json
  {
    "code": "texte du code",
    "description": "optionnelle",
    "tags": [
      {
        "tag": "fonction / déclarations / conditions etc"
      },
      {
        "tag": ""
      }
    ],
    "langage": ""
  }

}
```

### Créer un code appartenant à un utilisateur

url: http://vps381611.ovh.net:8080/WTSAPI/codes

méthode: POST

json:
```json
  {
    "code": "",
    "description": "",
    "tags": [
      {
        "tag": ""
      }
    ],
    "langage": "",
    "user": "id de l'utilisateur",
    "visible": "true ou false"
  }
```
