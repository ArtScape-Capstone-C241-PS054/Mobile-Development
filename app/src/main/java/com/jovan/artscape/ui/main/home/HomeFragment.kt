package com.jovan.artscape.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovan.artscape.R
import com.jovan.artscape.ViewModelFactory
import com.jovan.artscape.databinding.FragmentHomeBinding
import com.jovan.artscape.remote.response.ApiResponse
import com.jovan.artscape.ui.CartActivity
import com.jovan.artscape.ui.NotificationActivity
import com.jovan.artscape.ui.main.painting.DetailPaintingFragment
import com.jovan.artscape.ui.search.SearchActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val binding get() = _binding!!
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        toolbar = binding.homeToolbar
        viewModel.setAllPainting().apply {
            showLoading(true)
        }
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        adapterBind()
        topAppBar()
    }

    private fun adapterBind() {
        val adapter = PaintingListAdapter()
        binding.rvArt.adapter = adapter
        binding.rvArt.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllPainting().observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Success -> {
                    Log.d("HomeFragment SUCCESS", "${it.data}")
                    showLoading(false)
                    adapter.setHomePaintingList(it.data)
                    if (adapter.itemCount == 0) {
                        Toast.makeText(
                            requireContext(),
                            "User doesnt Exist",
                            Toast.LENGTH_SHORT,
                        )
                            .show()
                    }
                }

                is ApiResponse.Error -> {
                    Log.d("HomeFragment ERROR", it.error)
                    showLoading(false)
                }
            }
        }
    }

    private fun topAppBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(
                    menu: Menu,
                    menuInflater: MenuInflater,
                ) {
                    menuInflater.inflate(R.menu.top_action_bar, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    // Handle the menu selection
                    return when (menuItem.itemId) {
                        R.id.bar_cart -> {
                            // TODO : Go to cart
                            startActivity(Intent(requireContext(), CartActivity::class.java))
                            showToast("Cart Bro")
                            true
                        }

                        R.id.bar_notification -> {
                            // TODO : Go to notification
                            startActivity(
                                Intent(
                                    requireContext(),
                                    NotificationActivity::class.java,
                                ),
                            )
                            showToast("Notif Bro")
                            true
                        }

                        R.id.bar_search -> {
                            startActivity(Intent(requireContext(), SearchActivity::class.java))
                            showToast("Search Bro")
                            Log.d("HomeFragment", "onOptionsItemSelected: Search")
                            true
                        }

                        else -> false
                    }
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED,
        )
    }

    private fun replaceFragment() {
        val newFragment = DetailPaintingFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
