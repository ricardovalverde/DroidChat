package com.ricardovalverde.droidchat.ui.components

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricardovalverde.droidchat.DroidChatFileProvider
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePictureOptionModalBottomSheet(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onPictureSelected: (uri: Uri) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }


    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { onPictureSelected(it) }
        })


    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && photoUri != null) {
                onPictureSelected(photoUri!!)
            }
        }
    )


    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        ProfilePictureOptionElevatedCard(
            onClick = {
                photoUri = DroidChatFileProvider.getImageUri(context.applicationContext)
                cameraLauncher.launch(photoUri!!)
            },
            iconResId = R.drawable.ic_photo_camera,
            textStringId = R.string.common_take_photo
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfilePictureOptionElevatedCard(
            onClick = { imagePicker.launch("image/*") },
            iconResId = R.drawable.ic_photo_library,
            textStringId = R.string.common_upload_photo
        )
    }
}


@Composable
private fun ProfilePictureOptionElevatedCard(
    onClick: () -> Unit,
    @DrawableRes
    iconResId: Int,
    @StringRes
    textStringId: Int,
) {
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp)
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.elevatedButtonColors().copy(containerColor = Color(0xCEE2E2F5)),
        onClick = { onClick() }
    ) {
        Icon(
            painterResource(iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(textStringId),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun ProfilePictureOptionModalBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = false,
        initialValue = SheetValue.Expanded,
        positionalThreshold = { 1f },
        velocityThreshold = { 1f },
    )

    DroidChatTheme {
        Box(Modifier.fillMaxSize()) {
            ProfilePictureOptionModalBottomSheet(
                onDismissRequest = {},
                onPictureSelected = {},
                sheetState = sheetState
            )
        }
    }
}
