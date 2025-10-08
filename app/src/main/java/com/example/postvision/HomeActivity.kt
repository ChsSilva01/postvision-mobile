package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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