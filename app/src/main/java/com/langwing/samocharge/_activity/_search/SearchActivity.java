package com.langwing.samocharge._activity._search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.EditorInfo;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingStationDetails.StationDetailsActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._fragment._chargingPile._list.ListAdapter;

import java.util.List;

/**
 *
 */
public class SearchActivity extends BaseBackActivity implements  ISearchContract.ISearchView {

    private SearchPresenter searchPresenter;
    private RecyclerView rvCharingPile;

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("搜索");
        AppCompatEditText etSearch = findViewById(R.id.et_search);
        rvCharingPile = findViewById(R.id.rv_charging_pile);
        rvCharingPile.setLayoutManager(new LinearLayoutManager(this));
        searchPresenter = new SearchPresenter(this);
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                String name = etSearch.getText().toString().trim();
                searchPresenter.getSearchDetails(name);
                return true;
            }
            return false;
        });
    }

    @Override
    public void initRvChargingPile(List<Station> stationList) {
        ListAdapter adapter = new ListAdapter(this, stationList);
        rvCharingPile.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(SearchActivity.this, StationDetailsActivity.class);
            intent.putExtra("Station", stationList.get(position));
            startActivity(intent);
        });
    }
}
