# Grid List and Bottom Sheet

## Overview

Android application that displays a list of images with description. The data for these images is fetched from [this API](https://static.leboncoin.fr/img/shared/technical-test.json).
[Demo Video](Screen_recording_20231001_181328.gif)

<img src="demo-video.gif" alt="Demo" width="300">

## Architecture

The app uses **Clean Architecture principles + MVVM Pattern**, for  a clear separation of concerns across these layers:

### Data Layer
Focused on data management, it uses:
- **Room Database** for local data storage.
- **Retrofit2** for API interactions.

Components include:
- Repositories
- Mappers
- Local data storage modules
- Remote data retrieval modules

### Domain Layer
Encapsulates the app's core logic and functionalities with:
- Use cases (e.g., Fetching a list of images)
- Domain models
- Data repository interfaces

### Presentation Layer
Handles the user interface and interactions with:
- ViewModels for UI logic.
- UI Screens designed using Jetpack Compose.
- Utility functions.
- Reusable UI components also built with Jetpack Compose.

## Configuration Change

The app handles configuration changes like screen rotations using:

- **ViewModel**: Ensure that ViewModel retains data across configuration changes. Also, annotated with *@HiltViewModel*, which means it's managed by Hilt and will survive configuration changes.
- **State**: using StateFlow and MutableState to manage the state in the ViewModel.
- **Jetpack Compose**: Provides a more intuitive way to handle UI state without the complexities of traditional Android UI development.

## Libraries

The application integrates a variety of libraries:

- **Room**: Database operations.
- **Dagger/Hilt**: Dependency injection.
- **Retrofit2 + GsonConverter**: API interactions and JSON data processing.
- **Coroutine**: Asynchronous operations.
- **Jetpack Compose/Material3**: UI development.
- **Coil**: Image loading in Jetpack Compose.
- **JUnit and Mockito**: Unit testing and data mocking.

## Testing

Unit tests are in place to validate the app's functionality:

- **ViewModels**
- **common and Utils**

## To improve
- Load data using pagination
- Persist data into local database only when there is new update (e.g; condition based on a dateTime value returned with api that contains last update time)


