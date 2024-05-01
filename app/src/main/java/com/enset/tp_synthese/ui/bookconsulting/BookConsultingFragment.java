package com.enset.tp_synthese.ui.bookconsulting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.enset.tp_synthese.R;
import com.enset.tp_synthese.ui.bookconsulting.Adapters.BookAdapter;
import com.enset.tp_synthese.ui.bookconsulting.models.Book;
import com.enset.tp_synthese.ui.bookconsulting.services.GoogleBooksService;
import com.enset.tp_synthese.ui.bookconsulting.models.GoogleBooksResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookConsultingFragment extends Fragment {

    ListView listViewBooks;
    Button buttonSearch;
    EditText editTextQuery;
    List<Book> books;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bookconsulting, container, false);

        books = new ArrayList<>();
        listViewBooks = root.findViewById(R.id.listItem);
        buttonSearch = root.findViewById(R.id.buttom);
        editTextQuery = root.findViewById(R.id.edittextQuery);

        if (listViewBooks != null) {

            BookAdapter bookAdapter = new BookAdapter(requireContext(), R.layout.book_item, books);

            listViewBooks.setAdapter(bookAdapter);

            Gson gson = new GsonBuilder().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/books/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            GoogleBooksService booksApi = retrofit.create(GoogleBooksService.class);

            buttonSearch.setOnClickListener(view -> {
                String query = editTextQuery.getText().toString();
                Call<GoogleBooksResponse> call = booksApi.searchBooks(query);
                call.enqueue(new Callback<GoogleBooksResponse>() {
                    @Override
                    public void onResponse(Call<GoogleBooksResponse> call, Response<GoogleBooksResponse> response) {
                        GoogleBooksResponse booksResponse = response.body();
                       Log.e("hello","books");
                        books.clear();
                        books.addAll(booksResponse.getItems());
                        bookAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<GoogleBooksResponse> call, Throwable throwable) {
                        throwable.printStackTrace();
                        Toast.makeText(requireContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
            });

            listViewBooks.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent intent = new Intent(requireContext(), com.enset.tp_synthese.ui.bookconsulting.Activity.BookDetailActivity.class);
                intent.putExtra("book", books.get(i));
                startActivity(intent);
            });
        } else {
            Log.e("GalleryFragment", "ListView is null. Check the layout file.");
        }

            return root;
        }

}