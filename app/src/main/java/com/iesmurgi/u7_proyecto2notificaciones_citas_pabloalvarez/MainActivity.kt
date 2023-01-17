package com.iesmurgi.u7_proyecto2notificaciones_citas_pabloalvarez

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.iesmurgi.u7_proyecto2notificaciones_citas_pabloalvarez.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    fun notificacion(View: View) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(this, "my_channel_id")
            .setSmallIcon(R.drawable.pizza_svgrepo_com)
            .setContentTitle("+K Pizza")
            .setContentText("Promocion de pizza de espaguetis grande a solo 10€")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "my_channel_id",
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(1, builder.build())
    }

    fun establecerHora(view: View) {

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        calendar[Calendar.YEAR] = 2022 //Año en el que quieres que el TimePickerDialog aparezca

        calendar[Calendar.MONTH] = 3 //Mes en el que quieres que el TimePickerDialog aparezca

        calendar[Calendar.DAY_OF_MONTH] =
            20 //Día en el que quieres que el TimePickerDialog aparezca

        val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            selectedTime.set(Calendar.MINUTE, minute)

            if (selectedTime.before(calendar)) {
                //Muestra un mensaje de error o deshabilita el botón de confirmación
                Toast.makeText(this, "No puedes seleccionar una hora anterior a la actual", Toast.LENGTH_SHORT).show()
            } else {
                //Ejecuta el código para manejar la selección de tiempo
            }
        }, currentHour, currentMinute, false)

        timePickerDialog.show()
    }
}


