# Alura - Oracle - Challenge ONE: Spring Framework: Fórum Hub

## Challenge Description

> History

Welcome to our latest Challenge Back End challenge!

A forum is a space where all participants on a platform can ask their questions about certain topics. Here at Alura, students use the forum to ask questions about the courses and projects in which they are participating. This magical place is full of lots of learning and collaboration between students, teachers and moderators.

We already know what the forum is for and we know what it looks like, but do we know how it works behind the scenes? That is, where is the information stored? How is the data processed to relate a topic to an answer, or how do users relate to the answers to a topic?

This is our challenge, called ForumHub: in it, we will replicate this process at the back end level and, for this, we will create a REST API using Spring.

Our API will focus specifically on topics, and should allow users to:

- Create a new topic;
- Show all created topics;
- Show a specific topic;
- Update a topic;
- Delete a topic.

This is what we normally know as CRUD (CREATE, READ, UPDATE, DELETE)* and it is very similar to what we developed in LiterAlura, but now in a complete way, adding the UPDATE and DELETE operations, and using a framework that will make it much easier. our work.

*Free translation (in order): Create, Consult, Update and Delete.

In summary, our objective with this challenge is to implement a REST API with the following functionalities:

1. API with routes implemented following the best practices of the REST model;
2. Validations carried out according to business rules;
3. Implementation of a relational database for information persistence;
4. Authentication/authorization service to restrict access to information.

> Development details.

The API entities were developed following the challenge description an business logic.

The Database scheme is described in the image below.
![Forum Hub database diagram](./docs/diagrama_banco_de_dados_forumhub.png)

### Authentication
To use transactional endpoints you must be autenticated with a JWT Bearer Token. To generate the token, go to /auth endpoint and put your credentials. To Register go to /users

**localhost:8080/auth** - Post - *Request Body*
```json
{
  "email": "string",
  "password": "string"
}
```
- email: Must have the email format. Must be unique.
- password: The password must contain at least one uppercase letter, one lowercase letter, and one digit. The password must be between 8 and 20 characters long

**localhost:8080/auth** - Post - *Response* - Code: 200
```json
{
  "token": "string"
}
```
- it should generate a jwt token for you to use in transactional api requests.

### Users

**localhost:8080/users** - Post *Request Body*
```json
{
  "name": "string",
  "email": "string",
  "password": "O~*7Dq![B<F 9unyo9='",
  "profiles": "string"
}
```
- name: is required.
- email: is required. Must have the email format. Must be unique.
- password: is required. The password must contain at least one uppercase letter, one lowercase letter, and one digit. The password must be between 8 and 20 characters long
- profiles: is optional. must be unique or a comma separated string.
- profiles: if you don't want to specify any profiles, the api will set your first profile as "New user" automatically.
- After registered your user, you can interact with transactional endpoints.

**localhost:8080/users** - Post - Response Body - Code: 201
```json
{
  "id": 0,
  "name": "string",
  "email": "string",
  "profiles": [
    {
      "id": 0,
      "name": "string"
    }
  ]
}
```
**localhost:8080/users**
Get
- This will list all users as paginated json.

**localhost:8080/users/{id}**
Put
```json
{
  "name": "string",
  "password": "string",
  "profiles": "string"
}
```
- All put parameters are optional
- You must be authenticated.

**localhost:8080/users/{id}**
Delete
- You must be authenticated as the user you want to delete.

**localhost:8080/users/{id}**
Get
- The id must be a valid user id.
- Show all details of a user, except password.

### Profiles

**localhost:8080/profiles**
Post
```json
{
  "name": "string"
}
```
- name: is required

**localhost:8080/profiles** - Get - *Request*
- This will list all profiles as paginated json.

**localhost:8080/profiles/{id}** - Put - *Request*
```json
{
  "name": "string"
}
```
- You must be authenticated to edit a profile.

**localhost:8080/profiles/{id}** - Delete - *Request*
- You must be authenticated to delete a profile.
- id must be a valid profile id

**localhost:8080/profiles/{id}** - Get - *Request*
- The id must be a valid profile id.
- Show all details of a profile.

### Courses

**localhost:8080/courses** - Post - *Request Body*
```json
{
  "name": "string",
  "category": "PROGRAMMING"
}
```
- name: is required.
- category: is required.
- category must be one of the following: 
    - PROGRAMMING
	- FRONT_END
	- DATA_SCIENCE
	- ARTIFICIAL_INTELLIGENCE
	- DEVOPS
	- UX_DESIGN
	- MOBILE
	- INNOVATION_MANAGEMENT

 **localhost:8080/courses** - Post - *Response*
```json
{
  "id": 0,
  "name": "string",
  "category": "PROGRAMMING"
}
```

**localhost:8080/courses** - Get - *Request*
- This will list all courses as paginated json.

**localhost:8080/courses/{id}** - Put - *Request*
```json
{
  "name": "string",
  "category": "PROGRAMMING"
}
```
- all fields are optional
- id must be a valid course id

**localhost:8080/courses/{id}** - Delete - *Request*
- You must be authenticated to delete a profile.
- id must be a valid course id

### Topics

**localhost:8080/topics** - Post - *Request*
```json
{
  "course_name": "string",
  "title": "string",
  "message": "string"
}
```
- title: is required.
- message: is required.
- course_name: is required.
- title and message must be unique to avoid duplicates.
- you must be authenticated.

**localhost:8080/topics** - Post - *Response*
```json
{
  "id": 0,
  "title": "string",
  "message": "string",
  "created_at": "2024-07-09T20:49:21.065Z",
  "user": "string",
  "status": "ANSWERED",
  "answers": [
    {
      "id": 0,
      "message": "string",
      "topic": "string",
      "creation": "2024-07-09T20:49:21.065Z",
      "user": "string",
      "solution": true
    }
  ]
}
```

**localhost:8080/topics** - Get - *Request*
- This endpoint will list all topics in a paginated json

**localhost:8080/topics** - Put - *Request* 
```json
{
  "courseName": "string",
  "title": "string",
  "message": "string"
}
```
- all parameters are optional.
- you must be authenticated.

**localhost:8080/topics** - Delete - *Request*
- You must be the creator of the topic to delete it.
- you must be authenticated.

**localhost:8080/topics/first-topics** - Get - *Request*
- It will paginate the first 10 topics in asc order.

**localhost:8080/topics/{id}** - Get - *Request*
- The id must be a valid topic id.

### Answers

**localhost:8080/answers** - Post - *Request* 
```json
{
  "message": "string",
  "topicId": 0
}
```
- topicId: is required. Must have to be a valid topic id.
- messages: is requires. Must have to be unique for this topic.
- you must be authenticated.

**localhost:8080/answers** - Post - *Response Body* 
```json
{
  "id": 0,
  "message": "string",
  "topic": "string",
  "creation": "2024-07-09T20:53:59.817Z",
  "user": "string",
  "solution": true
}
```
**localhost:8080/answers** - Get - *Request*
- This endpoint will list all answers in a paginated json

**localhost:8080/answers** - Put - *Request*
```json
{
  "solution": true,
  "message": "string",
  "topicID": 0
}
```
- all parameters are optional.
- topic_id: you can change the topic you want to answer.
- message: it can be changed.
- solution: if this answer solve the topic it should be set to true.
- you must be authenticated.

**localhost:8080/answers** - Put - *Response Body*
```json
{
  "id": 0,
  "message": "string",
  "topic": "string",
  "creation": "2024-07-09T20:51:39.448Z",
  "user": "string",
  "solution": true
}
```

**localhost:8080/answers** - Delete - *Request*
- You must be the creator of the answer.
- you must be authenticated.

**localhost:8080/answers/{id}** - Get - *Request*
- Id must be valid

```json
{
  "id": 0,
  "message": "string",
  "topic": "string",
  "creation": "2024-07-09T20:55:29.401Z",
  "user": "string",
  "solution": true
}
```