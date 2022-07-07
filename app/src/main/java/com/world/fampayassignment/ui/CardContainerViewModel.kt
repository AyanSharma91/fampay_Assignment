package com.world.fampayassignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.world.fampayassignment.model.CardModel
import com.world.fampayassignment.repository.CardDataRepository
import com.world.fampayassignment.sealedClasses.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardContainerViewModel
@Inject
constructor(
    var repository: CardDataRepository
) : ViewModel()
{

    private  val _dataState = MutableStateFlow<MainStateEvent>(MainStateEvent.None)
    var dataState : StateFlow<MainStateEvent> = _dataState


    fun getCardDataDetails() {
        viewModelScope.launch {
            _dataState.value = MainStateEvent.Loading
            when (val dataDetails = repository.getCardData()) {
                is DataState.Error -> _dataState.value =
                    MainStateEvent.Failure(dataDetails.message!!)
                is DataState.Success -> {
                    _dataState.value = MainStateEvent.Success(dataDetails.data!!)
                }
            }
        }
    }



//    Just a separate class to handle the result in view Model
    sealed class MainStateEvent {
        class Success(val result : CardModel) : MainStateEvent()
        class Failure(val errorText: String) : MainStateEvent()
        object Loading : MainStateEvent()
        object None : MainStateEvent()
    }
}