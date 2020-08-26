package com.nineone.nocm.xss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeFilter;



public class XssEscapeServletFilterWrapper extends HttpServletRequestWrapper{
	
	
	private XssEscapeFilter xssEscapeFilter;
	private String path;
//	private Gson gson = new Gson();
	private Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new MapDeserializer()).setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
	private boolean isMultipart;

	public XssEscapeServletFilterWrapper(ServletRequest request, XssEscapeFilter xssEscapeFilter) {
		super((HttpServletRequest)request);

		isMultipart = isMultipartContent((HttpServletRequest)request);

		this.xssEscapeFilter = xssEscapeFilter;

		String contextPath = ((HttpServletRequest)request).getContextPath();
		this.path = ((HttpServletRequest)request).getRequestURI().substring(contextPath.length());
	}

	@Override
	public String getParameter(String paramName) {
		String value = super.getParameter(paramName);
		return doFilter(paramName, value);
	}

	@Override
	public String[] getParameterValues(String paramName) {
		String values[] = super.getParameterValues(paramName);
		if(values == null) {
			return values;
		}
		for(int index = 0; index < values.length; index++) {
			values[index] = doFilter(paramName, values[index]);
		}
		return values;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = super.getParameterMap();
		Map<String, String[]> newFilteredParamMap = new HashMap<String, String[]>();

		Set<Entry<String, String[]>> entries = paramMap.entrySet();
		for(Entry<String, String[]> entry : entries) {
			String paramName = entry.getKey();
			Object[] valueObj = (Object[])entry.getValue();
			String[] filteredValue = new String[valueObj.length];
			for(int index = 0; index < valueObj.length; index++) {
				filteredValue[index] = doFilter(paramName, String.valueOf(valueObj[index]));
			}

			newFilteredParamMap.put(entry.getKey(), filteredValue);
		}
		return newFilteredParamMap;
	}

	/****
	 * application/json 일때 사용하는 inputStream 에도 필터링
	 * 단, multipart 데이터로 넘어오는 경우에도 해당 stream 을 사용할 수 있는데, 이때는 exception 나와서 그냥 원본 inputStream 넘기도록 해준다.
	 * @return
	 */
	@Override
	public ServletInputStream getInputStream() {

			try {
				if(isMultipart){
					return super.getInputStream();
				}
				String inputString = IOUtils.toString(super.getInputStream(), getCharacterEncoding());
				Map<String, Object> map = gson.fromJson(inputString, new TypeToken<Map<String,Object>>(){}.getType());

				Set<String> keys = map.keySet();
				for(String key : keys) {
					Object value = map.get(key);
					if(value instanceof String) {
						map.put(key, doFilter(key, (String)map.get(key)));
					}
				}
				String result = gson.toJson(map);

				return new XssFilteredServletInputStream(new ByteArrayInputStream(result.getBytes(getCharacterEncoding())));
			} catch(IOException ioe) {
				// error handling
				ioe.printStackTrace();
			} catch(JsonParseException jpe) {
				// error handling
				jpe.printStackTrace();
			}
		return getInputStream();
	}

	public class XssFilteredServletInputStream extends ServletInputStream {
		private ByteArrayInputStream input;

		public XssFilteredServletInputStream(ByteArrayInputStream bis) {
			input = bis;
		}

		@Override
		public int read() {
			return input.read();
		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			// TODO Auto-generated method stub
			
		}
	}

	public boolean isMultipartContent(HttpServletRequest request) {
		String contentType = request.getContentType();
		if(contentType == null) {
			return false;
		}
		if(contentType.toLowerCase().startsWith("multipart/form-data")) {
			return true;
		}
		return false;
	}

	/**
	 * @param paramName String
	 * @param value     String
	 * @return String
	 */
	private String doFilter(String paramName, String value) {		
		return xssEscapeFilter.doFilter(path, paramName, value);
	}
}
