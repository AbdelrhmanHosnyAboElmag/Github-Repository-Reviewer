# Github Repository Reviewer Android Project

Welcome to the **Github Repository Reviewer** Android project! This application is designed to help you review and explore Github repositories using the latest Android technologies. In this project, we utilize ViewModel with Jetpack Compose for the user interface, Retrofit for networking, a local database with Room for caching data, offline capabilities, and navigation with Jetpack Compose to provide a seamless user experience. We follow a clean architecture pattern to ensure modularity and maintainability. Below, you'll find an overview of the project's components and features.
![370387070_139034655928864_1898177735220196439_n](https://github.com/AbdelrhmanHosnyAboElmag/Github-Repository-Reviewer/assets/102666930/6d4b1a3f-ca11-4cb9-aef2-fb975e729aad)
![370385092_1112092619767121_2297890372175681873_n](https://github.com/AbdelrhmanHosnyAboElmag/Github-Repository-Reviewer/assets/102666930/da5dee3e-0b0b-4fef-b52f-68e0304d85ba)
![370400410_263511516528883_9142476378244018144_n](https://github.com/AbdelrhmanHosnyAboElmag/Github-Repository-Reviewer/assets/102666930/6a7bb168-91f2-4aff-b963-812d79f7173a)
![370432803_256669757283912_8906006133426400579_n](https://github.com/AbdelrhmanHosnyAboElmag/Github-Repository-Reviewer/assets/102666930/3bb8db38-1401-4eb8-9d23-51a9707053f4)




## Features

1. **ViewModel with Jetpack Compose**: We use the ViewModel architecture component in combination with Jetpack Compose for building the user interface. This allows for efficient UI updates and separation of concerns.

2. **Navigation with Jetpack Compose**: The app incorporates Jetpack Compose's built-in navigation capabilities to navigate between different screens and components within the app. This ensures a smooth user experience and clear app flow.

3. **Retrofit Networking**:
   - **Get Repository**: Interface to fetch repositories from the Github API.
   - **Search for Repository**: Interface to search for repositories based on specific criteria.
   - **Repository Detail**: Interface to retrieve detailed information about a specific repository.
   - **Repository Issue**: Interface to fetch issues related to a repository.

4. **Local Database with Room**:
   - **Repository List Table**: Stores a list of repositories fetched from the network.
   - **Repository Detail Table**: Stores detailed information about repositories, accessible by their unique identifiers.

5. **Data Transfer Objects (DTO)**: We employ DTOs to seamlessly transfer data between the network layer and the local database. These DTOs are transformed into UI models before being displayed.

6. **Offline Capabilities**: The application is designed to work offline seamlessly. Data fetched from the network is cached locally using the Room database. This allows users to access previously fetched data even when they are offline.

7. **Use Cases for Error Handling**: The application employs use cases to handle errors gracefully. This helps manage errors and unexpected situations effectively.

8. **Unit Tests**:
   - Each ViewModel and Use Case has dedicated unit tests to ensure their correctness and functionality.

9. **Integration Tests**:
   - Integration tests are implemented for two Data Access Objects (DAOs): one for retrieving repositories and another for retrieving repository details from the local database.

Thank you for your interest in the **Github Repository Reviewer** Android project. If you have any questions or need assistance, please don't hesitate to reach out to us. Happy coding! 🚀
