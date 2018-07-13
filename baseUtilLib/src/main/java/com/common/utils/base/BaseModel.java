package com.common.utils.base;

import com.common.utils.api.ApiFactory;
import com.common.utils.api.ApiHomeService;

public abstract class BaseModel {
    public static final ApiHomeService homeService = ApiFactory.getHomeService();
}
