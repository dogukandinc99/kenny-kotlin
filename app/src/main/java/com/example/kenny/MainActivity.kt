package com.example.kenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Debug
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.kenny.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var score = 0
    var randomnumber = 0
    var imageView = ArrayList<ImageView>()
    var runnable: Runnable = Runnable {}
    var handler: Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        imageView.add(0, binding.imageView)
        imageView.add(1, binding.imageView2)
        imageView.add(2, binding.imageView3)
        imageView.add(3, binding.imageView4)
        imageView.add(4, binding.imageView5)
        imageView.add(5, binding.imageView6)
        imageView.add(6, binding.imageView7)
        imageView.add(7, binding.imageView8)
        imageView.add(8, binding.imageView9)
        imageView.add(9, binding.imageView10)
        imageView.add(10, binding.imageView11)
        imageView.add(11, binding.imageView12)



        object : CountDownTimer(15000, 1000) {
            override fun onTick(p0: Long) {
                binding.textView.text = "Time: ${(p0 / 1000).toString()}"
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                var alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("Süre Doldu.")

                alertDialog.setMessage(
                    "Süreniz dolmuştur.\nYeni oyuna başlamak ister misiniz?" +
                            "\n Score: ${score.toString()}"
                )

                alertDialog.setPositiveButton("Evet") { p0, p1 ->
                    start()
                    handler.post(runnable)
                }

                alertDialog.setNegativeButton("Hayır") { p0, p1 ->
                    Toast.makeText(
                        this@MainActivity,
                        "Score: ${score.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    handler.removeCallbacks(runnable)
                }
                alertDialog.show()
                score = 0
            }
        }.start()

        runnable = object : Runnable {
            override fun run() {
                imageView[randomnumber].visibility = View.INVISIBLE
                randomnumber = (0..11).random()
                imageView[randomnumber].visibility = View.VISIBLE
                handler.postDelayed(this, 500)
            }
        }
        handler.post(runnable)
    }

    fun clickimage(view: View) {
        score++;
        binding.textView2.text = "Score: ${score.toString()}"
    }
}