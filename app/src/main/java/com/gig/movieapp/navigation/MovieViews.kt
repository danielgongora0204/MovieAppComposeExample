package com.gig.movieapp.navigation

enum class MovieViews {
    HomeFragment,
    DetailFragment;

    companion object {
        fun fromRoute(route: String?): MovieViews =
            when (route?.substringBefore("/")) {
                HomeFragment.name -> HomeFragment
                DetailFragment.name -> DetailFragment
                null -> HomeFragment
                else -> throw IllegalArgumentException("Unknown route")
            }
    }
}
