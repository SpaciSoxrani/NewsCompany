package com.example.hostapp.mails;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hostapp.R;
import com.example.hostapp.serverapi.DemoServerApi;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import java.util.List;

public class MailsFragment extends Fragment {

    private Context context;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mails, container, false);
        context = root.getContext();
        final LinearLayoutCompat cardsContainer = root.findViewById(R.id.card_mailing_container);
        List<MailingItem> testListMailings = DemoServerApi.TEST_MAILINGS;

        redrawEventsCards(testListMailings, cardsContainer);

        return root;
    }

    private void redrawEventsCards(List<MailingItem> menuItems, LinearLayoutCompat container) {
        container.removeAllViews();

        for (int i = 0; i < menuItems.size(); i++) {
            final MailingItem menuItem = menuItems.get(i);

            View cardMailingGroup = LayoutInflater.from(getContext()).inflate(R.layout.card_mailing_group, container, false);

            AppCompatImageView imageView = cardMailingGroup.findViewById(R.id.image_mailing_group);
            AppCompatTextView textViewDate = cardMailingGroup.findViewById(R.id.text_date_mailing_group);
            AppCompatTextView textViewName = cardMailingGroup.findViewById(R.id.text_name_mailing_group);
            imageView.setImageResource(menuItem.image);
            textViewDate.setText(menuItem.dateTime);
            textViewName.setText(menuItem.name);
            cardMailingGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mainMenuViewModel.setSelectedMenuItem(menuItem);
                }
            });

            container.addView(cardMailingGroup);
        }
    }
}
