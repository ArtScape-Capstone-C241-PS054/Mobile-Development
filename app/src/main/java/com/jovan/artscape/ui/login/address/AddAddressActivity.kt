package com.jovan.artscape.ui.login.address

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jovan.artscape.R
import com.jovan.artscape.ViewModelFactory
import com.jovan.artscape.databinding.ActivityAddAddressBinding
import com.jovan.artscape.remote.request.AddUserRequest
import com.jovan.artscape.ui.login.interest.InterestActivity
import com.jovan.artscape.utils.DialogUtils
import com.jovan.artscape.utils.NetworkUtils

class AddAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddAddressBinding
    private val viewModel by viewModels<AddressViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (NetworkUtils.isNetworkAvailable(this))
            {
                bindAddress()
                actionButton()
            } else {
            showLoading(false)
            Log.d("ERROR", "Network Not Available")
            DialogUtils.showNetworkSettingsDialog(this)
        }
    }

    private fun clearRegencyAdapter() {
        val emptyAdapter = ArrayAdapter<String>(this, R.layout.list_item_address, listOf())
        val autoCompleteRegency = binding.autoCompleteRegency
        autoCompleteRegency.setAdapter(emptyAdapter)
        autoCompleteRegency.setText("")
        clearDistrictAdapter()
    }

    private fun clearDistrictAdapter() {
        val emptyAdapter = ArrayAdapter<String>(this, R.layout.list_item_address, listOf())
        val autoCompleteDistrict = binding.autoCompleteDistrict
        autoCompleteDistrict.setAdapter(emptyAdapter)
        autoCompleteDistrict.setText("")
        clearVillageAdapter()
    }

    private fun clearVillageAdapter() {
        val emptyAdapter = ArrayAdapter<String>(this, R.layout.list_item_address, listOf())
        val autoCompleteVillage = binding.autoCompleteVillage
        autoCompleteVillage.setAdapter(emptyAdapter)
        autoCompleteVillage.setText("")
    }

    private fun actionButton() {
        binding.apply {
            buttonAddress.setOnClickListener {
                val token = intent.getStringExtra(EXTRA_ID_TOKEN)
                val province = autoCompleteProvince.text.toString()
                val regency = autoCompleteRegency.text.toString()
                val district = autoCompleteDistrict.text.toString()
                val village = autoCompleteVillage.text.toString()

                val userData =
                    AddUserRequest(
                        idToken = token.toString(),
                        name = intent.getStringExtra(EXTRA_NAME).toString(),
                        bio = intent.getStringExtra(EXTRA_BIO).toString(),
                        address = "$province/$regency/$district/$village",
                        interest = listOf(),
                        phoneNumber = intent.getStringExtra(EXTRA_PHONE_NUMBER).toString(),
                    )

                val intent = Intent(this@AddAddressActivity, InterestActivity::class.java)
                intent.putExtra(InterestActivity.EXTRA_USER_WITH_ADDRESS, userData)
                startActivity(intent)
            }
        }
    }

    private fun bindAddress()  {
        val autoCompleteProvince = binding.autoCompleteProvince
        val autoCompleteRegency = binding.autoCompleteRegency
        val autoCompleteDistrict = binding.autoCompleteDistrict
        val autoCompleteVillage = binding.autoCompleteVillage

        viewModel.getProvinces().observe(this) { provinces ->
            val item = provinces.map { it.name }
            val adapterItems = ArrayAdapter(this, R.layout.list_item_address, item)
            autoCompleteProvince.setAdapter(adapterItems)

            autoCompleteProvince.setOnItemClickListener { _, _, i, _ ->
                showLoading(true)
                clearRegencyAdapter()
                viewModel.setRegencies(provinces[i].id)
                showLoading(false)
            }
        }
        viewModel.getRegencies().observe(this) { regency ->
            val item = regency.map { it.name }
            val adapterItems = ArrayAdapter(this, R.layout.list_item_address, item)
            autoCompleteRegency.setAdapter(adapterItems)
            autoCompleteRegency.setOnItemClickListener { _, _, i, _ ->
                showLoading(true)

                clearDistrictAdapter()
                viewModel.setDistricts(regency[i].id)
                showLoading(false)
            }
        }
        viewModel.getDistricts().observe(this) { district ->
            val item = district.map { it.name }
            val adapterItems = ArrayAdapter(this, R.layout.list_item_address, item)
            autoCompleteDistrict.setAdapter(adapterItems)
            autoCompleteDistrict.setOnItemClickListener { _, _, i, _ ->
                showLoading(true)

                clearVillageAdapter()
                viewModel.setVillages(district[i].id)
                showLoading(false)
            }
        }
        viewModel.getVillages().observe(this) { village ->
            val item = village.map { it.name }
            val adapterItems = ArrayAdapter(this, R.layout.list_item_address, item)
            autoCompleteVillage.setAdapter(adapterItems)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_ID_TOKEN = "extra_id_token"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_BIO = "extra_bio"
        const val EXTRA_PHONE_NUMBER = "extra_phone_number"
    }
}
