package com.geekbrains.frontend;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public abstract class AbstractView extends VerticalLayout {
    protected TextField initTextFieldWithPlaceholder(String placeholder){
        TextField textField = new TextField();
        textField.setPlaceholder(placeholder);
        return textField;
    }
    protected NumberField initNumberFieldWithPlaceholder(String placeholder){
        NumberField numberField = new NumberField();
        numberField.setPlaceholder(placeholder);
        return numberField;
    }

}
