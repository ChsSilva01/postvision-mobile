package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {
                WrapperLogin()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrapperLogin(){
    var text by remember { mutableStateOf("") }
    
    PostVisionTheme() {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground)
        {
            Box(modifier = Modifier
                .fillMaxHeight()
                .height(231.dp)){
                Image(
                    modifier = Modifier
                        .width(350.dp)
                        .height(350.dp)
                        .offset(x = 105.dp, y = (-75).dp),

                    painter = painterResource(R.drawable.head_graph),
                    contentDescription = "Image from head graph"
                )
            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .height(116.dp)){
                Image(
                    modifier = Modifier
                        .width(295.dp)
                        .height(201.dp)
                        .offset(x = (-75).dp, y = (655).dp),

                    painter = painterResource(R.drawable.eye_two_graph),
                    contentDescription = "Image from head graph"
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(25.dp)
                .offset(y = 258.dp)){
                Text(
                    "Login",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp
                )

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .offset(y = 47.dp)){
                    Text(
                        "E-mail",
                        fontSize = 13.sp,
                        fontFamily = Raleway,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    TextField(
                        modifier = Modifier
                            .width(345.dp)
                            .height(40.dp),
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,

                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledContainerColor = MaterialTheme.colorScheme.background,
                            errorContainerColor = MaterialTheme.colorScheme.background


                        ),
                        value = text,
                        onValueChange = { newText: String -> text = newText },
                        shape = RoundedCornerShape(20.dp),
                    )

                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .offset(y = 67.dp)){
                    Text(
                        "Senha",
                        fontSize = 13.sp,
                        fontFamily = Raleway,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    TextField(
                        modifier = Modifier
                            .width(345.dp)
                            .height(40.dp),
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,

                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledContainerColor = MaterialTheme.colorScheme.background,
                            errorContainerColor = MaterialTheme.colorScheme.background


                        ),
                        value = text,
                        onValueChange = { newText: String -> text = newText },
                        shape = RoundedCornerShape(20.dp),
                    )

                }

                Column(
                    modifier = Modifier.offset(y = 151.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(modifier = Modifier
                        .width(345.dp)
                        .height(51.dp),
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),

                        ) {
                        Text(
                            "Entrar",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(modifier = Modifier
                        .width(195.dp)
                        .offset(y = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Text(
                            "Não tem conta?",
                            fontFamily = Raleway,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface

                        )
                        Text(
                            "Cadastre-se",
                            fontFamily = Raleway,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun MobilePreviewLogin(){
    WrapperLogin()
}
