package ed.maevski.testbalinasoft.view.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.User
import ed.maevski.testbalinasoft.domain.usecases.SignUpUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private var _isSubmit = MutableSharedFlow<Boolean>()
    val isSubmit: SharedFlow<Boolean>
        get() = _isSubmit.asSharedFlow()

    fun signup(user: User) {

        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (user.login.isEmpty()) {
                _isSubmit.emit(true)
            } else {
                val result = signUpUseCase(user)
                _isSubmit.emit(result)
            }
        }
    }

    class Factory(private val signUpUseCase: SignUpUseCase

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
                return SignupViewModel(signUpUseCase = signUpUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}