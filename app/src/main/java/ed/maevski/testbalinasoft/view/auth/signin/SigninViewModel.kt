package ed.maevski.testbalinasoft.view.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.User
import ed.maevski.testbalinasoft.domain.usecases.SignInUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SigninViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {

    private var _isSubmit = MutableSharedFlow<Boolean>()
    val isSubmit: SharedFlow<Boolean>
        get() = _isSubmit.asSharedFlow()

    fun signin(user: User) {

        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (user.login?.isEmpty() == true) {
                _isSubmit.emit(true)
            } else {
                val result = signInUseCase(user)
                _isSubmit.emit(result)
            }
        }
    }

    class Factory(private val signInUseCase: SignInUseCase

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SigninViewModel::class.java)) {
                return SigninViewModel(signInUseCase = signInUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}