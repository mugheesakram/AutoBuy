# Exercise project

Hi, I am Mughees Akram. Here is my best try to showcase my skills and understanding of best
practices.

# Tech Stack

In this project, I have used following:

- MVVM
- Retrofit
- Room
- Coroutines
- Hilt
- Paging 3
- Kotlin DSL for gradle.

## Architecture

I have structured the architecture keeping in mind the SOLID principles and clean architecture.
Initially, I started with the architecture base that I have developed previously, and then, while,
keeping in mind these principles, I improvised and improved the overall structure and flow.

## MVVM

For MVVM, I initially went with Data binding and thats why I was using 4 file structure for each
screen which are:

- Activity
- Fragment
- ViewModel
- Interface

## Unit tests
I have tried to cover the domain of the application.
## UI

I have kept UI of this application really simple. I have not implemented material theme that much.

## Gradle

For gradle, I have used Kotlin DSL. For me, It keeps gradle dependencies neat and more
understandable.

## How to run
Create a properties file in buildSrc folder and name it apikeys.properties
ADD two variables in it: 
BASE_URL = add url till v1/
API_SECRET_KEY = API secret key goes here
Just simply import this project with your version Control and run. I am sure it will not produce any bug.
