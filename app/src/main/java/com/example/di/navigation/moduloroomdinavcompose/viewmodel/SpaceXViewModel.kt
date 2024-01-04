package com.example.di.navigation.moduloroomdinavcompose.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.example.di.navigation.moduloroomdinavcompose.api.graphql.LaunchDetailsQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.CompanyDetailsQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpaceXViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
)  : ViewModel(){

    val defaultCompany= CompanyDetailsQuery.Company(employees = 0, founder = "", coo = "")

    val _data= MutableStateFlow<CompanyDetailsQuery.Company>(defaultCompany)
    val data=_data.asStateFlow()

    val apolloClient = ApolloClient.builder()
        .serverUrl("https://spacex-production.up.railway.app/")
        .build()

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //apolloClient.query(LaunchDetailsQuery(id = "83")).toDeferred().await()
                val result=apolloClient.query(CompanyDetailsQuery()).toDeferred().await()
                val launch = result.data?.company
                if (launch == null || result.hasErrors()) {
                    Log.d(TAG,"Error  on result")
                }else{
                    result.data?.company?.let {
                        _data.value=it
                    }
                }
            } catch (e: ApolloException) {
                Log.d(TAG,"General error", e)
            }
        }
    }

    companion object{
        const val TAG="SpaceXViewModel"
    }
}