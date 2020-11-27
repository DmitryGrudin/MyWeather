package com.github.myweather.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.myweather.R;
import com.github.myweather.data.ForecastsViewModel;
import com.github.myweather.databinding.FragmentForecastsBinding;

import org.jetbrains.annotations.NotNull;

public class ForecastsFragment extends Fragment {

    public static ForecastsFragment newInstance() {
        return new ForecastsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ForecastsViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(ForecastsViewModel.class);

        FragmentForecastsBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_forecasts,
                container,
                false);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }
}