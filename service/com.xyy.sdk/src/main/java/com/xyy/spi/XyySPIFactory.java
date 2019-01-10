package com.xyy.spi;

import com.xyy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @描述: XyySPIFactory
 * @作者: DuKai
 * @创建时间: 2018/7/26 16:36
 * @版本号: V3.0
 */
public class XyySPIFactory {

	private static Logger logger = LoggerFactory.getLogger(XyySPIFactory.class);

	private ConcurrentHashMap<String, XyySPIList> cacheMap = null;

	private static class GySpiFactoryHolder {
		private static XyySPIFactory instance = new XyySPIFactory();
	}

	private XyySPIFactory() {
		cacheMap = new ConcurrentHashMap<>();
	}

	/**
	 * 
	 * @return
	 */
	public static XyySPIFactory getInstance() {
		return GySpiFactoryHolder.instance;
	}

	/**
	 * 
	 * @param interCls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getDefaultExtension(Class<?> interCls) {
		if (interCls == null || !interCls.isInterface()) {
			logger.error("interCls must a interface!");
			return null;
		}
		String interName = interCls.getName();
		if (this.cacheMap.containsKey(interName)) {
			return getDefaultExtension(this.cacheMap.get(interName));
		}
		XyySPIList spiList = new XyySPIList();
		ServiceLoader serviceLoader = ServiceLoader.load(interCls);
		Iterator iter = serviceLoader.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			SPI spi = obj.getClass().getAnnotation(SPI.class);
			if(spi==null)
			{
				spiList.addSPI(obj.getClass().getName(), false, obj);
			}
			else
			{
				spiList.addSPI(spi.value(), spi.isDefault(), obj);
			}
			
		}
		this.cacheMap.put(interName,spiList);
		return this.getDefaultExtension(spiList);
	}
	
	/**
	 * 
	 * @param interCls
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getExtension(Class<?> interCls, String val) {
		if (interCls == null || !interCls.isInterface()) {
			logger.error("interCls must a interface!");
			return null;
		}
		String interName = interCls.getName();
		if (this.cacheMap.containsKey(interName)) {
			return getExtension(this.cacheMap.get(interName),val);
		}
		XyySPIList spiList = new XyySPIList();
		ServiceLoader serviceLoader = ServiceLoader.load(interCls);
		Iterator iter = serviceLoader.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			SPI spi = obj.getClass().getAnnotation(SPI.class);
			spiList.addSPI(spi.value(), spi.isDefault(), obj);
		}
		this.cacheMap.put(interName,spiList);
		return this.getExtension(spiList, val);
	}
	
	/**
	 * 
	 * @param list
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getExtension(XyySPIList list,String val)
	{
		if(list!=null)
		{
			List<XyySPIObj> spiList = list.getSPIList();
			if(spiList==null||spiList.isEmpty())
			{
				logger.error("not found spilist ");
				return null;
			} 
			for(XyySPIObj obj:spiList)
			{
				 if(StringUtil.equals(obj.getValue(), val))
				 {
					 return (T)obj.getTarget();
				 } 
			}
			logger.info("find val:{} failed,use Default Object",val);
			return getDefaultExtension(list);
		}
		logger.error("XyySPIList is null! ");
		return null;
	}
	
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getDefaultExtension(XyySPIList list)
	{
		if(list!=null)
		{
			List<XyySPIObj> spiList = list.getSPIList();
			if(spiList==null||spiList.isEmpty())
			{
				logger.error("not found spilist ");
				return null;
			} 
			for(XyySPIObj obj:spiList)
			{
				 if(obj.isDefault)
				 {
					 return (T)obj.getTarget();
				 } 
			}
			logger.error("Default Object is null,please check conf again!");
			return null;
		}
		logger.error("MhsSPIList is null! ");
		return null;
	}

	/**
	 * 
	 * @author Administrator
	 *
	 */
	private static class XyySPIList implements java.io.Serializable {
		private List<XyySPIObj> objs = null;

		public XyySPIList() {
			objs = new ArrayList<>();
		}

		public void addSPI(XyySPIObj obj) {
			if (obj != null) {
				objs.add(obj);
			}
		}

		public void addSPI(String val, boolean isDefault, Object target) {
			XyySPIObj obj = new XyySPIObj();
			obj.setDefault(isDefault);
			obj.setTarget(target);
			obj.setValue(val);
			this.addSPI(obj);
		}
		
		public List<XyySPIObj> getSPIList() {
             return objs;
		}
	}

	/**
	 * 
	 * @author Administrator
	 *
	 */
	private static class XyySPIObj implements java.io.Serializable {

		private boolean isDefault;

		private String value;

		private Object target;

		public boolean isDefault() {
			return isDefault;
		}

		public void setDefault(boolean isDefault) {
			this.isDefault = isDefault;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Object getTarget() {
			return target;
		}

		public void setTarget(Object target) {
			this.target = target;
		}

	}
}
