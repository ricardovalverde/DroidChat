package com.ricardovalverde.droidchat.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ricardovalverde.droidchat.R
import com.ricardovalverde.droidchat.ui.theme.DroidChatTheme

@Composable
fun ProfilePictureSelector(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(84.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_upload_photo),
            model = imageUri ?: R.drawable.ic_upload_photo,
            contentDescription = null
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.common_add_profile_photo),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePictureSelectorPreview() {
    DroidChatTheme {
        ProfilePictureSelector()
    }
}