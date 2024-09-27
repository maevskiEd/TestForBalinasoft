package ed.maevski.testbalinasoft.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.usecases.GetUserNameFromStorageUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
) : ViewModel() {
    private val _userName = MutableSharedFlow<String>()
    val userName: SharedFlow<String>
        get() = _userName.asSharedFlow()

    fun getUserName() {
        viewModelScope.launch {
            val name = getUserNameFromStorageUseCase()
            _userName.emit(name)
        }
    }

    class Factory(
        private val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(
                    getUserNameFromStorageUseCase = getUserNameFromStorageUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}