package com.example.di.navigation.moduloroomdinavcompose.compose


import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import com.example.di.navigation.moduloroomdinavcompose.api.dto.QuoteList
import com.example.di.navigation.moduloroomdinavcompose.api.graphql.LaunchDetailsQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.CompanyDetailsQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql3.CountriesListQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql4.CharactersListQuery
import com.example.di.navigation.moduloroomdinavcompose.model.TenthDataClass
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.*


interface MyDestination {
    val icon: ImageVector
    val route: String
}

object First : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "FirstScreen"
}

object Second : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "SecondScreen"
}

object Threeth : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "ThreethScreen"
}


object Fourth : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "FourthScreen"
}

object Form : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "FormScreen"
}

object Sixth : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "Sixth"
}

object Seventh : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "Seventh"
}

object Eighth : MyDestination{

    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val icon = Icons.Filled.PieChart
    override val route = "Eighth"
    const val myIdTypeArg = "myIdTypeArg"
    val routeWithArgs = "$route/{$myIdTypeArg}"
    val arguments = listOf(
        navArgument(myIdTypeArg) { type = NavType.StringType }
    )
}

object Nineth : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "Nineth"
}

object Tenth : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "Tenth"
    const val myIdTypeArg = tenthViewModelIdArg

    val routeWithArgs = "${route}/{${myIdTypeArg}}"
}

object EleventhApi : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "ElevenApi"
}

object TwelveGraphQL : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "TwelveGraphQL"
}
object SpaceX : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "SpaceX"
}
object Countries : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "Countries"
}
object RickAndMorty : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "RickAndMorty"
}

// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.TenthDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: (conversationId: String) -> Unit= {}
) {
    composable(route=Tenth.routeWithArgs) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: TenthViewModel = hiltViewModel<TenthViewModel>()
        val uiState: State<TenthDataClass> = viewModel.uiState.collectAsStateWithLifecycle()
        TenthScreen(
            uiState,
            onDelete = viewModel::onDelete,
            onNavigateToList
        )
    }
}


fun NavHostController.navigateToTenth(argFromUser: String) {
    this.navigateSingleTopTo("${Tenth.route}/$argFromUser")
}


// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.EleventhDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: (conversationId: String) -> Unit= {}
) {
    composable(route=EleventhApi.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: ElevenApiViewModel = hiltViewModel<ElevenApiViewModel>()
        val uiState: State<QuoteList> = viewModel.apiResult.collectAsStateWithLifecycle()
        ElevenApiScreen(
            uiState.value,
            onLoad = viewModel::loadData,
            onNavigateToList
        )
    }
}


// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.TwelveDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: () -> Unit= {}
) {
    composable(route=TwelveGraphQL.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: TwelveGraphQLViewModel = hiltViewModel<TwelveGraphQLViewModel>()
        val uiState: State<LaunchDetailsQuery.Launch> = viewModel.data.collectAsStateWithLifecycle()
        TwelveGraphqlScreen(
            uiState.value,
            onLoad = viewModel::getData,
            onNavigateToList
        )
    }
}

// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.SpaceXDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: () -> Unit= {}
) {
    composable(route=SpaceX.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: SpaceXViewModel = hiltViewModel<SpaceXViewModel>()
        val uiState: State<CompanyDetailsQuery.Company> = viewModel.data.collectAsStateWithLifecycle()
        SpaceXScreen(
            uiState.value,
            onLoad = viewModel::getData,
            onNavigateToList
        )
    }
}

// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.CountriesDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: () -> Unit= {}
) {
    composable(route=Countries.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: CountriesViewModel = hiltViewModel<CountriesViewModel>()
        val uiState: State<List<CountriesListQuery.Country>> = viewModel.data.collectAsStateWithLifecycle()
        CountriesScreen(
            uiState.value,
            onLoad = viewModel::getData,
            onNavigateToList
        )
    }
}

// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.RickAndMortyDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: () -> Unit= {}
) {
    composable(route=RickAndMorty.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: RickAndMortyViewModel = hiltViewModel<RickAndMortyViewModel>()
        val uiState: State<List<CharactersListQuery.Result?>?> = viewModel.data.collectAsStateWithLifecycle()
        RickAndMortyScreen(
            uiState.value,
            onLoad = viewModel::getData,
            onNavigateToList
        )
    }
}

fun NavHostController.navigateToEleventh() {
    this.navigateSingleTopTo("${EleventhApi.route}")
}

object MainGraph:MyDestination{
    override val icon = Icons.Filled.PieChart
    override val route = "MainGraph"

}

val myListOfIdDestination= listOf(First,Second,Threeth,Fourth)

fun NavGraphBuilder.mainGraph(navController: NavHostController) {


    composable(First.route) {

        FirstScreen(
            onClickNavigateTo ={
                Log.d("mainGraph","FirstScreen onClickNavigateTo mainGraph")
                //navController.navigate(Second.route)

                navController.navigateSingleTopTo(Second.route)
            }
        )
    }

    composable(Second.route) {
        SecondScreen {
            Log.d("mainGraph","SecondScreen onClickNavigateTo mainGraph")
            //navController.navigate(First.route)

            navController.navigateSingleTopTo(Threeth.route)
        }
    }



    composable(Threeth.route) { backStackEntry ->
        // Creates a ViewModel from the current BackStackEntry
        // Available in the androidx.hilt:hilt-navigation-compose artifact
        val viewModel = hiltViewModel<MyViewModel>()

        ThreethScreen(myViewModel = viewModel) {
            Log.d("mainGraph","ThreethScreen onClickNavigateTo mainGraph")
            //navController.navigate(First.route)

            navController.navigateSingleTopTo(Fourth.route)
        }
    }

    composable(Fourth.route){ backStackEntry ->
        val viewModel = hiltViewModel<UserViewModel>()
        FourthScreen(viewModel,
            {
                Log.d("mainGraph","FourthScreen onClickNavigateTo mainGraph")
                //navController.navigate(First.route)
                navController.navigateSingleTopTo(First.route)

            },
            {
                Log.d("mainGraph","FourthScreen onClickNavigateTo FormGraph")
                //navController.navigate(First.route)
                navController.navigateSingleTopTo(Form.route)

            })

    }

    composable(Form.route){ backStackEntry ->
        val viewModel = hiltViewModel<UserViewModel>()
        Form(viewModel) {
            Log.d("formScreen", "FormScreen onClickNavigateTo mainGraph")
            //navController.navigate(First.route)

            navController.navigateSingleTopTo(Fourth.route)

        }

    }


    composable(Sixth.route){ backStackEntry ->
        //val viewModel = hiltViewModel<UserViewModel>()
        SixthScreen()

    }

    composable(Seventh.route){ backStackEntry ->

        SeventhScreen(){ argFromUser->
            navController.navigateSingleTopTo(
                "${Eighth.route}/$argFromUser")
        }

    }

    composable(
            route = Eighth.routeWithArgs,
            arguments = Eighth.arguments,
        ) { navBackStackEntry ->
            val currentArgument =
                navBackStackEntry.arguments?.getString(Eighth.myIdTypeArg)
        EighthScreen(currentArgument)
    }


    composable(Nineth.route){ backStackEntry ->

        NinethScreen(){ argFromUser->
            navController.navigateToTenth(argFromUser)
        }

    }

    TenthDestination()

    EleventhDestination()

    TwelveDestination()

    SpaceXDestination()

    CountriesDestination()

    RickAndMortyDestination()
}



fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
