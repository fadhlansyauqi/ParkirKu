package org.d3if3049.mobpro1.parkirku

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.d3if3049.mobpro1.parkirku.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.imageViewInstagram.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/fadhlansyauqi/")
                ).setPackage("com.instagram.android")
            )
        }

        binding.imageViewGithub.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/fadhlansyauqi")
                )
            )
        }
        binding.imageViewLinkedIn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/fadhlansyauqi")
                )
            )
        }

        binding.buttonKirimEmail.setOnClickListener {
            val mailto = "mailto: mailto: adhansyauqi@gmail.com" +
                    "?subject=" + Uri.encode("Saran/Kritik App ParkirKu")

            startActivity(
                Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse(mailto)
                }
            )
        }
    }
}