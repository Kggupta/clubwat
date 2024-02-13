import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // Mutable states for email, and password
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    // Function to handle sign up logic
    fun login() {
        viewModelScope.launch {
        }
    }
}