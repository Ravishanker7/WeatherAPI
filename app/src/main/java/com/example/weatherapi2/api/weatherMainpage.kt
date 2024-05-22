package com.example.weatherapi2.api


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapi2.R
import com.example.weatherapi2.WeatherDetails
import com.example.weatherapi2.WeatherViewModel


@Composable
fun weatherui(viewModel: WeatherViewModel) {

    var city by remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResult.observeAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color =
                    Color(android.graphics.Color.parseColor("#FEB054"))
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(value = city, onValueChange = {
                    city = it
                })
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "search image",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp)
                        .clickable {
                            viewModel.getData(city)
                        }
                )
            }
        }

        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.Success -> {
                weatherMainpage(result.data)
            }

            null -> {
            }
        }
    }
}

    @Composable
    fun weatherMainpage(data: WeatherModel) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(android.graphics.Color.parseColor("#FEB054"))
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color =
                        Color(android.graphics.Color.parseColor("#FEB054"))
                    )
                    .padding(20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = data.location.name,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = androidx.compose.ui.graphics.Color.Black
                )
                Text(
                    text = data.location.country,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = androidx.compose.ui.graphics.Color.Black
                )
                Text(
                    text = "Tue, Jun 30",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .size(160.dp)
                            .size(120.dp),
                        model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
                        contentDescription = "Condition icon"
                    )
                    Text(text = "${data.current.temp_c}° c",
                        modifier = Modifier, fontSize = 40.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .graphicsLayer {
                            alpha = 0.8f
                            clip = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_4),
                            contentDescription = "weather",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(50.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "Humidity",
                            color = androidx.compose.ui.graphics.Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            fontSize = 15.sp
                        )
                        Text(
                            text = data.current.humidity,
                            color = androidx.compose.ui.graphics.Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .padding(end = 30.dp),
                            textAlign = TextAlign.End,
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .graphicsLayer {
                            alpha = 0.8f
                            clip = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_5),
                            contentDescription = "weather",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(50.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "Wind",
                            color = androidx.compose.ui.graphics.Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            fontSize = 15.sp
                        )
                        Text(
                            text = data.current.wind_kph+" km/h",
                            color = androidx.compose.ui.graphics.Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .padding(end = 30.dp),
                            textAlign = TextAlign.End,
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .graphicsLayer {
                            alpha = 0.8f
                            clip = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_6),
                            contentDescription = "Humidity",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(top = 9.dp)
                                .width(50.dp)
                        )
                        Text(
                            text = "RainFall",
                            color = androidx.compose.ui.graphics.Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            fontSize = 15.sp
                        )
                        Text(
                            text = data.current.wind_mph,
                            color = androidx.compose.ui.graphics.Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .padding(end = 30.dp),
                            textAlign = TextAlign.End,
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }

