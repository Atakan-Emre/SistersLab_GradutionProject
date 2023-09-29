package com.example.sisterslab_ecommerce.view.detail

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.sisterslab_ecommerce.R

public class DetailFragmentDirections private constructor() {
  public companion object {
    public fun actionDetailFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_detailFragment_to_homeFragment)
  }
}
