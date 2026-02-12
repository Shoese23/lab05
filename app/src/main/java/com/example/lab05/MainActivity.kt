package com.example.lab05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity: AppCompatActivity() {
    private var temperature = 22
    private var lightLevel = 75
    private var isSecurityActive = true
    private var isLighton = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tempValue: TextView = findViewById(R.id.tempValue)
        val lightValue: TextView = findViewById(R.id.lightValue)
        val statusText: TextView = findViewById(R.id.statusText)
        val securityButton: Button = findViewById(R.id.securityButton)
        val securityIndicator: View = findViewById(R.id.securityIndicator)
        val securityText: TextView = findViewById(R.id.securityText)

        val tempUpButton: Button = findViewById(R.id.tempUpButton)
        val tempDownButton: Button = findViewById(R.id.tempDownButton)
        val lightOnButton: Button = findViewById(R.id.lightOnButton)
        val lightoffButton: Button = findViewById(R.id.lightOffButton)

        fun updateTemperature() {
            tempValue.text = "${temperature}°C"
            when {
                temperature < 18 -> {
                    tempValue.setBackgroundColor(getColor(android.R.color.holo_blue_light))
                    statusText.text = "❄Температура низкая"
                    statusText.setTextColor(getColor(android.R.color.holo_blue_dark))
                }

                temperature < 26 -> {
                    tempValue.setBackgroundColor(getColor(android.R.color.holo_red_light))
                    statusText.text = "🔥Температура высокая"
                    statusText.setTextColor(getColor(android.R.color.holo_red_dark))
                }

                else -> {
                    tempValue.setBackgroundColor(getColor(android.R.color.holo_green_light))
                    statusText.text = "✅Температура комфортная"
                    statusText.setTextColor(getColor(android.R.color.holo_green_dark))
                }
            }
        }

        fun updateLight() {
            lightValue.text = "${lightLevel}%"
            when {
                lightLevel < 30 -> {
                    lightValue.setBackgroundColor(getColor(android.R.color.darker_gray))
                    lightValue.text = "🌙$lightLevel%"
                }

                lightLevel < 70 -> {
                    lightValue.setBackgroundColor(getColor(android.R.color.holo_orange_light))
                    lightValue.text = "💡$lightLevel%"
                }

                else -> {
                    lightValue.setBackgroundColor(getColor(android.R.color.holo_red_light))
                    lightValue.text = "☀$lightLevel%"
                }
            }
            if (!isLighton) {
                lightValue.text = "🚫Выкл"
                lightValue.setBackgroundColor(getColor(android.R.color.darker_gray))
            }
        }

        fun updateSecurity() {
            if (isSecurityActive) {
                securityButton.text = "AKTИBHA"
                securityButton.backgroundTintList =
                    getColorStateList(android.R.color.holo_green_dark)
                securityIndicator.setBackgroundColor(getColor(android.R.color.holo_green_dark))
                securityText.text = "🔒Система АКТИВНА"
                statusText.text = "✅Безопасность активирована"
                statusText.setTextColor(getColor(android.R.color.holo_green_dark))
            } else {
                securityButton.text = "OTKЛЮЧEHA"
                securityButton.backgroundTintList = getColorStateList(android.R.color.holo_red_dark)
                securityIndicator.setBackgroundColor(getColor(android.R.color.holo_red_dark))
                securityText.text = "⚠ Система ОТКЛЮЧЕНА"
                statusText.text = "⚠ Безопасность отключена"
                statusText.setTextColor(getColor(android.R.color.holo_red_dark))
            }
        }
        tempUpButton.setOnClickListener {
            if (temperature < 30) {
                temperature++
                updateTemperature()
                Toast.makeText(this, "Температура повышена: ${temperature}°C", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Mаксимальная температура достигнута", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        tempDownButton.setOnClickListener {
            if (temperature > 10) {
                temperature--
                updateTemperature()
                Toast.makeText(this, "Teмneратуpа понижена: ${temperature}°C", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Mинимальная температура достигнута", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        lightOnButton.setOnClickListener {
            isLighton = true
            if (lightLevel < 10) lightLevel = 50
            updateLight()
            Toast.makeText(this, "Освешение включено", Toast.LENGTH_SHORT).show()
        }
        lightoffButton.setOnClickListener {
            isLighton = false
            updateLight()
            Toast.makeText(this, "Освешение выклчено", Toast.LENGTH_SHORT).show()
        }
        securityButton.setOnClickListener {
            isSecurityActive = !isSecurityActive
            updateSecurity()
            val message = if (isSecurityActive) "Система безопасности активирована"
            else "Система безопасности отключена"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        tempValue.setOnClickListener {
            temperature = 22
            updateTemperature()
            Toast.makeText(this, "Температура сброшена до 22°С", Toast.LENGTH_SHORT).show()
        }
        lightValue.setOnClickListener {
            if (isLighton) {
                lightLevel = 75
                Toast.makeText(this, "Освещение сброшено до 75%", Toast.LENGTH_SHORT).show()
            }
        }
    }
}