# Getting Started

### Reference Documentation

* [Official GitHub documentation](https://docs.github.com/en/rest)


### List of endpoints to use testing my app

* [localhost:8080/repos/{User}]               returns name of every repository that user(owner of the github) has, number of stars in this repository, language and size of the files.
* [localhost:8080/repos/{User}/stars]         returns sum of every star user's(owner of the github) got on GitHub.
* [localhost:8080/repos/{User}/languages]     returns languages used by user(owner of the github), number of repositories wrote in this language and size of files in that specific language.