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
import androidx.compose.foundation.layout.fillMaxSize // ADICIONADO
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

/*class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {
                WrapperHome(onNavigateToChooseExercise = {})
            }
        }
    }
}*/

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WrapperHome(
    onNavigateToChooseExercise: () -> Unit
){
    PostVisionTheme(){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground)
        {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 22.dp)
                    .fillMaxSize(),
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                )
                {
                    IconButton(onClick = {},
                        modifier = Modifier
                            .width(52.dp)
                            .height(52.dp),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.imagem),
                            contentDescription = "Imagem from profile"
                        )
                    }
                    IconButton(onClick = {},
                        modifier = Modifier
                            .width(21.dp)
                            .fillMaxHeight(),
                    ){
                        Image(
                            painter = painterResource(R.drawable.notification_icon),
                            contentDescription = "Imagem from profile"
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 31.dp),

                ){
                    Text(
                        "Olá Carlos",
                        fontFamily = Raleway,
                        color = MaterialTheme.colorScheme.surface
                    )
                }

                Column(modifier = Modifier.padding(top = 23.dp)){
                    Card(modifier = Modifier
                        .fillMaxWidth() // CORRIGIDO: Usando fillMaxWidth
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
                                    ),
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    FlowRow(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.SpaceAround,
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        itemVerticalAlignment = Alignment.CenterVertically) {

                                        Text(
                                            "Iniciar",
                                            modifier = Modifier
                                                .padding(start = 12.dp),
                                            fontFamily = Raleway,
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.background
                                        )

                                        IconButton(
                                            onClick = onNavigateToChooseExercise,
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
                        horizontalArrangement = Arrangement.spacedBy(15.dp) // CORRIGIDO: Espaçamento entre cartões
                    ){

                        Card(modifier = Modifier
                            .weight(2f)
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
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Text(
                                    "Verifique as estatísticas das suas ultimas sessões",
                                    fontFamily = Raleway,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.surface
                                )
                            }
                        }
                        Card(modifier = Modifier
                            .weight(1f)
                            .height(96.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onBackground
                            ),
                            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surface)){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally

                                ) {
                                Text(
                                    "Ver mais \n " +
                                            "detalhes",
                                    textAlign = TextAlign.Start,
                                    fontFamily = Raleway,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.surface
                                )
                            }
                        }
                    }
                }

                // Sugestões

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    Card(modifier = Modifier
                        .weight(1f)
                        .height(96.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onBackground
                        ),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surface)){
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                            Text(
                                "Receber \n" +
                                        "Sugestões ",
                                textAlign = TextAlign.Start,
                                fontFamily = Raleway,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                    Card(modifier = Modifier
                        .weight(2f) // CORRIGIDO: Usando weight para responsividade (2/3)
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
                    .fillMaxWidth()
                    .height(143.dp)
                    .padding(top = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )) {
                    Row(modifier = Modifier
                        .width(265.dp)
                        .height(95.63.dp)
                        .align(Alignment.CenterHorizontally)
                        .offset(y = 23.69.dp),
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
                            .padding(start = 30.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Streak",
                                fontFamily = Raleway,
                                fontSize = 28.sp,
                                color = MaterialTheme.colorScheme.surface,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "Realize as análises para aumentar seu streak",
                                fontFamily = Raleway,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.surface,
                            )
                        }
                    }
                }

                // BARRA DE NAVEGAÇÃO INFERIOR
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 15.dp)
                    .width(180.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(69.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround){

                            Card(modifier = Modifier
                                .width(50.dp)
                                .height(57.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0x52DEDEFF)
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ){
                                Column(modifier = Modifier
                                    .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                    Column(modifier = Modifier
                                        .offset(y = 9.dp)){
                                        Image(modifier = Modifier
                                            .width(25.dp)
                                            .height(27.dp),
                                            painter = painterResource(R.drawable.home_graph),
                                            contentDescription = "Image from cam graph")
                                        Text(
                                            "Início",
                                            fontFamily = Raleway,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.surface
                                        )
                                    }
                                }
                            }

                            Card(modifier = Modifier
                                .width(50.dp)
                                .height(57.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ){
                                Column(modifier = Modifier
                                    .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Column(modifier = Modifier
                                        .offset(y = 9.dp)){
                                        Image(modifier = Modifier
                                            .width(25.dp)
                                            .height(27.dp),
                                            painter = painterResource(R.drawable.stats_graph),
                                            contentDescription = "Image from cam graph")
                                        Text(
                                            "Stats",
                                            fontFamily = Raleway,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.surface
                                        )
                                    }
                                }
                            }


                            Card(modifier = Modifier
                                .width(50.dp)
                                .height(57.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ){
                                Column(modifier = Modifier
                                    .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Column(modifier = Modifier
                                        .offset(y = 9.dp)){
                                        Image(modifier = Modifier
                                            .width(25.dp)
                                            .height(27.dp),
                                            painter = painterResource(R.drawable.profile_graph),
                                            contentDescription = "Image from cam graph")
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
    }
}

@Preview
@Composable
fun MobilePreview(){
    WrapperHome(onNavigateToChooseExercise = {})
}