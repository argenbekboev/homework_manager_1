package com.example.manager.ui.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.manager.databinding.FragmentProfileBinding
import com.example.manager.loadImage


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    var galleryActivityLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.OpenDocument(),
            ActivityResultCallback<Uri?> { result ->
                if (result != null) {
                    binding.imageCircle.loadImage(result.toString())
                } else {
                    Log.d("oioi", "onActivityResult: the result is null for some reason")
                }
            })

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageCircle.setOnClickListener {
            galleryActivityLauncher.launch(arrayOf("image/*"));
        }

    }
}


