// Generated by view binder compiler. Do not edit!
package com.example.sisterslab_ecommerce.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sisterslab_ecommerce.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView PriceTextview;

  @NonNull
  public final ConstraintLayout cardView;

  @NonNull
  public final TextView descriptionTextview;

  @NonNull
  public final ImageView productDetailImagesview;

  @NonNull
  public final TextView productDetailTextview;

  @NonNull
  public final Button shopNowButton;

  @NonNull
  public final Toolbar toolbar;

  private FragmentDetailBinding(@NonNull ConstraintLayout rootView, @NonNull TextView PriceTextview,
      @NonNull ConstraintLayout cardView, @NonNull TextView descriptionTextview,
      @NonNull ImageView productDetailImagesview, @NonNull TextView productDetailTextview,
      @NonNull Button shopNowButton, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.PriceTextview = PriceTextview;
    this.cardView = cardView;
    this.descriptionTextview = descriptionTextview;
    this.productDetailImagesview = productDetailImagesview;
    this.productDetailTextview = productDetailTextview;
    this.shopNowButton = shopNowButton;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PriceTextview;
      TextView PriceTextview = ViewBindings.findChildViewById(rootView, id);
      if (PriceTextview == null) {
        break missingId;
      }

      id = R.id.cardView;
      ConstraintLayout cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.descriptionTextview;
      TextView descriptionTextview = ViewBindings.findChildViewById(rootView, id);
      if (descriptionTextview == null) {
        break missingId;
      }

      id = R.id.productDetailImagesview;
      ImageView productDetailImagesview = ViewBindings.findChildViewById(rootView, id);
      if (productDetailImagesview == null) {
        break missingId;
      }

      id = R.id.productDetailTextview;
      TextView productDetailTextview = ViewBindings.findChildViewById(rootView, id);
      if (productDetailTextview == null) {
        break missingId;
      }

      id = R.id.shopNowButton;
      Button shopNowButton = ViewBindings.findChildViewById(rootView, id);
      if (shopNowButton == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new FragmentDetailBinding((ConstraintLayout) rootView, PriceTextview, cardView,
          descriptionTextview, productDetailImagesview, productDetailTextview, shopNowButton,
          toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
