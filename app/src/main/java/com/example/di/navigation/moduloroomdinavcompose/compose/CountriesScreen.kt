package com.example.di.navigation.moduloroomdinavcompose.compose


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.di.navigation.moduloroomdinavcompose.api.dto.Result

import com.example.di.navigation.moduloroomdinavcompose.api.graphql3.CountriesListQuery

@Composable
internal fun  CountriesScreen (countries: List<CountriesListQuery.Country>, onLoad: () -> Unit,
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

            if(countries != null){

                countries.forEach { country ->

                    item{
                        Column{
                            Text(text = "Code: ${country.code}")
                            Text(text = "Name: ${country.name}")
                            Text(text = "Emoji: ${country.emoji}")

                            Divider()
                        }
                    }
                }
            }
        }
        
    }


}


