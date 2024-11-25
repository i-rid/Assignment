MVVM Project Structure
.
├── data
│   ├── local
│   │   ├── dao
│   │   │   ├── PostDao.kt
│   │   │   └── RemoteKeysDao.kt
│   │   ├── db
│   │   │   └── AppDatabase.kt
│   │   └── entity
│   │       ├── PostEntity.kt
│   │       └── RemoteKesEntity.kt
│   ├── model
│   │   └── PostsResponse.kt
│   ├── remote
│   │   └── api
│   │       └── ApiService.kt
│   └── repository
│       ├── PagingSource.kt
│       ├── PostLocalRepo.kt
│       ├── PostMediator.kt
│       └── PostRepo.kt
├── di
│   └── LocalInjector.kt
├── MyApplication.kt
├── utils
│   ├── AppUiState.kt
│   └── View.kt
└── view
    ├── adapter
    │   ├── PostAdapter2.kt
    │   └── PostAdapter.kt
    ├── BaseActivity.kt
    ├── fragments
    │   ├── DetailsFragment.kt
    │   ├── HomeFragment.kt
    │   └── LibraryFragment.kt
    └── view_model
        └── PostViewModel.kt
