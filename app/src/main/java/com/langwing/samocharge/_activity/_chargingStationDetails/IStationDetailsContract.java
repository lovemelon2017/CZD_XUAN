package com.langwing.samocharge._activity._chargingStationDetails;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by WYJ
 * on 2017/9/7.
 */

public interface IStationDetailsContract {

    interface IStationDetailsView extends IBaseView {
        void addMarker(Station station);

        void initStation(StationDetails stationDetails);

        void setCollect(boolean b);
    }

    interface IStationDetailsPresenter extends IBasePresenter {
        void getPileDetails(int pileID);

        void addCollect(int stationID);

        void removeCollect(int stationID);
    }

    class StationDetailsModel {
        void getPileDetails(int pileID, OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/station/" + pileID + "/details", getData);
        }

        void addCollect(int stationID, OKHttpUtil.GetData getData) {
            RequestBody requestBody = new FormBody.Builder()
                    .add("station_id", String.valueOf(stationID))
                    .add("action", "add")
                    .build();
            OKHttpUtil.post(URL.API + "/favorite-station", requestBody, getData);
        }

        void removeCollect(int stationID, OKHttpUtil.GetData getData) {
            RequestBody requestBody = new FormBody.Builder()
                    .add("station_id", String.valueOf(stationID))
                    .add("action", "remove")
                    .build();
            OKHttpUtil.post(URL.API + "/favorite-station", requestBody, getData);
        }
    }
}
