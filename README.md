# Assignment

## 📂 MVVV - Project Structure

Here’s an overview of the project structure:

```plaintext
.
├── data
│   ├── local
│   │   ├── dao
│   │   │   ├── PostDao.kt          # Data Access Object for Post
│   │   │   └── RemoteKeysDao.kt    # DAO for pagination metadata
│   │   ├── db
│   │   │   └── AppDatabase.kt      # Room Database definition
│   │   └── entity
│   │       ├── PostEntity.kt       # Entity for Post table
│   │       └── RemoteKeysEntity.kt # Entity for pagination keys
│   ├── model
│   │   └── PostsResponse.kt        # Data class for API response
│   ├── remote
│   │   └── api
│   │       └── ApiService.kt       # Retrofit API interface
│   └── repository
│       ├── PagingSource.kt         # Custom PagingSource for fetching data
│       ├── PostLocalRepo.kt        # Repository for local database operations
│       ├── PostMediator.kt         # RemoteMediator for offline support
│       └── PostRepo.kt             # Centralized repository for data
├── di
│   └── LocalInjector.kt            # Dependency injection setup
├── MyApplication.kt                # Application class for initialization
├── utils
│   ├── AppUiState.kt               # App state management helper
│   └── View.kt                     # Extension functions for views
└── view
    ├── adapter
    │   ├── PostAdapter2.kt         # RecyclerView adapter variant 2
    │   └── PostAdapter.kt          # Main RecyclerView adapter
    ├── BaseActivity.kt             # Base activity for shared functionality
    ├── fragments
    │   ├── DetailsFragment.kt      # Post details screen
    │   ├── HomeFragment.kt         # Main screen with post list
    │   └── LibraryFragment.kt      # Additional feature screen
    └── view_model
        └── PostViewModel.kt        # ViewModel for handling post data
