package kg.yoriichi

import android.app.Application
import kg.yoriichi.youtube.repository.Repository

class App : Application() {
    val repository: Repository by lazy {
        Repository()
    }
}