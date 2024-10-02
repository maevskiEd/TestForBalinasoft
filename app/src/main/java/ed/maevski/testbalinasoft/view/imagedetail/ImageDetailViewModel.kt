package ed.maevski.testbalinasoft.view.imagedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.Comment
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.usecases.DelCommentApiUseCase
import ed.maevski.testbalinasoft.domain.usecases.DownloadCommentsUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetCommentsByIdImageFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetImageByIdFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.SendCommentUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ImageDetailViewModel(
    private val getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase,
    private val sendCommentUseCase: SendCommentUseCase,
    private val downloadCommentsUseCase: DownloadCommentsUseCase,
    private val delCommentApiUseCase: DelCommentApiUseCase,
    private val getCommentsByIdImageFromDbUseCase: GetCommentsByIdImageFromDbUseCase,
) : ViewModel() {
    var imageId = 0

    private var _image = MutableSharedFlow<Image>()
    val image: SharedFlow<Image>
        get() = _image.asSharedFlow()

    private var _comments = MutableSharedFlow<List<Comment>>()
    val comments: SharedFlow<List<Comment>>
        get() = _comments.asSharedFlow()

    private var _idComment = MutableSharedFlow<Int>()
    val idComment: SharedFlow<Int>
        get() = _idComment.asSharedFlow()

    fun getImageById(id: Int) {
        viewModelScope.launch {
            val im = getImageByIdFromDbUseCase(id)
            getComments(id)
            println("ImageDetailViewModel getImageById $im")

            _image.emit(im)
        }
    }

    fun sendComment(comment: Comment) {
        viewModelScope.launch {
            if (sendCommentUseCase(comment)) getComments(imageId)
        }
    }

    fun getComments(id: Int) {
        viewModelScope.launch {
            if (downloadCommentsUseCase(id)) _comments.emit(getCommentsByIdImageFromDbUseCase(id))
        }
    }

    fun delComment(imageId: Int, commentId: Int) {
        viewModelScope.launch {
            if (delCommentApiUseCase(imageId, commentId)) _idComment.emit(commentId)
        }
    }

    class Factory(
        private val getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase,
        private val sendCommentUseCase: SendCommentUseCase,
        private val downloadCommentsUseCase: DownloadCommentsUseCase,
        private val delCommentApiUseCase: DelCommentApiUseCase,
        private val getCommentsByIdImageFromDbUseCase: GetCommentsByIdImageFromDbUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ImageDetailViewModel::class.java)) {
                return ImageDetailViewModel(
                    getImageByIdFromDbUseCase = getImageByIdFromDbUseCase,
                    sendCommentUseCase = sendCommentUseCase,
                    downloadCommentsUseCase = downloadCommentsUseCase,
                    delCommentApiUseCase = delCommentApiUseCase,
                    getCommentsByIdImageFromDbUseCase = getCommentsByIdImageFromDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}