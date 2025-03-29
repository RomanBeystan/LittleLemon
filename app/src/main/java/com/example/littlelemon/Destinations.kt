package com.ilyes.littlelemon

interface Destinations {
    val route: String
}

object Onboarding : Destinations {
    override val route = "Onboarding"
}

object Home : Destinations {
    override val route = "HomeScreen"
}

object Profile : Destinations {
    override val route = "ProfileScreen"
}
