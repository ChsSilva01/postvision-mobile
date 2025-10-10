package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway
import kotlinx.coroutines.selects.select

class StepByStep : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {

            }
        }
    }
}

@Composable
fun WraapperStepByStep(){
    PostVisionTheme() {
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
                Text(
                    "Agachamento Livre",
                    fontFamily = Raleway,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(219.8.dp)
                    .padding(top = 70.dp)) {
                        Image(
                            painter = painterResource(R.drawable.first_step),
                            contentDescription = "Image from first step",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(131.65.dp)
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()){
                                Row(modifier = Modifier
                                    .fillMaxWidth()){
                                    Card(
                                        modifier = Modifier.size(24.dp),
                                        shape = CircleShape,
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        )
                                        ) {
                                        FlowRow(modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                            horizontalArrangement = Arrangement.Center) {
                                            Text(
                                                "1",
                                                fontFamily = Raleway,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.background
                                            )
                                        }
                                    }
                                    Row(modifier = Modifier
                                        .padding(start = 5.dp)){
                                        Text(
                                            "Posição inicial:",
                                            fontFamily = Raleway,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                                Text(
                                    "Fique em pé com os pés afastados na largura dos ombros. Mantenha a coluna reta, o peito aberto e os braços estendidos à frente para equilíbrio.",
                                    modifier = Modifier.padding(top = 8.dp),
                                    fontFamily = Raleway,
                                    fontSize = 16.sp,
                                    lineHeight = 15.sp,
                                    textAlign = TextAlign.Justify,
                                )
                        }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(219.8.dp)
                    .padding(top = 70.dp)) {
                    Image(
                        painter = painterResource(R.drawable.second_step),
                        contentDescription = "Image from first step",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(131.65.dp)
                    )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()){
                        Row(modifier = Modifier
                            .fillMaxWidth()){
                            Card(
                                modifier = Modifier.size(24.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                FlowRow(modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                    horizontalArrangement = Arrangement.Center) {
                                    Text(
                                        "2",
                                        fontFamily = Raleway,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.background
                                    )
                                }
                            }
                            Row(modifier = Modifier
                                .padding(start = 5.dp)){
                                Text(
                                    "Agache com controle:",
                                    fontFamily = Raleway,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        Text(
                            "Dobre os joelhos e os quadris ao mesmo tempo, como se fosse sentar. Evite tirar os calcanhares do chão e mantenha os joelhos apontando na direção dos pés.",
                            modifier = Modifier.padding(top = 8.dp),
                            fontFamily = Raleway,
                            fontSize = 16.sp,
                            lineHeight = 15.sp,
                            textAlign = TextAlign.Justify,
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp)){
                    Row(modifier = Modifier
                        .fillMaxWidth()){
                        Card(
                            modifier = Modifier.size(24.dp),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            FlowRow(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                                horizontalArrangement = Arrangement.Center) {
                                Text(
                                    "3",
                                    fontFamily = Raleway,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.background
                                )
                            }
                        }
                        Row(modifier = Modifier
                            .padding(start = 5.dp)){
                            Text(
                                "Volte a posição inicial:",
                                fontFamily = Raleway,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Text(
                        "Empurre o chão com os pés e estique as pernas até ficar em pé novamente, sem perder o alinhamento da postura.",
                        modifier = Modifier.padding(top = 8.dp),
                        fontFamily = Raleway,
                        fontSize = 16.sp,
                        lineHeight = 15.sp,
                        textAlign = TextAlign.Justify,
                    )
                }

                Row(modifier = Modifier.padding(top = 88.dp)){
                    Button(modifier = Modifier
                        .width(345.dp)
                        .height(51.dp),
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),

                        ) {
                        Text(
                            "Iniciar Passo a Passo",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MobilePreviewStep(){
    WraapperStepByStep()
}