package com.aospinsight.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.aospinsight.aidlclient.databinding.FragmentFirstBinding
import com.aospinsight.myaidl.IUserInterface

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val TAG = "Client FirstFragment"

    private var _binding: FragmentFirstBinding? = null

    var iUserInterface: IUserInterface? = null
    private lateinit var textViewResult : TextView

    val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.v(TAG, "Service has connected!")
            binding.buttonFirst.text = "Unbind from the service"
            binding.textviewResult.append("\nService has connected!\n-----------------\n")
            iUserInterface = IUserInterface.Stub.asInterface(service)

            iUserInterface?.getUser(0)?.let{
                val user = it

                Log.v(TAG, "user.id = ${user.id}")
                binding.textviewResult.append("\nid: ${user.id}")

                Log.v(TAG, "user.name = ${user.name}")
                binding.textviewResult.append("\nname: ${user.name}")

                Log.v(TAG, "user.address.street = ${user.address.street}")
                binding.textviewResult.append("\nstreet: ${user.address.street}")

                Log.v(TAG, "user.address.city = ${user.address.city}")
                binding.textviewResult.append("\ncity: ${user.address.city}")

                Log.v(TAG, "user.address.postalCode = ${user.address.postalCode}")
                binding.textviewResult.append("\npostal code: ${user.address.postalCode}")


                user.name = "Mike"
                iUserInterface?.updateUser(user)

            }

        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.e(TAG, "Service has unexpectedly disconnected")
            iUserInterface = null
        }

        override fun onBindingDied(name: ComponentName?) {
            super.onBindingDied(name)
            Log.e(TAG, "onBindingDied")
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            bindUnbindToAidlService()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindUnbindToAidlService() {
        if(iUserInterface == null) {
            Intent("com.aospinsight.aidlserver.AidlService").apply {
                setClassName("com.aospinsight.aidlserver", "com.aospinsight.aidlserver.AidlService")
            }.also { intent ->
                requireContext().applicationContext.bindService(
                    intent,
                    connection,
                    Context.BIND_AUTO_CREATE
                )
            }
        }
        else{
            requireContext().applicationContext.unbindService(connection)
            iUserInterface = null
        }
    }
}