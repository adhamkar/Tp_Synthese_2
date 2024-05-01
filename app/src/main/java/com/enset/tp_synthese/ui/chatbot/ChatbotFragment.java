
package com.enset.tp_synthese.ui.chatbot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enset.tp_synthese.R;
import com.enset.tp_synthese.ui.chatbot.adapters.ChatBotAdapter;
import com.enset.tp_synthese.ui.chatbot.apis.BrainShopApi;
import com.enset.tp_synthese.ui.chatbot.models.BrainShopResponse;
import com.enset.tp_synthese.ui.chatbot.models.MessageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatbotFragment extends Fragment {

    private List<MessageModel> messages = new ArrayList<>();
    private EditText editTextUser;
    private ImageButton buttonSend;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chatbot, container, false);

        editTextUser = root.findViewById(R.id.edit_text);
        buttonSend = root.findViewById(R.id.send_button);
        recyclerView = root.findViewById(R.id.recycler_view);

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.brainshop.ai/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BrainShopApi brainShopApi = retrofit.create(BrainShopApi.class);
        ChatBotAdapter chatBotAdapter = new ChatBotAdapter(messages, requireContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(chatBotAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonSend.setOnClickListener(view -> {
            String msg = editTextUser.getText().toString();
            messages.add(new MessageModel(msg, "user"));
            chatBotAdapter.notifyDataSetChanged();
            String url = "http://api.brainshop.ai/get?bid=181757&key=utDihH9dpUjsACYf&uid=[uid]&msg=" + msg;
            Call<BrainShopResponse> call = brainShopApi.getResponse(url);
            Log.e("URL", url); // Log the URL before making the network request
            editTextUser.setText("");
            call.enqueue(new Callback<BrainShopResponse>() {
                @Override
                public void onResponse(Call<BrainShopResponse> call, Response<BrainShopResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        BrainShopResponse brainShopResponse = response.body();
                        Log.i("Response", "Response successful: " + brainShopResponse.getCnt());
                        messages.add(new MessageModel(brainShopResponse.getCnt(), "bot"));
                        chatBotAdapter.notifyDataSetChanged();
                    } else {
                        int errorCode = response.code();
                        Log.e("Response", "Response not successful: " + errorCode);
                        // Handle unsuccessful response
                    }
                }

                @Override
                public void onFailure(Call<BrainShopResponse> call, Throwable t) {
                    Log.e("Error", "Error: " + t.getMessage());
                }

        });

    });
        return root;
    }
}
