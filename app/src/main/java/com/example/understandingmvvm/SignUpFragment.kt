package com.example.understandingmvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.understandingmvvm.databinding.SignupFragmentLayoutBinding
import com.example.understandingmvvm.di.ViewModelFactory
import com.example.understandingmvvm.mock.SignUpViewModel
import com.example.understandingmvvm.mock.UserData
import com.google.gson.Gson
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpFragment : DaggerFragment() {

    private lateinit var binding: SignupFragmentLayoutBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val signupViewModel: SignUpViewModel by viewModels({requireActivity()}) { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.signup_fragment_layout,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        binding.click.setOnClickListener {
            fetchUserDataList()
        }
    }

    private fun initViewModel(){
        signupViewModel.let{
            it.userDataResponse.observe(viewLifecycleOwner, Observer {userDataResponse ->
                Log.e("Observed", "Some data received in observer ${Gson().toJson(userDataResponse)}")
            })
        }
    }

    fun storeDataExample(){
        val data = UserData()
        data.emailId = "testemail@test.com"
        data.firstName = "test name"
        data.password = "test password"
        data.uid = "12312"

        CoroutineScope(Dispatchers.IO).launch {
            signupViewModel.storeUserData(userData = data)
        }
    }

    fun fetchUserDataList(){
        signupViewModel.fetchAllUserData()
    }
}
