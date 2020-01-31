package com.val.techberries.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.adaptors.AdvertisingRecyclerViewAdaptor;
import com.val.techberries.entities.Advertising;
import com.val.techberries.entities.Item;
import com.val.techberries.R;
import com.val.techberries.adaptors.RecyclerViewAdaptor;
import com.val.techberries.interfacies.MyCallBackToRepo;
import com.val.techberries.interfacies.OnRecyclerViewItemClick;
import com.val.techberries.modelViews.ViewModelForHomePage;
import com.val.techberries.utils.netWork.InternetConnectionChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator2;

public class HomeFragment extends Fragment {
    private RecyclerView firstRecyclerView;
    private RecyclerView secondRecyclerView;
    private RecyclerView thirdRecyclerView;

    private CircleIndicator2 circleIndicator;
    private CircleIndicator2 circleIndicator2;

    private Toolbar toolbar;

    private EditText searchLine;

    private CircleImageView youtubeBtn;
    private CircleImageView secondVkBtn;
    private ImageView odnoklasnikiBtn;
    private CircleImageView twitterBtn;
    private CircleImageView instagramBtn;
    private CircleImageView facebookBtn;
    private InternetConnectionChecker internetConnectionChecker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);
        searchLine = view.findViewById(R.id.search_line_et);
        youtubeBtn = view.findViewById(R.id.ic_youtube_social_network);
        secondVkBtn = view.findViewById(R.id.SecondVkBtn);
        odnoklasnikiBtn = view.findViewById(R.id.ok_social_network_btn);
        twitterBtn = view.findViewById(R.id.ic_tviter_social_network);
        instagramBtn = view.findViewById(R.id.ic_instagram_social_network);
        facebookBtn = view.findViewById(R.id.ic_facebook_soсial_network);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doActionsIfNoInternetConnection(view);

        ViewModelForHomePage viewModelForHomePage = ViewModelProviders.of(this).get(ViewModelForHomePage.class);


        //второй ресайклер. Он будет для рекламы
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(layoutManager2);


        PagerSnapHelper pagerSnapHelper2 = new PagerSnapHelper();
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();


        circleIndicator = view.findViewById(R.id.circleIndicator_1_NestedScroll_View);
        circleIndicator2 = view.findViewById(R.id.circleIndicator_2_NestedScroll_View);


        firstRecyclerView = view.findViewById(R.id.firstRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdaptor recyclerViewAdaptor = new RecyclerViewAdaptor(R.layout.first_recycler_view_item, getActivity());
        firstRecyclerView.setAdapter(recyclerViewAdaptor);
        firstRecyclerView.setHasFixedSize(true);

        viewModelForHomePage.getDataForHomePageForFirstRecyclerView().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                recyclerViewAdaptor.submitList(items);
                pagerSnapHelper.attachToRecyclerView(firstRecyclerView);
                circleIndicator.attachToRecyclerView(firstRecyclerView, pagerSnapHelper);

            }
        });

        AdvertisingRecyclerViewAdaptor recyclerViewAdaptor2 = new AdvertisingRecyclerViewAdaptor(R.layout.advertising_layout_for_second_rv, getActivity());
        secondRecyclerView.setAdapter(recyclerViewAdaptor2);
        secondRecyclerView.setHasFixedSize(true);

        viewModelForHomePage.getDataFroHomePageAdvertisementRecyclerView().observe(this, new Observer<List<Advertising>>() {
            @Override
            public void onChanged(List<Advertising> advertisings) {
                recyclerViewAdaptor2.submitList(advertisings);
                pagerSnapHelper2.attachToRecyclerView(secondRecyclerView);
                circleIndicator2.attachToRecyclerView(secondRecyclerView, pagerSnapHelper);
            }
        });


        thirdRecyclerView = view.findViewById(R.id.thirdRecyclerView);
        RecyclerViewAdaptor recyclerViewAdaptor3 = new RecyclerViewAdaptor(R.layout.third_recycler_view_item, getActivity());
        thirdRecyclerView.setAdapter(recyclerViewAdaptor3);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(linearLayoutManager3);

        viewModelForHomePage.getDataForHomePageForThirdRecyclerView().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                recyclerViewAdaptor3.submitList(items);
            }
        });

        toolbar = view.findViewById(R.id.customToolbar);
        toolbar.inflateMenu(R.menu.notification);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("MyTag", "Нажал на уведомления");
                Toast.makeText(getActivity(), "Нажал на уведомления", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        recyclerViewAdaptor2.setItemClickListener((OnRecyclerViewItemClick<Advertising>) item -> {
            Log.d("MyTag", "Нажал на РЕКЛАМУ ");
            Toast.makeText(getActivity(), "Нажал на РЕКЛАМУ", Toast.LENGTH_LONG).show();
        });
        recyclerViewAdaptor.setItemClickListener((OnRecyclerViewItemClick<Item>) item -> {
            Toast.makeText(getActivity(), "Нажал на " + item.getItemName(), Toast.LENGTH_LONG).show();
            Bundle data = dataThatSendToOtherFragment(item);
            Navigation.findNavController(view).navigate(R.id.productFragment, data);
        });
        recyclerViewAdaptor3.setItemClickListener((OnRecyclerViewItemClick<Item>) item -> {
            Toast.makeText(getActivity(), "Нажал на " + item.getItemName(), Toast.LENGTH_LONG).show();
            Bundle data = dataThatSendToOtherFragment(item);
            Navigation.findNavController(view).navigate(R.id.productFragment, data);
        });

        searchLine.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String name = searchLine.getText().toString();
                    Bundle data = new Bundle();
                    data.putString("ProductThatNeedToFind", name);
                    Navigation.findNavController(view).navigate(R.id.prductListByCategoryFragment, data);
                }
                return false;
            }
        });


        youtubeBtn.setOnClickListener(v -> openNecessaryUserPage("com.google.android.youtube","https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA"));
        facebookBtn.setOnClickListener(v -> openNecessaryUserPage("com.facebook.katana","https://ru-ru.facebook.com/wildberries.ru"));
        odnoklasnikiBtn.setOnClickListener(v -> openNecessaryUserPage("ru.ok.android","https://ok.ru/wildberries"));
        secondVkBtn.setOnClickListener(v -> openNecessaryUserPage("com.vkontakte.android","https://vk.com/wildberries_shop"));
        twitterBtn.setOnClickListener(v -> openNecessaryUserPage("com.twitter.android","https://twitter.com/wildberries_ru?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor"));
        instagramBtn.setOnClickListener(v->openNecessaryUserPage("com.instagram.android","https://www.instagram.com/_u/wildberriesru"));
    }


    private  void openNecessaryUserPage(String packageName,String necessary){
        Uri uri=Uri.parse(necessary);
        try {
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage(packageName);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("MyTag", "" + e.toString());
        }
    }

    private Bundle dataThatSendToOtherFragment(Item item) {
        Bundle data = new Bundle();
        data.putString("ProductName", item.getItemName());
        data.putString("ProductCost", String.valueOf(item.getCost()));
        data.putString("ProductDescription", item.getDescription());
        data.putInt("ProductCategory", item.getCategory());
        return data;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.notification, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void doActionsIfNoInternetConnection(View view){
        internetConnectionChecker=new InternetConnectionChecker(getActivity().getApplication());
        internetConnectionChecker.execute();
        try {
            boolean internet= internetConnectionChecker.get(1, TimeUnit.SECONDS);
            if(internet){
                Log.e("MyTag","Интернет ЕСТЬ");
            }else {
                Log.e("MyTag","Интернет НЕТ");

                Bundle data=new Bundle();
                data.putInt("FragmentThatIsNotHaveInternet",R.id.homeFragment);
                Navigation.findNavController(view).navigate(R.id.noInternetConection,data);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
