package com.xlily6x.core.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaowenlong on 18/8/2017.
 */
public class InstanceUtil  {

    private InstanceUtil(){}

    public static Map<String,MethodAccess> methodMap = new HashMap<String,MethodAccess>();

    public static final Object invokeMethod(Object owner,String methodName,Object ... args){
        Class<?> ownerClass = owner.getClass();
        String key = null;
        if(args!=null){
            Class<?>[] argsClass = new Class[args.length];
            for(int i=0,j=args.length;i<j;i++){
                if(args[i]!=null){
                    argsClass[i] = args[i].getClass();
                }
            }
            //用于区分方法重载
            key = ownerClass +"_"+methodName+"_"+ StringUtils.join(argsClass,",");
        }else{
            key = ownerClass +"_"+methodName;
        }
        MethodAccess methodAccess = methodMap.get(key);
        if(methodAccess==null){
            methodAccess = MethodAccess.get(ownerClass);
            methodMap.put(key,methodAccess);
        }

        return methodAccess.invoke(owner,methodName,args);

    }
}
