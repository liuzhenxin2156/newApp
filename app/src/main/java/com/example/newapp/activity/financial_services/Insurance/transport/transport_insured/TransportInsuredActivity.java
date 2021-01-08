package com.example.newapp.activity.financial_services.Insurance.transport.transport_insured;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.Insurance.details.TransportInsuranceDetailsActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;

/**
 * 生猪短途运输险保险
 *
 * @author 56454
 */
public class TransportInsuredActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back_iv;
    private EditText quarantine_num_et;
    private EditText order_num_et;
    private EditText start_date_et;
    private EditText start_time_et;
    private EditText insured_name_et;
    private EditText insured_card_type_et;
    private EditText insured_id_card_et;
    private EditText insured_id_card_effective_et;
    private EditText insured_address_et;
    private EditText insured_contact_person_et;
    private EditText insured_phone_et;
    private EditText company_phone_et;
    private EditText company_founded_time_et;
    private EditText be_insured_name_et;
    private EditText be_insured_card_type_et;
    private EditText be_insured_id_card_et;
    private EditText be_insured_id_card_effective_et;
    private EditText be_insured_address_et;
    private EditText be_insured_contact_person_et;
    private EditText be_insured_phone_et;
    private EditText carrier_et;
    private EditText insured_animals_et;
    private EditText insured_amount_et;
    private EditText departure_place_et;
    private EditText destination_et;
    private EditText license_plate_number_et;
    private EditText ear_tags_list_et;
    private EditText be_company_phone_et;
    private EditText be_company_founded_time_et;
    private LinearLayout company_phone_ll;
    private LinearLayout company_founded_time_ll;
    private LinearLayout be_company_phone_ll;
    private LinearLayout be_company_founded_time_ll;

    private CheckBox personal_cb, legal_person_cb;
    private CheckBox be_personal_cb, be_legal_person_cb;
    private Button commit_bt;
    private Button insurance_notice_btn;
    private Button insurance_terms_btn;
    private Button customer_service_btn;
    private Button claim_service_btn;


    public static void start(Context context) {
        Intent intent = new Intent(context, TransportInsuredActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_transport_insured;
    }

    @Override
    protected void initViews() {
        back_iv = findViewById(R.id.back_iv);
        quarantine_num_et = findViewById(R.id.quarantine_num_et);

        order_num_et = findViewById(R.id.order_num_et);
        start_date_et = findViewById(R.id.start_date_et);

        start_time_et = findViewById(R.id.start_time_et);
        insured_name_et = findViewById(R.id.insured_name_et);
        insured_card_type_et = findViewById(R.id.insured_card_type_et);
        insured_id_card_et = findViewById(R.id.insured_id_card_et);
        insured_id_card_effective_et = findViewById(R.id.insured_id_card_effective_et);
        insured_address_et = findViewById(R.id.insured_address_et);
        insured_contact_person_et = findViewById(R.id.insured_contact_person_et);
        insured_phone_et = findViewById(R.id.insured_phone_et);
        company_phone_et = findViewById(R.id.company_phone_et);
        company_founded_time_et = findViewById(R.id.company_founded_time_et);
        be_insured_name_et = findViewById(R.id.be_insured_name_et);
        be_insured_card_type_et = findViewById(R.id.be_insured_card_type_et);
        be_insured_id_card_et = findViewById(R.id.be_insured_id_card_et);
        be_insured_id_card_effective_et = findViewById(R.id.be_insured_id_card_effective_et);
        be_insured_address_et = findViewById(R.id.be_insured_address_et);
        be_insured_contact_person_et = findViewById(R.id.be_insured_address_et);
        be_insured_phone_et = findViewById(R.id.be_insured_phone_et);
        carrier_et = findViewById(R.id.carrier_et);
        insured_animals_et = findViewById(R.id.insured_animals_et);
        insured_amount_et = findViewById(R.id.insured_amount_et);
        be_company_phone_et = findViewById(R.id.be_company_phone_et);
        be_company_founded_time_et = findViewById(R.id.be_company_founded_time_et);
        company_phone_ll = findViewById(R.id.company_phone_ll);
        company_founded_time_ll = findViewById(R.id.company_founded_time_ll);
        be_company_phone_ll = findViewById(R.id.be_company_phone_ll);
        be_company_founded_time_ll = findViewById(R.id.be_company_founded_time_ll);

        departure_place_et = findViewById(R.id.departure_place_et);
        destination_et = findViewById(R.id.destination_et);
        license_plate_number_et = findViewById(R.id.license_plate_number_et);
        ear_tags_list_et = findViewById(R.id.ear_tags_list_et);

        personal_cb = findViewById(R.id.personal_cb);
        legal_person_cb = findViewById(R.id.legal_person_cb);
        be_personal_cb = findViewById(R.id.be_personal_cb);
        be_legal_person_cb = findViewById(R.id.be_legal_person_cb);

        insurance_notice_btn = findViewById(R.id.insurance_notice_btn);
        insurance_terms_btn = findViewById(R.id.insurance_terms_btn);
        customer_service_btn = findViewById(R.id.customer_service_btn);
        claim_service_btn = findViewById(R.id.claim_service_btn);
        commit_bt = findViewById(R.id.commit_btn);

        insurance_notice_btn.setOnClickListener(this);
        insurance_terms_btn.setOnClickListener(this);
        customer_service_btn.setOnClickListener(this);
        claim_service_btn.setOnClickListener(this);
        commit_bt.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        setCheckListener();//监听

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.insurance_notice_btn:

                break;
            case R.id.insurance_terms_btn:

                break;
            case R.id.customer_service_btn:

                break;
            case R.id.claim_service_btn:

                break;
            case R.id.commit_btn:

                break;
            default:
        }
    }

    private void setCheckListener() {
        if (personal_cb.isChecked()) {
           personal_cb.setClickable(false);
        }
        if (  be_personal_cb.isChecked()) {
            be_personal_cb.setClickable(false);
        }
        personal_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (b == personal_cb.isChecked()) {
                        personal_cb.setClickable(false);
                    }
                    personal_cb.setClickable(false);
                    legal_person_cb.setChecked(false);
                    company_phone_ll.setVisibility(View.GONE);
                    company_founded_time_ll.setVisibility(View.GONE);
                    legal_person_cb.setClickable(true);
                }
                personal_cb.setChecked(b);
            }
        });

        legal_person_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    legal_person_cb.setClickable(false);
                    personal_cb.setChecked(false);
                    company_phone_ll.setVisibility(View.VISIBLE);
                    company_founded_time_ll.setVisibility(View.VISIBLE);
                    personal_cb.setClickable(true);
                }
            }
        });

        be_personal_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    be_personal_cb.setClickable(false);
                    be_legal_person_cb.setChecked(false);
                    be_company_phone_ll.setVisibility(View.GONE);
                    be_company_founded_time_ll.setVisibility(View.GONE);
                    be_legal_person_cb.setClickable(true);
                }
                be_personal_cb.setChecked(b);
            }
        });

        be_legal_person_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    be_legal_person_cb.setClickable(false);
                    be_personal_cb.setChecked(false);
                    be_company_phone_ll.setVisibility(View.VISIBLE);
                    be_company_founded_time_ll.setVisibility(View.VISIBLE);
                    be_personal_cb.setClickable(true);
                }
            }
        });
    }
}
