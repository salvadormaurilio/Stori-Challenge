# Stori Login Challenge
Example of Sing Up and Sign In with Firebase

## :scroll: Motivation and Context

This project simulates the Sign Up and Sign in of a user, things to consider:
- For Sing In and Sign Auth, was used Firebase Authentication
- All Userdata is saved in Firestore
- For Sign Up and Sing in, there is validation of the fields, like email, passord, etc.


## :paperclip:  Technologies and topics used

### Technologies
- Kotlin
- Corrutines like Flow
- Hilt
- Firebase
- Compose

#### For testing:
- JUnit
- Mockito
- Hamcrest
- Corrutines test

### Topics
- Clean Architecture
- Clean Code
- SOLID
- MVVM patten
- Repository pattern
- Dependency injection

## :art: UI Test

### Auth

<img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/43ffd9bb-e299-4631-8e9f-c43ec8cb2628" width="320">

### Sign Up

Sign Up Success | Some Fiel is Wrong | User al readey Exist
--- | ---  | --- 
<img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/71c255a7-aa8b-4151-b692-cc921c3785d4" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/3292f148-c6dd-4745-9a7c-53c689b8ad4a" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/ae4ecbaa-9b17-48f2-a065-180eec69a7cf" width="320">

### Sign In

Sign In Success | Some Fiel is Wrong | User Invalid
--- | ---  | --- 
<img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/f15e9f3e-ab73-4ed1-a526-1e7346b1f9b0" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/400ffd0e-0691-4b27-a1fa-d455642651ce" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/e2dc25c2-b10b-44e2-897e-b4308179b23b" width="320">


## :green_heart: How did you test it?

To test and see movements you can use the following credentials

```
email: salvador@stori.mx
password: Admin_123
```
You can also create a user from scratch to test Sign Up


**Also you can run the Unit Tests, recommended command:**

```
/gradlew test
```


