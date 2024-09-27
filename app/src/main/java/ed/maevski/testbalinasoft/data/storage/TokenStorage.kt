package ed.maevski.testbalinasoft.data.storage

import android.content.SharedPreferences
import ed.maevski.testbalinasoft.domain.istorage.ITokenStorage
import javax.inject.Inject

class TokenStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ITokenStorage {
    override fun get(): String {
        return sharedPreferences.getString(TOKEN, "") ?: ""
    }

    override fun save(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    companion object {
        private const val TOKEN = "token"
    }
}
