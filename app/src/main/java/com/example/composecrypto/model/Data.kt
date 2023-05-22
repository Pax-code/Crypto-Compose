package com.example.composecrypto.model

data class Data(
    val coins: List<Coin>
    )

/*
      val service = retrofit.create(CryptoAPI::class.ja
      val call = service.getData()

      call.enqueue(object: Callback<Crypto> {
          override fun onResponse(call: Call<Crypto>, response: Response<Crypto>) {
              if (response.isSuccessful){
                  response.body()?.let {
                      val coins = it.data.coins
                      recyclerAdapter = RecyclerAdapter(coins)
                      binding.recyclerView.adapter = recyclerAdapter
                      recyclerAdapter.notifyDataSetChanged()
                  }
              }
          }

          override fun onFailure(call: Call<Crypto>, t: Throwable) {
              t.printStackTrace()
          }
      })


       */