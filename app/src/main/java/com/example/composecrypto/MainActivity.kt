package com.example.composecrypto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecrypto.model.Coin
import com.example.composecrypto.model.Crypto
import com.example.composecrypto.service.API

import com.example.composecrypto.ui.theme.ComposeCryptoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCryptoTheme {
               MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var cryptoModels = remember {
        mutableStateListOf<Coin>()
    }

    val BASE_URL = "https://api.coinranking.com/v2/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)

    val call = retrofit.getData()

    call.enqueue(object : Callback<Crypto>{
        override fun onResponse(call: Call<Crypto>, response: Response<Crypto>) {
            if (response.isSuccessful){
                response.body()?.let {
                    val coins = it.data.coins
                    cryptoModels.addAll(coins)

                }
            }
        }

        override fun onFailure(call: Call<Crypto>, t: Throwable) {
            t.printStackTrace()
        }

    })

    Scaffold(topBar = {AppBar()}) {
        CryptoList(CoinList = cryptoModels)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
   /* TopAppBar(
        title = { Text(text = "Compose Crypto App", fontSize = 30.sp) }
    )

    */
}

@Composable
fun CryptoList(CoinList: List<Coin>){
    LazyColumn{
        items(CoinList){ crypto ->
            CryptoRow(crypto = crypto)
        }
    }
}

@Composable
fun CryptoRow(crypto: Coin){
   Column (modifier = Modifier
       .fillMaxWidth()
       .background(color = Color.DarkGray)){
       Text(text = crypto.symbol,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(3.dp),
            fontWeight = FontWeight.Bold
       )
       Text(text = crypto.name,
           modifier = Modifier.padding(3.dp))
       Text(text = crypto.price,
           modifier = Modifier.padding(3.dp))
   }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeCryptoTheme {
        CryptoRow(crypto = Coin("Bitcoin","202020200","BTC"))
    }
}