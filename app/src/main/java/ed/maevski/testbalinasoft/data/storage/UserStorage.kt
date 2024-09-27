package ed.maevski.testbalinasoft.data.storage

import android.content.SharedPreferences
import ed.maevski.testbalinasoft.domain.istorage.IUserStorage
import javax.inject.Inject

class UserStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IUserStorage {
    override fun get(): String {
        return sharedPreferences.getString(USER_NAME, "") ?: ""
    }

    override fun save(userName: String) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply()
    }

    override fun save(userId: Int) {
        sharedPreferences.edit().putInt(USER_ID, userId).apply()
    }

    companion object {
        private const val USER_NAME = "userName"
        private const val USER_ID = "userId"
    }
}
