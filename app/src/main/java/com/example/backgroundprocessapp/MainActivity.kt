package com.example.backgroundprocessapp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textoConexion = findViewById<TextView>(R.id.mensaje)

        Thread(Runnable {


                var textoInicial = "No Tienes Conexión"

                val sc = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val redInfo = sc.allNetworkInfo

                for (ir in redInfo) {

                    if(ir.typeName.equals("WIFI", ignoreCase = true))
                        if(ir.isConnected) {
                            textoInicial = "Conectado con WiFi"
                            val phone = "3133729416"
                            val text = "asdasdsdaasdasd"
                            val sms = SmsManager.getDefault()
                            sms.sendTextMessage(phone, null, text, null, null)
                        }

                    if(ir.typeName.equals("MOBILE", ignoreCase = true))
                        if(ir.isConnected) textoInicial = "Conectado con Datos Móviles"

                }

                runOnUiThread {
                    textoConexion.text = textoInicial
                }


        }).start()



    }
}