package com.example.di.navigation.moduloroomdinavcompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.CompanyDetailsQuery

@Composable
internal fun  SpaceXScreen (uiState: CompanyDetailsQuery.Company, onLoad: () -> Unit,
                                   onNavigateTo: () -> Unit= {}
)  {
    Column {
        Button(onClick = { onLoad() }) {
            Text(text = "TwelveGraphqlScreen. OnLoad with ViewModel ")
        }

        Text(text = "Listado API" )
        if(uiState != null){
            Text(text = uiState.employees.toString() )
            Text(text = uiState.coo.toString() )
            Text(text = uiState.founder.toString() )

            Divider()
        }
    }
}