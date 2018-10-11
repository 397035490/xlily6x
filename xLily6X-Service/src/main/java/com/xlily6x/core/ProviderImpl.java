package com.xlily6x.core;

import com.alibaba.dubbo.config.annotation.Service;
import com.xlily6x.core.base.BaseProviderImpl;
import com.xlily6x.provider.IProvider;

/**
 * Created by xiaowenlong on 18/8/2017.
 */
@Service(interfaceClass = IProvider.class)
public class ProviderImpl extends BaseProviderImpl implements IProvider {
}
