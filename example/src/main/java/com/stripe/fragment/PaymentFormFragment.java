package com.stripe.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.stripe.PaymentForm;
import com.stripe.R;
import com.stripe.activity.PaymentActivity;

public class PaymentFormFragment extends Fragment implements PaymentForm {

    Button saveButton;
    EditText cardNumber;
    EditText cvc;
    Spinner monthSpinner;
    Spinner yearSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_form_fragment, container, false);

        this.saveButton = (Button) view.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveForm(view);
            }
        });

        this.cardNumber = (EditText) view.findViewById(R.id.number);
        this.cvc = (EditText) view.findViewById(R.id.cvc);
        this.monthSpinner = (Spinner) view.findViewById(R.id.expMonth);
        this.yearSpinner = (Spinner) view.findViewById(R.id.expYear);

        return view;
    }

    @Override
    public String getCardNumber() {
        return this.cardNumber.getText().toString();
    }

    @Override
    public String getCvc() {
        return this.cvc.getText().toString();
    }

    @Override
    public String getExpMonth() {
        return this.monthSpinner.getSelectedItem().toString();
    }

    @Override
    public String getExpYear() {
        return this.yearSpinner.getSelectedItem().toString();
    }

    @SuppressWarnings("unused")
    public void saveForm(View button) {
        ((PaymentActivity)getActivity()).saveCreditCard(this);
    }
}
