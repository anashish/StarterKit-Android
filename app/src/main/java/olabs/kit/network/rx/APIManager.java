package olabs.kit.network.rx;

import java.util.HashMap;

import olabs.kit.Constants;
import olabs.network.rxnetwork.RxNetworkServiceFactory;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by ttnd on 25/11/16.
 */

public abstract class APIManager<T> extends RxResponseHandler<T> {

    private HashMap<String, String> mRequestHeaderMap = new HashMap<>();
    private Scheduler defaultSubscribeScheduler;

    public APIManager() {
        subscriptions.clear();
        initHeaders();
    }

    public <S> S getServiceClient(Class<S> serviceClass) {
        if (Constants.IS_HEADER_UPDATE) {
            Constants.IS_HEADER_UPDATE = false;
            updateHeaders();
            return RxNetworkServiceFactory.getNewInstance(Constants.BASE_URL, serviceClass, mRequestHeaderMap);
        }
        return RxNetworkServiceFactory.getInstance(Constants.BASE_URL, serviceClass, mRequestHeaderMap);
    }

    private void initHeaders() {
        mRequestHeaderMap.put("app-type", "M");
        mRequestHeaderMap.put("Content-Type", "application/json");
        mRequestHeaderMap.put("Authorization", Constants.BASIC_AUTHORIZATION);
    }

    private void updateHeaders() {
        mRequestHeaderMap.put("app-type", "M");
        mRequestHeaderMap.put("Content-Type", "application/json");
        mRequestHeaderMap.put("Authorization", Constants.APP_AUTHORIZATION);
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

}
