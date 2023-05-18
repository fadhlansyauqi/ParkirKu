package org.d3if3049.mobpro1.parkirku.ui.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.d3if3049.mobpro1.parkirku.R
import org.d3if3049.mobpro1.parkirku.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            // fix back button
//            onBackPressedDispatcher.onBackPressed()
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