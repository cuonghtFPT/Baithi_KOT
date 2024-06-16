package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class ROUTER {
    welcom,
    list,
    add,
    update
}

@Composable
fun GetLayoutNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTER.welcom.name) {
        composable(ROUTER.welcom.name) {
            GetWelcom(navController = navController)
        }
        composable(ROUTER.list.name) {
            GetLayoutList(navController = navController)
        }
        composable(ROUTER.add.name) {
            GetAddXehoi(navController = navController)
        }
        composable(
            "${ROUTER.update.name}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            GetLayoutUpdateXeHoi(navController, id)
        }
    }
}
