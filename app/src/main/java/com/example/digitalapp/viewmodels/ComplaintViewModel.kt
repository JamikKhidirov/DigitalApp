package com.example.digitalapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalapp.uistate.ComplaintUiState
import com.example.domain.useCase.SubmitComplaintUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ComplaintViewModel @Inject constructor(
    private val submitComplaintUseCase: SubmitComplaintUseCase // Hilt внедрит
): ViewModel() {

    private val _uiState = MutableStateFlow(ComplaintUiState())
    val uiState: StateFlow<ComplaintUiState> = _uiState

    fun updateTitle(newTitle: String) {
        _uiState.update { it.copy(title = newTitle, error = null) }
    }

    fun updateDescription(newDescription: String) {
        _uiState.update { it.copy(description = newDescription, error = null) }
    }

    fun submitComplaint() {
        if (_uiState.value.isSubmitting) return

        _uiState.update { it.copy(isSubmitting = true, isSuccess = false, error = null) }

        viewModelScope.launch {
            try {
                submitComplaintUseCase(_uiState.value.title, _uiState.value.description)
                // Очистка полей при успехе
                _uiState.update { it.copy(isSuccess = true, title = "", description = "") }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Неизвестная ошибка отправки") }
            } finally {
                _uiState.update { it.copy(isSubmitting = false) }
            }
        }
    }

    fun resetSuccess() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}
