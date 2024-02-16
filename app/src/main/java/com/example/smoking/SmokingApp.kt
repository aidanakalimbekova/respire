package com.example.smoking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smoking.ui.theme.SmokingTheme
import com.example.smoking.ui.theme.home.HomeScreen
import com.example.smoking.ui.theme.home.HomeViewModel
import com.example.smoking.ui.theme.profile.ProfileScreen
import com.example.smoking.ui.theme.quest.QuestScreen
import com.example.smoking.ui.theme.statistics.StatisticsScreen


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Statistics : BottomNavItem("statistics", Icons.Default.Insights, "Statistics")
    object Quest : BottomNavItem("quest", Icons.Default.Leaderboard, "Quest")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
//@Preview
@Composable
fun SmokingApp() {
    SmokingTheme {
        val smokeNavController = rememberNavController()
        Scaffold (modifier = Modifier.fillMaxSize(), bottomBar = {BottomNavigationBar(smokeNavController)}  ) {
            NavHost(
                navController = smokeNavController,
                startDestination = BottomNavItem.Home.route,
                Modifier.padding(it)
            ) {
//            navigation(startDestination = "login", route = "auth"){
//                composable("login"){
//
//                    Button(onClick = {
//                        smokeNavController.navigate(""){
//                            popUpTo("auth"){inclusive=true}
//                        }
//                    }){
//
//                    }
//
//                }
//                composable("register"){}
//            }

                composable(BottomNavItem.Home.route){
                    HomeScreen(viewModel = HomeViewModel())
                }
                composable(BottomNavItem.Statistics.route){
                    StatisticsScreen()
                }
                composable(BottomNavItem.Quest.route){
                    QuestScreen()
                }
                composable(BottomNavItem.Profile.route){
                    ProfileScreen()
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Statistics,
        BottomNavItem.Quest,
        BottomNavItem.Profile,
    )
    NavigationBar (containerColor = Color.Transparent){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach {
            item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }

    }
}