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
```json
{
  "id":"",
  "name":"",
  "email":"",
  "roles":[],
  "password":""
}
```


### Récupérer un utilisateur
