package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {
                WrapperHome()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WrapperHome(){
    PostVisionTheme(){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground)
        {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 22.dp)
                .width(345.dp)
                .height(814.dp),

        ) {
                FlowRow(
                    modifier = Modifier.width(345.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                )
                {
                    Button(onClick = {},
                        modifier = Modifier
                            .width(52.dp)
                            .height(52.dp),
                        shape = RoundedCornerShape(CornerSize(50.dp))
                    ) {
                    }
                    Button(onClick = {},
                        modifier = Modifier
                            .width(21.dp)
                            .height(23.dp),
                        shape = RoundedCornerShape(CornerSize(50.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                    }
                }
                Row(modifier = Modifier.padding(top = 31.dp)){
                    Text(
                        "Olá Renan",
                        fontFamily = Raleway
                    );
                }

                Column(modifier = Modifier.padding(top = 23.dp)){
                    Card(modifier = Modifier
                        .width(345.dp)
                        .height(222.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        )) {
                            Column(modifier = Modifier
                                .padding(start = 26.dp, top = 20.dp, end = 10.dp)) {
                                    Text(
                                        "Faça sua análise",
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        "Corrija sua postura e melhore seu desempenho nos exercícios físicos.",
                                        fontFamily = Raleway,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontSize = 14.sp
                                    )

                                    Row(modifier = Modifier
                                        .padding(vertical = 39.dp)
                                        .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                        ){
                                        Image(modifier = Modifier
                                            .width(72.04.dp)
                                            .height(72.04.dp),
                                            painter = painterResource(R.drawable.eye_graph),
                                            contentDescription = "Image from eye graph")

                                        Card(modifier = Modifier
                                            .width(100.dp)
                                            .height(46.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.primary
                                            )) {
                                                FlowRow(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight(),
                                                    verticalArrangement = Arrangement.SpaceAround,
                                                    horizontalArrangement = Arrangement.SpaceAround,
                                                    itemVerticalAlignment = Alignment.CenterVertically) {

                                                    Text(
                                                        "Iniciar",
                                                        fontFamily = Raleway,
                                                        fontSize = 12.sp,
                                                        color = MaterialTheme.colorScheme.background
                                                    )
                                                    
                                                    IconButton(
                                                        onClick = {},
                                                        modifier = Modifier
                                                        .width(35.dp)
                                                        .height(35.dp),
                                                        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                                                        ) {
                                                        Image(
                                                            modifier = Modifier
                                                                .width(21.dp)
                                                                .height(18.dp),
                                                            painter = painterResource(R.drawable.cam_graph),
                                                            contentDescription = "Image from cam graph"
                                                        )
                                                    }
                                                }
                                        }
                                    }
                            }
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                        ){

                        Card(modifier = Modifier
                            .width(220.dp)
                            .height(96.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                            )){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(start = 28.dp),
                                verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        "Estatísticas",
                                        fontFamily = Raleway,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        "Verifique as estatísticas das suas ultimas sessões",
                                        fontFamily = Raleway,
                                        fontSize = 14.sp
                                    )
                            }
                        }
                        Card(modifier = Modifier
                            .width(100.dp)
                            .height(96.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onBackground
                            ),
                            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surface)){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .offset(x = 15.dp),
                                verticalArrangement = Arrangement.Center,

                                ) {
                                Text(
                                    "Ver mais detalhes",
                                    fontFamily = Raleway,
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }
                }

                // Sugestões

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Card(modifier = Modifier
                        .width(100.dp)
                        .height(96.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onBackground
                        ),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surface)){
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                                .offset(x = 15.dp),
                            verticalArrangement = Arrangement.Center,


                            ) {
                            Text(
                                "Ver mais detalhes",
                                fontFamily = Raleway,
                                fontSize = 16.sp,
                            )
                        }
                    }
                    Card(modifier = Modifier
                        .width(220.dp)
                        .height(96.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )){
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 28.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                "Sugestões",
                                fontFamily = Raleway,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.background
                            )
                            Text(
                                "Verifique as estatísticas das suas ultimas sessões",
                                fontFamily = Raleway,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.background
                            )
                        }
                    }
                }

                Card(modifier = Modifier
                    .width(345.dp)
                    .height(143.dp)
                    .padding(top = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                modifier = Modifier
                                    .width(72.dp)
                                    .height(95.63.dp),
                                painter = painterResource(R.drawable.streak_graph),
                                contentDescription = "Image from streak graph"
                            )
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .width(163.dp),
                                verticalArrangement = Arrangement.Center
                                ) {
                                Text(
                                    "Streak",
                                    fontFamily = Raleway,
                                    fontSize = 30.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    "Realize as análises para aumentar seu streak",
                                    fontFamily = Raleway,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                )
                            }
                        }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 89.dp, top = 10.dp)){
                    Card(modifier = Modifier
                        .width(168.dp)
                        .height(69.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround){
                                Column(modifier = Modifier
                                    .width(25.dp)
                                    .height(45.dp)) {
                                        Image(modifier = Modifier
                                            .width(25.dp)
                                            .height(27.dp),
                                            painter = painterResource(R.drawable.home_graph),
                                            contentDescription = "Image from cam graph")
                                        Text(
                                            "Inicio",
                                            fontFamily = Raleway,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.surface
                                        )
                                }

                                Column(modifier = Modifier
                                    .width(25.dp)
                                    .height(45.dp)) {
                                    Image(modifier = Modifier
                                        .width(25.dp)
                                        .height(27.dp),
                                        painter = painterResource(R.drawable.stats_graph),
                                        contentDescription = "Image from stats graph")
                                    Text(
                                        "Stats",
                                        fontFamily = Raleway,
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                }

                                Column(modifier = Modifier
                                    .width(25.dp)
                                    .height(45.dp)) {
                                    Image(modifier = Modifier
                                        .width(25.dp)
                                        .height(27.dp),
                                        painter = painterResource(R.drawable.profile_graph),
                                        contentDescription = "Image from profile graph")
                                    Text(
                                        "Perfil",
                                        fontFamily = Raleway,
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                }
                            }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MobilePreview(){
    WrapperHome()
}