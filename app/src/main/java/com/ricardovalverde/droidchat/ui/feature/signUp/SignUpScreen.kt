package com.ricardovalverde.droidchat.ui.feature.signUp

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.components.PrimaryButton
import com.ricardovalverde.droidchat.ui.components.ProfilePictureOptionModalBottomSheet
import com.ricardovalverde.droidchat.ui.components.ProfilePictureSelector
import com.ricardovalverde.droidchat.ui.components.SecondaryTextField
import com.ricardovalverde.droidchat.ui.theme.BackgroundGradient
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var profilePictureSelectedUri by remember { mutableStateOf<Uri?>(null) }

    var openProfilePictureOptionsModalBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState())
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(Modifier.height(56.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )

            Spacer(Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { openProfilePictureOptionsModalBottomSheet = true },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ProfilePictureSelector(imageUri = profilePictureSelectedUri)

                    Spacer(Modifier.height(30.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_first_name),
                        value = "",
                        extraText = "",
                        onValueChange = {}
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_last_name),
                        value = "",
                        extraText = "",
                        onValueChange = {}
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_login_email),
                        value = "",
                        extraText = "",
                        keyboardType = KeyboardType.Email,
                        onValueChange = {}
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password),
                        value = "",
                        extraText = "",
                        keyboardType = KeyboardType.Password,
                        onValueChange = {}
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(R.string.feature_sign_up_password_confirmation),
                        value = "",
                        extraText = "",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        onValueChange = {}
                    )

                    Spacer(Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(R.string.feature_sign_up_button),
                        onClick = {}
                    )
                }
            }

            if (openProfilePictureOptionsModalBottomSheet) {
                ProfilePictureOptionModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { openProfilePictureOptionsModalBottomSheet = false },
                    onPictureSelected = {
                        profilePictureSelectedUri = it
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                openProfilePictureOptionsModalBottomSheet = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUp() {
    DroidChatTheme {
        SignUpScreen()
    }
}