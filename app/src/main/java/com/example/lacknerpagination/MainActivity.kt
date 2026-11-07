package com.example.lacknerpagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lacknerpagination.presentation.screens.BeerScreen
import com.example.lacknerpagination.presentation.view_model.BeerViewModel
import com.example.lacknerpagination.ui.theme.LacknerPaginationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LacknerPaginationTheme {
                val viewModel = hiltViewModel<BeerViewModel>()
                val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BeerScreen(
                        beers = beers,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LacknerPaginationTheme {
        Greeting("Android")
    }
}