# Blockchain Mkt

## :scroll: Description
This application focus on using dynamic feature along with Hilt / Dagger2 to request Blockchain data.

## :package: Architecture organization
The project is based on a clean architecture with clear separation among the Data, Domain, and UI layers

- app-blockchain-mkt
    - app
        - app (UI / Main)
        - data (It is a centralized data module to be used by the other data layers)
    - charts
        - charts (UI)
        - data (Repository)
        - domain (Business Logic)
    - pools
        - data (Repository)
        - domain (Business Logic)
        - pools (UI)
    - stats
        - data (Repository)
        - domain (Business Logic)
        - stats (UI)

PS: All mentioned packages contains unit test coverage except the `app/app/data`

## :floppy_disk: Tech-stack
The code is mainly based on the following concepts/libraries:
* Clean architecture
* MVVM
* Dynamic features (Charts, Pools, and Stats are each one dynamic feature)
* Dependency injection with Hilt (app module) and Dagger2 (feature modules)
* Coroutines (Data, Domain and View model layers)
* LiveData (View model / fragment)
* MPAndroidChart (Graphs and charts)
* Animations using Lottie
* And unit testing using JUnit, Jupiter and mockk

## :pushpin: Tech-debt
* Instrumented tests

## :camera_flash: Preview

<img src="/preview/preview_complete_flow.gif" width="260">&emsp;<img src="/preview/preview_error.gif" width="260">
