package com.example.sisterslab_ecommerce.view.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.sisterslab_ecommerce.R
import kotlin.Int

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToDetailFragment(
    public val id: Int
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_detailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("id", this.id)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToDetailFragment(id: Int): NavDirections =
        ActionHomeFragmentToDetailFragment(id)
  }
}
