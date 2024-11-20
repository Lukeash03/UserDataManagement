package com.luke.userdatamanagement.presentation.user_listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.luke.userdatamanagement.R
import com.luke.userdatamanagement.domain.models.Address
import com.luke.userdatamanagement.domain.models.Coordinates
import com.luke.userdatamanagement.domain.models.PaymentDetails
import com.luke.userdatamanagement.domain.models.Timezone
import com.luke.userdatamanagement.domain.models.User
import com.luke.userdatamanagement.ui.theme.UserDataManagementTheme

@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
) {
//    Column(
//        modifier = modifier
//            .padding(5.dp)
//            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
////            .clickable { /* Handle click */ }
//            .fillMaxWidth()
//    ) {
        Card(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
            ,
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
//            colors = CardDe
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = rememberImagePainter(data = user.profilePictureUrl),
                        contentDescription = null,
                        modifier = Modifier
//                            .fillMaxWidth()
                            .height(60.dp)
                            .width(60.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val gender =
                                if (user.gender == "male") R.drawable.baseline_male_24
                                else R.drawable.baseline_female_24
                            Icon(
                                painter = painterResource(id = gender),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 2.dp)
                            )
                            Text(
                                text = user.name,
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            text = user.phone,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Paid",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                }

//                Spacer(modifier = Modifier.height(6.dp))
//
//                Column(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(
//                        text = "Locations:",
//                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
//                        color = Color.Black
//                    )
//
//                    Text(
//                        text = user.email,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = Color.Black
//                    )
//                }
            }
        }
//    }
}

@Preview
@Composable
fun UserItemPreview() {
    UserDataManagementTheme {
        UserItem(
            user = User(
                "0",
                "Name",
                "male",
                "email@example.com",
                "username",
                "password",
                "9998887776",
                "VI",
                "199934",
                10,
                "19983",
                "url",
                Address(
                    100,
                    "StreetName",
                    "City",
                    "State",
                    "Country",
                    555555,
                ),
                Coordinates(
                    "19191",
                    "19191",
                ),
                Timezone(
                    "0",
                    "Description"
                ),
                PaymentDetails(
                    "Cash",
                    2500.0,
                    true
                )
            )
        )
    }
}