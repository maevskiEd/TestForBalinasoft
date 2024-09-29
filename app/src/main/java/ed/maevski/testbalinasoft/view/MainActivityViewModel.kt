package ed.maevski.testbalinasoft.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.usecases.GetUserNameFromStorageUseCase
import ed.maevski.testbalinasoft.domain.usecases.SaveImageToDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.UploadImageUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase,
    private val saveImageUseCase: SaveImageToDbUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
) : ViewModel() {
    private val _userName = MutableSharedFlow<String>()
    val userName: SharedFlow<String>
        get() = _userName.asSharedFlow()

    private var _isDbUpdating = MutableSharedFlow<Boolean>()
    val isDbUpdating: SharedFlow<Boolean>
        get() = _isDbUpdating.asSharedFlow()

    private var _uriString = MutableSharedFlow<String>()
    val uriString: SharedFlow<String>
        get() = _uriString.asSharedFlow()

    private var _idImage = MutableSharedFlow<Long>()
    val idImage: SharedFlow<Long>
        get() = _idImage.asSharedFlow()

    fun getUserName() {
        viewModelScope.launch {
            val name = getUserNameFromStorageUseCase()
            _userName.emit(name)
        }
    }

    fun saveImageToDb(image: Image) {
        viewModelScope.launch {
            println("saveImageToDb")

            if (saveImageUseCase(image)) {
                println("saveImageToDb -> true")

                _isDbUpdating.emit(true)
            }
        }
    }

    fun upload(image: Image) {
        viewModelScope.launch {
            println("uploadImageUseCase")

            val result = uploadImageUseCase(image)

            if (result.first) {

                println("uploadImageUseCase -> true")

                _idImage.emit(result.second)
            }
        }
    }



    class Factory(
        private val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase,
        private val saveImageUseCase: SaveImageToDbUseCase,
        private val uploadImageUseCase: UploadImageUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(
                    getUserNameFromStorageUseCase = getUserNameFromStorageUseCase,
                    saveImageUseCase = saveImageUseCase,
                    uploadImageUseCase = uploadImageUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}