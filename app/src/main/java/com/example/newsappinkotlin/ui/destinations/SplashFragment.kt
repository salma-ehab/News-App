package com.example.newsappinkotlin.ui.destinations
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsappinkotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var handler: Handler = Handler()
        var runnable = Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_headlinesFragment)
        }
        handler.postDelayed(runnable, 1000)
    }

}