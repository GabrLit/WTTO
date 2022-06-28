package com.example.NFTMarketplace.converters;

import com.example.NFTMarketplace.commands.BundleCommand;
import com.example.NFTMarketplace.model.Bundle;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BundleCommandToBundle implements Converter<BundleCommand, Bundle> {

    @Synchronized
    @Nullable
    @Override
    public Bundle convert(BundleCommand source) {
        if (source == null) {
            return null;
        }

        final Bundle bundle = new Bundle();
        bundle.setName(source.getName());
        bundle.setAddress(source.getAddress());

        return bundle;
    }
}
