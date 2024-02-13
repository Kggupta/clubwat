package com.example.clubwat.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clubwat.R
import com.example.clubwat.viewmodels.CodeVerificationViewModel
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type

@Composable
fun CodeVerificationView(viewModel: CodeVerificationViewModel = viewModel()) {
    val codeLength = 6
    val code = remember { mutableStateListOf(*Array(codeLength) { "" }) }
    val focusRequesters = List(codeLength) { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Verification",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter the verification code to complete sign up",
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.waterloo_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(fraction = 0.7f)
                .aspectRatio(2f)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            for (i in 0 until codeLength) {
                CodeInputField(
                    code[i],
                    onValueChange = {
                        if (it.length <= 1) {
                            code[i] = it
                            if (it.length == 1 && i < codeLength - 1) {
                                focusRequesters[i + 1].requestFocus()
                            }
                        }
                    },
                    focusRequester = focusRequesters[i],
                    onBackspace = {
                        if (i > 0) {
                            focusRequesters[i - 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .focusRequester(focusRequesters[i])
                )
            }
        }
        Button(
            onClick = {
                // Handle the "Done" action here
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Done")
        }
        TextButton(onClick = {}) {
            Text("Resend code")
        }
    }
}

@Composable
fun CodeInputField(
    digit: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onBackspace: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = digit,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
            .onKeyEvent { keyEvent ->
                if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Backspace && digit.isEmpty()) {
                    onBackspace()
                    true
                } else {
                    false
                }
            }
            .focusRequester(focusRequester),
    )
}

