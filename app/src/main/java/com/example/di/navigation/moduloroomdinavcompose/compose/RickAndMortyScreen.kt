package com.example.di.navigation.moduloroomdinavcompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.di.navigation.moduloroomdinavcompose.api.graphql3.CountriesListQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql4.CharactersListQuery


@Composable
internal fun  RickAndMortyScreen (characters: List<CharactersListQuery.Result?>?, onLoad: () -> Unit,
                                  onNavigateTo: () -> Unit= {}
)  {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp)
    ){

        LazyColumn {
            item{
                Button(onClick = { onLoad() }) {
                    Text(text = "TwelveGraphqlScreen. OnLoad with ViewModel ")
                }
            }

            item{
                Text(text = "Listado API" )
            }

            if(characters != null){

                characters.forEach { character ->

                    item{
                        Column{
                            Text(text = "Personaje: ${character?.name}")

                            Divider()
                        }
                    }
                }
            }
        }

    }


}