package com.example.rentbuddy.Model.Property;

public class PropertyResultFactory implements IPropertyResultFactory {

    private static PropertyResultFactory uniqueInstance = null;

    private PropertyResultFactory() {

    }

    public static PropertyResultFactory instance() {

        if (null == uniqueInstance) {
            uniqueInstance = new PropertyResultFactory();
        }
        return uniqueInstance;

    }


    @Override
    public IPropertyResult create() {
        return new PropertyResult();
    }
}