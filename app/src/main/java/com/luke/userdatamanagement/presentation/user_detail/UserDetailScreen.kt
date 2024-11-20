package com.luke.userdatamanagement.presentation.user_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.luke.userdatamanagement.ui.theme.UserDataManagementTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun UserDetailScreen(
    uuid: String,
    viewModel: UserDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state
    if (state.error == null) {

        var paymentMethod by remember { mutableStateOf(state.user?.paymentDetails?.paymentMethod ?: "") }
        var amount by remember { mutableStateOf(state.user?.paymentDetails?.amount?.toString() ?: "2500") }

        val paymentOptions = listOf("Cash", "UPI")
        var selectedOption by remember { mutableStateOf(paymentOptions[0]) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "User Details", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(8.dp))

            // Display user details
            Text(text = "Name: ${state.user?.name}")
            Text(text = "Email: ${state.user?.email}")
            Text(text = "Phone: ${state.user?.phone}")

            Spacer(modifier = Modifier.height(16.dp))

            // Display current payment details
            Text(text = "Payment Details", style = MaterialTheme.typography.labelLarge)
            Text(text = "Payment Method: ${state.user?.paymentDetails?.paymentMethod ?: "N/A"}")
            Text(text = "Amount: ${state.user?.paymentDetails?.amount ?: "N/A"}")
            Text(text = "Status: ${if (state.user?.paymentDetails?.isPaid == true) "Paid" else "Not Paid"}")

            Spacer(modifier = Modifier.height(16.dp))

            // Input fields for updating payment data
            Row(modifier = Modifier.fillMaxWidth()) {
                paymentOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { selectedOption = option }
                    ) {
                        RadioButton(
                            selected = (selectedOption == option),
                            onClick = { selectedOption = option }
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "Paid")
//                Switch(
//                    checked = isPaid,
//                    onCheckedChange = { isPaid = it }
//                )
//            }

            Spacer(modifier = Modifier.height(16.dp))

            // Update button
            Button(
                onClick = {
                    val amountDouble = amount.toDoubleOrNull()
                    if (paymentMethod.isNotBlank() && amountDouble != null) {
//                        onUpdatePayment(paymentMethod, amountDouble, isPaid)
                    } else {
                        // Show an error message or handle input validation
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Update Payment")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailPreview() {
    UserDataManagementTheme {
        UserDetailScreen(uuid = "00")
    }
}