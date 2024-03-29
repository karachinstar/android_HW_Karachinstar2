package ru.gb.android.lession14.m14_retrofit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.android.lession14.m14_retrofit.databinding.FragmentMainBinding
import ru.gb.android.lession14.m14_retrofit.ui.main.retrofit.Person
import ru.gb.android.lession14.m14_retrofit.ui.main.retrofit.PersonAPI

class MainFragment : Fragment() {

    companion object{
        fun newInstance() = MainFragment()
    }
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPerson.onEach {
            if (it != null) {
                update(it)
            }
        }.launchIn(lifecycleScope)

        if (savedInstanceState == null) {
            lifecycleScope.launch {
                viewModel.update()
            }
        }
        binding.bUpdate.setOnClickListener {
            lifecycleScope.launch {
                viewModel.update()
            }
        }
    }

    private fun update(person: Person) {
        lifecycleScope.launch(Dispatchers.IO){
            activity?.runOnUiThread {
                binding.apply {
                    Picasso.get().load(person.results[0].picture.large).into(iwPhoto)
                    twCountry.text = person.results[0].location.country
                    twCity.text = person.results[0].location.city
                    twEmail.text = person.results[0].email
                    twPhone.text = person.results[0].phone
                    twAge.text = person.results[0].dob.age
                    twNationality.text = person.results[0].nat
                    twFirstName.text = person.results[0].name.first
                    twLastName.text = person.results[0].name.last
                    twStreetAdnHome.text = person.results[0].location.street.name + " street, h. " + person.results[0].location.street.number.toString()
                }

            }

        }
    }
}